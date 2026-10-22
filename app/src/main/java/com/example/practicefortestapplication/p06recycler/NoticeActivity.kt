package com.example.practicefortestapplication.p06recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicefortestapplication.databinding.P06ActivityNoticeBinding

/**
 * 문제 6. 공지사항 목록 RecyclerView (5단계 중 5단계: 화면 연결)
 */
class NoticeActivity : AppCompatActivity() {

    private lateinit var binding: P06ActivityNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P06ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notices = listOf(
            Notice("2학기 중간고사 일정 안내", "2026-10-15", true),
            Notice("학습법 특강 신청", "2026-10-08"),
            Notice("코스모스 앱 접속 오류 정상화", "2026-10-08")
        )

        // TODO: 6번 문제 - binding.recyclerView.layoutManager 에 LinearLayoutManager(this) 대입
        //  (확장) GridLayoutManager(this, 2) 로 바꿔 2열 그리드 확인
        // TODO: 6번 문제 - binding.recyclerView.adapter 에 NoticeAdapter(notices) 대입
    }
}
