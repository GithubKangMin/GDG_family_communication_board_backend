import React, { useState } from 'react';

function Login() {
    const [loginData, setLoginData] = useState({
        username: '',
        password: ''
    });

    const handleChange = (e) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // 로그인 요청 API 호출
        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        if (response.ok) {
            const data = await response.json();
            // 로그인 성공 시, JWT 토큰 또는 세션 저장
            localStorage.setItem('token', data.token);
            alert('로그인 성공!');
        } else {
            alert('로그인 실패. 아이디 또는 비밀번호를 확인하세요.');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="username"
                placeholder="아이디"
                value={loginData.username}
                onChange={handleChange}
            />
            <input
                type="password"
                name="password"
                placeholder="비밀번호"
                value={loginData.password}
                onChange={handleChange}
            />
            <button type="submit">로그인</button>
        </form>
    );
}

export default Login;
