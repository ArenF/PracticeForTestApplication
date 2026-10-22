package com.example.practicefortestapplication.p02spinner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P02ItemLostfoundBinding

/**
 * 문제 2. 분실물 어댑터 + 필터.
 *  - allitems: 전체 목록 (원본, 건드리지 않음)
 *  - items: 화면에 보일 목록
 *  - setFilters(typeFilter, statusFilter) → applyFilters() 로 조건에 맞는 것만 submitList
 */
class LostFoundAdapter : RecyclerView.Adapter<LostFoundAdapter.VH>() {

    private var items: MutableList<LostFoundItem> = mutableListOf()
    private var allitems: MutableList<LostFoundItem> = mutableListOf()
    private var typeFilter = 0
    private var statusFilter = 0

    class VH(val binding: P02ItemLostfoundBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LostFoundItem) {
            binding.tvItemName.text = item.name
            val typeText = if (item.type == LostFoundItem.TYPE_LOST) "분실물" else "습득물"
            val statusText = if (item.status == LostFoundItem.STATUS_OPEN) "진행중" else "완료"
            binding.tvItemMeta.text = "$typeText · $statusText · ${item.place}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = P02ItemLostfoundBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    /** 전체 데이터를 넣고 현재 필터를 적용한다. */
    fun setItems(newItems: List<LostFoundItem>) {
        allitems = newItems.toMutableList()
        applyFilters()
    }

    fun setFilters(typeFilter: Int, statusFilter: Int) {
        this.typeFilter = typeFilter
        this.statusFilter = statusFilter
        applyFilters()
    }

    private fun applyFilters() {
        // TODO: 2번 문제 - typeFilter(0 전체/1 분실물/2 습득물)와 statusFilter(0 전체/1 진행중/2 완료)를
        //  문자열 조건으로 바꾸고(0이면 null = 조건 없음), allitems 에서 두 조건을 모두 만족하는 것만 골라 submitList 호출.
    }

    private fun submitList(newItems: List<LostFoundItem>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }
}
