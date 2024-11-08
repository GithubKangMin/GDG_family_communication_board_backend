import React, { useState } from 'react';

function MemberRegister() {
    const [memberData, setMemberData] = useState({
        familyNickname: '',
        name: '',
        age: '',
        username: '',
        password: ''
    });

    const handleChange = (e) => {
        setMemberData({
            ...memberData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // 회원 등록 API 호출
        await fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(memberData)
        });
        alert('회원 등록 완료!');
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="familyNickname" placeholder="가족별칭" onChange={handleChange} />
            <input type="text" name="name" placeholder="이름" onChange={handleChange} />
            <input type="number" name="age" placeholder="나이" onChange={handleChange} />
            <input type="text" name="username" placeholder="아이디" onChange={handleChange} />
            <input type="password" name="password" placeholder="비밀번호" onChange={handleChange} />
            <button type="submit">회원 등록</button>
        </form>
    );
}

export default MemberRegister;
