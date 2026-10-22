package com.example.practicefortestapplication.p08memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P08ItemMemoBinding

/**
 * 문제 8. 메모 어댑터. DB 작업(thread)과 화면 반영(Handler.post)을 어댑터가 함께 책임진다.
 *  - DB 작업은 thread { } 안에서
 *  - 화면 변경(notifyDataSetChanged 등)은 Handler(Looper.getMainLooper()).post { } 안에서
 */
class MemoViewHolder(
    private val binding: P08ItemMemoBinding,
    private val onItemLongClick: (Memo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(memo: Memo) {
        binding.tvTitle.text = memo.title
        binding.tvContent.text = memo.content
        binding.root.setOnLongClickListener {
            onItemLongClick(memo)
            true
        }
    }
}

class MemoAdapter(
    private val onItemLongClick: (Memo) -> Unit
) : RecyclerView.Adapter<MemoViewHolder>() {

    private var items: MutableList<Memo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val binding = P08ItemMemoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MemoViewHolder(binding, onItemLongClick)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun loadItems(dao: MemoDao) {
        // TODO: 8번 문제 - thread 안에서 dao.getAll() 호출,
        //  Handler(Looper.getMainLooper()).post 안에서 items 교체 후 notifyDataSetChanged()
    }

    fun addItem(memo: Memo, dao: MemoDao) {
        // TODO: 8번 문제 - thread 안에서 dao.insert(memo),
        //  Handler.post 안에서 items.add(0, memo) 후 notifyDataSetChanged()
    }

    fun deleteItem(memo: Memo, dao: MemoDao) {
        // TODO: 8번 문제 - thread 안에서 dao.delete(memo),
        //  Handler.post 안에서 items.remove(memo) 후 notifyDataSetChanged()
    }
}
