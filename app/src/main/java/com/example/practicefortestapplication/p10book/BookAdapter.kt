package com.example.practicefortestapplication.p10book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P10ItemBookBinding

/**
 * 문제 10. 독서 기록 어댑터. DB 작업 + 화면 반영 + 상태 필터를 함께 책임진다.
 *  - allitems(전체) / items(화면용) 두 리스트
 *  - loadItems/addItem/updateItem/deleteItem 은 thread + Handler 패턴
 *  - 수정 후에도 applyFilters() 를 호출해 현재 필터에 즉시 반영 (수업 코드 개선 포인트)
 */
class BookViewHolder(
    private val binding: P10ItemBookBinding,
    private val currentUser: String,
    private val onItemClick: (BookRecord) -> Unit,
    private val onItemLongClick: (BookRecord) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookRecord) {
        with(binding) {
            tvTitle.text = item.title
            tvAuthor.text = "저자: ${item.author}"
            tvStatus.text = if (item.status == BookRecord.STATUS_DONE) "완독" else "읽는 중"
            ratingItem.rating = item.rating
            // TODO: 10번 문제 - tvOwner: 내 글이면 "내가 쓴 글", 아니면 "작성자: ${item.ownerId}"

            root.setOnClickListener { onItemClick(item) }
            root.setOnLongClickListener {
                onItemLongClick(item)
                true
            }
        }
    }
}

class BookAdapter(
    private val currentUser: String,
    private val onItemClick: (BookRecord) -> Unit,
    private val onItemLongClick: (BookRecord) -> Unit
) : RecyclerView.Adapter<BookViewHolder>() {

    private var items: MutableList<BookRecord> = mutableListOf()
    private var allitems: MutableList<BookRecord> = mutableListOf()
    private var statusFilter = 0   // 0 전체, 1 읽는 중, 2 완독

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = P10ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookViewHolder(binding, currentUser, onItemClick, onItemLongClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    private fun submitList(newItems: List<BookRecord>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun loadItems(dao: BookDao) {
        // TODO: 10번 문제 - thread 에서 dao.getAll(), Handler.post 에서 allitems 저장 후 applyFilters()
    }

    fun addItem(item: BookRecord, dao: BookDao) {
        // TODO: 10번 문제 - thread 에서 dao.insert, Handler.post 에서 allitems.add(0, item) 후 applyFilters()
    }

    fun updateItem(item: BookRecord, dao: BookDao) {
        // TODO: 10번 문제 - thread 에서 dao.update, Handler.post 에서 applyFilters() (상태 변경을 필터에 반영)
    }

    fun deleteItem(item: BookRecord, dao: BookDao) {
        // TODO: 10번 문제 - thread 에서 dao.delete, Handler.post 에서 allitems.remove(item) 후 applyFilters()
    }

    fun setFilter(statusFilter: Int) {
        this.statusFilter = statusFilter
        applyFilters()
    }

    private fun applyFilters() {
        // TODO: 10번 문제 - statusFilter(0 전체/1 읽는 중/2 완독)를 상태 문자열로 바꾸고
        //  allitems.filter { status == null || it.status == status } 결과를 submitList
    }
}
