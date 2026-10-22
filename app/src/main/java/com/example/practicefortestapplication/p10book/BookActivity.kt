package com.example.practicefortestapplication.p10book

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.practicefortestapplication.databinding.P10ActivityMainBinding

/**
 * 문제 10. 통합 모의: 독서 기록 앱
 *
 * 요구사항
 *  1) 시작 시 최신순 표시
 *  2) btnAdd → 등록 다이얼로그(빈 폼)
 *  3) 항목 클릭 → 수정 다이얼로그(기존 값). 작성자가 현재 사용자가 아니면 Toast 후 안 띄움
 *  4) 항목 롱클릭 → 삭제
 *  5) 상태 스피너로 필터, 수정으로 상태가 바뀌면 필터 결과에도 즉시 반영
 *  6) 제목 비면 Toast + 다이얼로그 유지
 *  7) 옵션 메뉴 "로그아웃" → finish()
 *  8) tvOwner: 내 글/작성자 표시
 *
 * 등록과 수정은 showBookFormDialog(existingItem: BookRecord?) 하나로 처리한다 (null 이면 등록).
 */
class BookActivity : AppCompatActivity() {

    private lateinit var binding: P10ActivityMainBinding
    private lateinit var adapter: BookAdapter
    private var currentUser = "student1"

    private lateinit var database: BookDatabase
    private lateinit var dao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P10ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = Room.databaseBuilder(applicationContext, BookDatabase::class.java, "book_db").build()
        dao = database.bookDao()

        adapter = BookAdapter(currentUser,
            { item -> showBookFormDialog(item) },          // 클릭: 수정
            { item -> adapter.deleteItem(item, dao) })      // 롱클릭: 삭제
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.loadItems(dao)

        binding.btnAdd.setOnClickListener { showBookFormDialog(null) }

        // 스피너 필터
        val statuses = arrayOf("전체", "읽는 중", "완독")
        binding.spinnerStatusFilter.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        // TODO: 10번 문제 - spinnerStatusFilter.onItemSelectedListener 에 OnItemSelectedListener 등록,
        //  onItemSelected 에서 adapter.setFilter(position), onNothingSelected 도 재정의
    }

    /** 등록(existingItem == null)과 수정(existingItem != null)을 하나의 함수로 처리. */
    private fun showBookFormDialog(existingItem: BookRecord?) {
        // TODO: 10번 문제
        //  1) 수정인데 작성자(ownerId)가 currentUser 가 아니면 Toast "작성자만 수정 가능" 후 return
        //  2) DialogBookFormBinding(= P10DialogBookFormBinding) inflate, tvFormTitle "등록"/"수정"
        //  3) 수정이면 기존 값으로 폼 채우기(제목/저자/메모/별점/상태 라디오)
        //  4) AlertDialog.Builder(this).setView(root).create()
        //  5) btnCancel → dismiss, btnSave → saveFromDialog(...)
        //  6) dialog.show()
    }

    private fun saveFromDialog(/* dialogBinding, existingItem, dialog */) {
        // TODO: 10번 문제
        //  - 제목 공백이면 Toast "제목을 입력하세요" 후 return (dismiss 하지 않음)
        //  - 등록: 새 BookRecord(id/createdAt = now, ownerId = currentUser) 만들어 adapter.addItem
        //  - 수정: existingItem 의 필드를 바꿔 adapter.updateItem
        //  - 성공 시 dialog.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // TODO: 10번 문제 - menuInflater.inflate(R.menu.p10_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO: 10번 문제 - R.id.menu_logout 클릭 시 finish()
        return super.onOptionsItemSelected(item)
    }
}
