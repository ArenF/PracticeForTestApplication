/*
 * 문제 2 정답 — p02spinner 의 LostFoundActivity 와 LostFoundAdapter 를 이렇게 채운다.
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p02spinner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicefortestapplication.databinding.P02ActivityLostfoundBinding
import com.example.practicefortestapplication.databinding.P02ItemLostfoundBinding

// ───────────────────────── Activity ─────────────────────────
class LostFoundActivity : AppCompatActivity() {

    private lateinit var binding: P02ActivityLostfoundBinding
    private val adapter = LostFoundAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P02ActivityLostfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setItems(sampleItems())

        // 1) 종류 스피너는 코드에서 ArrayAdapter 로 채운다
        val types = arrayOf("전체", "분실물", "습득물")
        binding.spinnerTypeFilter.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, types)

        // 2) 리스너 하나를 만들어 두 스피너에 공유 (어느 것을 바꾸든 두 값을 모두 읽음)
        val filterListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter.setFilters(
                    binding.spinnerTypeFilter.selectedItemPosition,
                    binding.spinnerStatusFilter.selectedItemPosition
                )
            }
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
        binding.spinnerTypeFilter.onItemSelectedListener = filterListener
        binding.spinnerStatusFilter.onItemSelectedListener = filterListener
    }

    private fun sampleItems(): List<LostFoundItem> = listOf(
        LostFoundItem("검정 지갑", "도서관 2층", LostFoundItem.TYPE_LOST, LostFoundItem.STATUS_OPEN),
        LostFoundItem("아이폰 충전기", "학생식당", LostFoundItem.TYPE_FOUND, LostFoundItem.STATUS_OPEN),
        LostFoundItem("우산", "정문", LostFoundItem.TYPE_FOUND, LostFoundItem.STATUS_COMPLETED),
        LostFoundItem("학생증", "강의동 3층", LostFoundItem.TYPE_LOST, LostFoundItem.STATUS_COMPLETED),
        LostFoundItem("무선 이어폰", "체육관", LostFoundItem.TYPE_FOUND, LostFoundItem.STATUS_OPEN)
    )
}

// ───────────────────────── Adapter ─────────────────────────
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

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

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
        val type = when (typeFilter) {
            1 -> LostFoundItem.TYPE_LOST
            2 -> LostFoundItem.TYPE_FOUND
            else -> null                        // 0 = 전체: 조건 없음
        }
        val status = when (statusFilter) {
            1 -> LostFoundItem.STATUS_OPEN
            2 -> LostFoundItem.STATUS_COMPLETED
            else -> null
        }
        val filtered = allitems.filter { item ->
            (type == null || item.type == type) && (status == null || item.status == status)
        }
        submitList(filtered)
    }

    private fun submitList(newItems: List<LostFoundItem>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }
}
