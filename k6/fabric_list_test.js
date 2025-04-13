import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 50,            // 가상 사용자 수 (동시 접속자 수)
    duration: '20s',    // 테스트 지속 시간
};

export default function () {
    const BASE_URL = 'http://localhost:8080';
    const page = Math.floor(Math.random() * 100); // 랜덤 페이지 요청
    const size = 15;

    const res = http.get(`${BASE_URL}/api/v1/fabric?&page=${page}&size=${size}`);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'body is not empty': (r) => r.body.length > 0,
    });

    sleep(1);
}
