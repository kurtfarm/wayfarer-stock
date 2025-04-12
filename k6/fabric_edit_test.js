import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    vus: 10, // 10명 동시 사용자
    duration: '5s',
};

const baseUrl = 'http://localhost:8080';
const headers = { headers: { 'Content-Type': 'application/json' } };

let latestCommentByVU = {};  // 사용자별 comment 저장

export function setup() {
    // 1. 등록 API 호출
    const registerPayload = JSON.stringify({
        registrationDate: "2025-04-11",
        expectedArrivalDate: "2025-04-11",
        ordererName: "홍길동",
        customerName: "테스트고객",
        fabricTypeName: "면직물",
        width: 100,
        length: 1.5,
        thickness: 0.3,
        quantity: 500,
        comment: "초기 등록"
    });

    const res = http.post(`${baseUrl}/api/v1/fabric/register`, registerPayload, headers);
    check(res, { '등록 성공': (r) => r.status === 200 });

    const fabricId = 1;

    return { fabricId };
}

export default function (data) {
    // 각 사용자마다 고유한 comment 및 quantity 설정
    const vu = __VU;
    const comment = `사용자${vu}_수정`;
    const quantity = 500 + vu;
    latestCommentByVU[vu] = comment;

    const updatePayload = JSON.stringify({
        registrationDate: "2025-04-11",
        expectedArrivalDate: "2025-04-11",
        ordererName: `주문자${vu}`,
        customerName: `고객사${vu}`,
        fabricTypeName: "면직물",
        width: 100,
        length: 1.5,
        thickness: 0.3,
        quantity: quantity,
        comment: comment,
    });

    const res = http.put(`${baseUrl}/api/v1/fabric/${data.fabricId}`, updatePayload, headers);
    check(res, {
        '수정 성공': (r) => r.status === 200,
    });

    sleep(1);
}

export function teardown(data) {
    const res = http.get(`${baseUrl}/api/v1/fabric/${data.fabricId}`, headers);
    const result = JSON.parse(res.body);

    const finalComment = result.comment;
    const finalQuantity = result.quantity;

    console.log(`\n🧾 [최종 결과]`);
    console.log(`최종 comment: ${finalComment}`);
    console.log(`최종 quantity: ${finalQuantity}`);

    // 2. 어떤 사용자의 값인지 역추적
    let matchedUser = null;
    for (let vu = 1; vu <= 10; vu++) {
        if (latestCommentByVU[vu] === finalComment && latestQuantityByVU[vu] === finalQuantity) {
            matchedUser = vu;
            break;
        }
    }

    if (matchedUser) {
        console.log(`✅ 동시성 마지막 반영 사용자: 사용자 ${matchedUser}`);
    } else {
        console.error(`❌ 예상치 못한 데이터. 동시성 충돌 발생 가능성 있음!`);
    }
}
