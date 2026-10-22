package com.example.practicefortestapplication.p06recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P06ItemNoticeBinding

/**
 * 문제 6. 공지 어댑터 (RecyclerView 5단계 중 3·4단계)
 *
 * ViewHolder.bind: 제목/날짜 채우기 + 중요 공지만 배지 표시(나머지는 자리도 차지 않게 GONE).
 * Adapter: onCreateViewHolder / onBindViewHolder / getItemCount 세 메소드.
 */
class NoticeViewHolder(private val binding: P06ItemNoticeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(notice: Notice) {
        binding.tvNoticeTitle.text = notice.title
        binding.tvNoticeDate.text = notice.date
        // TODO: 6번 문제 - notice.important 면 tvBadge 를 VISIBLE, 아니면 GONE (둘 다 지정)
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
        // TODO: 6번 문제 - position 위치의 데이터를 holder.bind(...) 로 연결
    }

    override fun getItemCount(): Int {
        // TODO: 6번 문제 - 목록 개수 반환
        return 0
    }
}
