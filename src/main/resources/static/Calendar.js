import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import { useForm } from 'react-hook-form';
import './App.css';

const CalendarApp = () => {
    const [date, setDate] = useState(new Date()); // 선택된 날짜 저장
    const [events, setEvents] = useState([]); // 전체 일정을 저장
    const { register, handleSubmit, reset } = useForm();

    // 선택한 날짜를 "YYYY.M.D" 형식으로 변환하는 함수
    const formatDate = (date) => {
        const year = date.getFullYear();
        const month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더함
        const day = date.getDate();
        return `${year}.${month}.${day}`;
    };

    // 일정 등록 처리 함수
    const onSubmit = (data) => {
        const newEvent = {
            name: data.name,
            title: data.title,
            date: formatDate(date), // 선택된 날짜를 포맷하여 저장
        };
        setEvents([...events, newEvent]); // 새 일정을 기존 일정에 추가
        reset(); // 폼 초기화
    };

    // 캘린더에서 날짜 선택 시 호출되는 함수
    const onChange = (selectedDate) => {
        setDate(selectedDate); // 선택된 날짜를 상태로 업데이트
    };

    return (
        <div className="calendar-app">
            <h1>캘린더 일정 등록</h1>
            {/* 캘린더 UI */}
            <Calendar onChange={onChange} value={date} />

            {/* 선택된 날짜를 YYYY.M.D 형식으로 표시 */}
            <div>
                <h2>{formatDate(date)}의 일정</h2>
                <ul>
                    {events
                        .filter((event) => event.date === formatDate(date)) // 선택한 날짜의 일정만 필터링
                        .map((event, index) => (
                            <li key={index}>
                                <strong>{event.name}</strong>: {event.title}
                            </li>
                        ))}
                </ul>
            </div>

            <h2>일정 등록하기</h2>
            {/* 일정 등록 폼 */}
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <label>이름:</label>
                    <input {...register('name')} placeholder="이름을 입력하세요" required />
                </div>
                <div>
                    <label>일정 제목:</label>
                    <input {...register('title')} placeholder="일정 제목을 입력하세요" required />
                </div>
                <button type="submit">등록</button>
            </form>
        </div>
    );
};

export default CalendarApp;