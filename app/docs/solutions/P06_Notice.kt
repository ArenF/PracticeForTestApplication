/*
 * 문제 6 정답 — 공지 RecyclerView (ViewHolder bind + Adapter 3메소드 + 화면 연결).
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p06recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P06ActivityNoticeBinding
import com.example.practicefortestapplication.databinding.P06ItemNoticeBinding

// ───────── Activity (5단계) ─────────
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // 2열 그리드로 보려면: GridLayoutManager(this, 2)
        binding.recyclerView.adapter = NoticeAdapter(notices)
    }
}

// ───────── ViewHolder + Adapter ─────────
class NoticeViewHolder(private val binding: P06ItemNoticeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(notice: Notice) {
        binding.tvNoticeTitle.text = notice.title
        binding.tvNoticeDate.text = notice.date
        // 재사용되는 뷰홀더이므로 VISIBLE/GONE 둘 다 지정
        binding.tvBadge.visibility = if (notice.important) View.VISIBLE else View.GONE
    }
}

class NoticeAdapter(private val items: List<Notice>) :
    RecyclerView.Adapter<NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = P06ItemNoticeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
