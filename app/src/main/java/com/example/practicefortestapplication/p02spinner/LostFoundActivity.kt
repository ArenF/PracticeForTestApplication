package com.example.practicefortestapplication.p02spinner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicefortestapplication.databinding.P02ActivityLostfoundBinding

/**
 * 문제 2. 스피너 두 개로 목록 필터링
 *
 * 요구사항
 *  1) spinnerTypeFilter 는 코드에서 배열("전체","분실물","습득물") + ArrayAdapter 로 채운다.
 *  2) spinnerStatusFilter 는 xml android:entries 로 이미 채워져 있다.
 *  3) 두 스피너 중 무엇을 바꾸든 두 조건을 모두 반영: 리스너 하나를 만들어 두 스피너에 공유.
 *  4) 처음에는 "전체, 전체".
 */
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

        // TODO: 2번 문제 - 종류 배열 + ArrayAdapter(this, android.R.layout.simple_spinner_item, types) 생성 후
        //  binding.spinnerTypeFilter.adapter 에 대입

        // TODO: 2번 문제 - AdapterView.OnItemSelectedListener 익명 객체 1개 작성
        //  onItemSelected 안에서 adapter.setFilters(두 스피너의 selectedItemPosition) 호출,
        //  onNothingSelected 도 반드시 재정의.
        //  만든 리스너를 두 스피너의 onItemSelectedListener 에 모두 대입.
    }

    private fun sampleItems(): List<LostFoundItem> = listOf(
        LostFoundItem("검정 지갑", "도서관 2층", LostFoundItem.TYPE_LOST, LostFoundItem.STATUS_OPEN),
        LostFoundItem("아이폰 충전기", "학생식당", LostFoundItem.TYPE_FOUND, LostFoundItem.STATUS_OPEN),
        LostFoundItem("우산", "정문", LostFoundItem.TYPE_FOUND, LostFoundItem.STATUS_COMPLETED),
        LostFoundItem("학생증", "강의동 3층", LostFoundItem.TYPE_LOST, LostFoundItem.STATUS_COMPLETED),
        LostFoundItem("무선 이어폰", "체육관", LostFoundItem.TYPE_FOUND, LostFoundItem.STATUS_OPEN)
    )
}
