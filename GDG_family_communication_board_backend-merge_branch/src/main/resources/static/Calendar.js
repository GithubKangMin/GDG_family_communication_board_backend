// Calendar.js

import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import { useForm } from 'react-hook-form';
import './App.css';

const CalendarApp = () => {
    const [date, setDate] = useState(new Date());
    const [events, setEvents] = useState([]);
    const { register, handleSubmit, reset } = useForm();

    const formatDate = (date) => {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${year}-${month}-${day}`;
    };

    const onSubmit = (data) => {
        const newEvent = {
            title: data.title,
            description: data.description,
            eventDate: formatDate(date),
            userId: 1 // 예시용 사용자 ID
        };

        fetch('/api/events/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newEvent),
        })
            .then(response => response.json())
            .then(data => {
                console.log('일정 생성 성공:', data);
                setEvents([...events, newEvent]); // 일정 추가
                reset();
            })
            .catch(error => {
                console.error('일정 생성 오류:', error);
            });
    };

    const onChange = (selectedDate) => {
        setDate(selectedDate);
    };

    return (
        <div className="calendar-app">
            <h1>캘린더 일정 등록</h1>
            <Calendar onChange={onChange} value={date} />
            <div>
                <h2>{formatDate(date)}의 일정</h2>
                <ul>
                    {events
                        .filter(event => event.eventDate === formatDate(date))
                        .map((event, index) => (
                            <li key={index}>
                                <strong>{event.title}</strong>: {event.description}
                            </li>
                        ))}
                </ul>
            </div>
            <h2>일정 등록하기</h2>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <label>일정 제목:</label>
                    <input {...register('title')} placeholder="일정 제목을 입력하세요" required />
                </div>
                <div>
                    <label>설명:</label>
                    <input {...register('description')} placeholder="설명을 입력하세요" required />
                </div>
                <button type="submit">등록</button>
            </form>
        </div>
    );
};

ReactDOM.render(<CalendarApp />, document.getElementById('calendar-root'));
