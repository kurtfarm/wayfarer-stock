import http from 'k6/http';
import { check, sleep } from 'k6';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const options = {
    vus: 50,               // 동시에 데이터를 등록할 사용자 수
    iterations: 255598,
};

const fabricTypeDescriptions = [
    "PET", "양PET", "OPP", "양OPP", "무광OPP",
    "NY", "양NY", "AL", "투명LLD", "옥텐LLD",
    "유백LLD", "옥텐유백LLD", "증착PET", "CPP",
    "증착CPP", "이지컷LLD", "이지필LLD", "CPR1",
    "CPR2", "CPR3", "CPR4", "직접입력"
];

export default function () {
    const BASE_URL = 'http://localhost:8080'; // 네 서버 주소로 변경
    const today = new Date();
    const randomDate = (offset) => {
        const date = new Date();
        date.setDate(date.getDate() - offset);
        return date.toISOString().split('T')[0]; // yyyy-mm-dd
    };

    const fabricTypeName = fabricTypeDescriptions[Math.floor(Math.random() * fabricTypeDescriptions.length)];

    const payload = JSON.stringify({
        registrationDate: randomDate(Math.floor(Math.random() * 100)),
        expectedArrivalDate: randomDate(Math.floor(Math.random() * 30)),
        ordererName: `발주처 ${Math.floor(Math.random() * 5) + 1}`,
        customerName: `고객사 ${Math.floor(Math.random() * 10) + 1}`,
        fabricTypeName: fabricTypeName,
        width: 100,
        length: 20.5,
        thickness: 0.3,
        quantity: Math.floor(Math.random() * 100) + 1,
        comment: `자동 생성된 원단 - ${uuidv4()}`
    });

    const headers = {
        'Content-Type': 'application/json',
    };

    const res = http.post(`${BASE_URL}/api/v1/fabric/register`, payload, { headers });

    check(res, {
        '등록 성공 (status 200)': (r) => r.status === 200,
        '응답이 true': (r) => JSON.parse(r.body).data === true,
    });

    sleep(0.1);
}
