/*
 * 문제 7 정답 — 클릭은 상세 다이얼로그, 롱클릭은 확인 후 삭제.
 * 어댑터는 "무엇을 할지" 모른다: 함수형 매개변수 (Notice)->Unit 을 호출만 한다.
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p07recycleraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P07ActivityNoticeBinding
import com.example.practicefortestapplication.databinding.P07ItemNoticeBinding

// ───────── Activity: 클릭/롱클릭 동작을 람다로 주입 ─────────
class NoticeActionActivity : AppCompatActivity() {

    private lateinit var binding: P07ActivityNoticeBinding
    private lateinit var adapter: NoticeAdapter   // 람다에서 참조하므로 클래스 속성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P07ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notices = mutableListOf(
            Notice("2학기 중간고사 일정 안내", "2026-10-15", true),
            Notice("학습법 특강 신청", "2026-10-08"),
            Notice("코스모스 앱 접속 오류 정상화", "2026-10-08")
        )

        adapter = NoticeAdapter(notices,
            { notice ->                                   // 클릭: 상세
                AlertDialog.Builder(this)
                    .setTitle(notice.title)
                    .setMessage("게시일: ${notice.date}")
                    .setPositiveButton("확인", null)
                    .show()
            },
            { notice ->                                   // 롱클릭: 확인 후 삭제
                AlertDialog.Builder(this)
                    .setTitle("삭제")
                    .setMessage("'${notice.title}' 공지를 삭제할까요?")
                    .setPositiveButton("삭제") { _, _ -> adapter.removeItem(notice) }
                    .setNegativeButton("취소", null)
                    .show()
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}

// ───────── ViewHolder + Adapter ─────────
class NoticeViewHolder(
    private val binding: P07ItemNoticeBinding,
    private val onItemClick: (Notice) -> Unit,
    private val onItemLongClick: (Notice) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notice: Notice) {
        binding.tvNoticeTitle.text = notice.title
        binding.tvNoticeDate.text = notice.date
        binding.tvBadge.visibility = if (notice.important) View.VISIBLE else View.GONE

        binding.root.setOnClickListener { onItemClick(notice) }
        binding.root.setOnLongClickListener {
            onItemLongClick(notice)
            return@setOnLongClickListener true          // false 면 롱클릭 후 클릭까지 발생
        }
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
        items.remove(notice)
        notifyDataSetChanged()
    }
}
