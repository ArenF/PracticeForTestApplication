package com.example.practicefortestapplication.p09contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P09ItemContactBinding

/**
 * 문제 9. 연락처 어댑터. 롱클릭 시 삭제 람다 호출.
 */
class ContactAdapter(
    private val items: List<Contact>,
    private val onItemLongClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.VH>() {

    class VH(val binding: P09ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = P09ItemContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val contact = items[position]
        holder.binding.tvName.text = contact.name
        holder.binding.tvPhone.text = contact.phone
        holder.binding.root.setOnLongClickListener {
            onItemLongClick(contact)
            true
        }
    }

    override fun getItemCount(): Int = items.size
}
