package com.example.practicefortestapplication.p06recycler

/** 문제 6. 공지 데이터. important=true 인 공지만 "중요" 배지 표시. */
data class Notice(
    val title: String,
    val date: String,
    val important: Boolean = false
)
