package com.example.practicefortestapplication.p07recycleraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicefortestapplication.databinding.P07ActivityNoticeBinding

/**
 * 문제 7. 항목 클릭은 상세 다이얼로그, 롱클릭은 확인 후 삭제.
 *
 * 클릭했을 때 무엇을 할지는 Activity 가 람다로 결정해 어댑터에 넘긴다.
 * 어댑터는 lateinit 속성으로 선언해야 람다 안에서 adapter.removeItem(...) 을 참조할 수 있다.
 */
class NoticeActionActivity : AppCompatActivity() {

    private lateinit var binding: P07ActivityNoticeBinding
    private lateinit var adapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P07ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notices = mutableListOf(
            Notice("2학기 중간고사 일정 안내", "2026-10-15", true),
            Notice("학습법 특강 신청", "2026-10-08"),
            Notice("코스모스 앱 접속 오류 정상화", "2026-10-08")
        )

        // TODO: 7번 문제 - NoticeAdapter 를 생성한다.
        //  1) 클릭 람다: AlertDialog 로 제목/날짜 표시
        //  2) 롱클릭 람다: 확인 다이얼로그 → "삭제" 선택 시 adapter.removeItem(notice)
        //  adapter = NoticeAdapter(notices, { ... }, { ... })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // TODO: 7번 문제 - binding.recyclerView.adapter = adapter
    }
}
