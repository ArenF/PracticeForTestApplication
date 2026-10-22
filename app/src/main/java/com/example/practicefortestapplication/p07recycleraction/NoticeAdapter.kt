package com.example.practicefortestapplication.p07recycleraction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P07ItemNoticeBinding

/**
 * 문제 7. 클릭 동작을 람다로 외부에서 주입받는 어댑터.
 *  - 어댑터는 "무엇을 할지" 모른다. 클릭되면 onItemClick/onItemLongClick 을 호출만 한다.
 *  - 삭제를 위해 items 는 MutableList.
 */
class NoticeViewHolder(
    private val binding: P07ItemNoticeBinding,
    private val onItemClick: (Notice) -> Unit,
    private val onItemLongClick: (Notice) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notice: Notice) {
        binding.tvNoticeTitle.text = notice.title
        binding.tvNoticeDate.text = notice.date
        binding.tvBadge.visibility = if (notice.important) View.VISIBLE else View.GONE

        // TODO: 7번 문제 - root 에 클릭 리스너: onItemClick(notice) 호출
        // TODO: 7번 문제 - root 에 롱클릭 리스너: onItemLongClick(notice) 호출 후 true 반환
    }
}

class NoticeAdapter(
    private val items: MutableList<Notice>,
    private val onItemClick: (Notice) -> Unit,
    private val onItemLongClick: (Notice) -> Unit
) : RecyclerView.Adapter<NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = P07ItemNoticeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoticeViewHolder(binding, onItemClick, onItemLongClick)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun removeItem(notice: Notice) {
        // TODO: 7번 문제 - items 에서 notice 제거 후 notifyDataSetChanged()
    }
}
