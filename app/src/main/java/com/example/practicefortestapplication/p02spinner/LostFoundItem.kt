package com.example.practicefortestapplication.p02spinner

/**
 * 문제 2. 분실물 항목 데이터.
 * type: 분실물/습득물, status: 진행중/완료
 */
data class LostFoundItem(
    val name: String,
    val place: String,
    val type: String,
    val status: String
) {
    companion object {
        const val TYPE_LOST = "LOST"
        const val TYPE_FOUND = "FOUND"
        const val STATUS_OPEN = "OPEN"
        const val STATUS_COMPLETED = "COMPLETED"
    }
}
