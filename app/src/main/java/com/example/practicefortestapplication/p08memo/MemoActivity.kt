package com.example.practicefortestapplication.p08memo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.practicefortestapplication.databinding.P08ActivityMemoBinding

/**
 * 문제 8. Room 으로 메모 저장. 껐다 켜도 남아야 한다.
 *
 * 흐름: Room.databaseBuilder → memoDao() → adapter.loadItems(dao) → 저장/삭제.
 */
class MemoActivity : AppCompatActivity() {

    private lateinit var binding: P08ActivityMemoBinding
    private lateinit var adapter: MemoAdapter
    private lateinit var database: MemoDatabase
    private lateinit var dao: MemoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P08ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext, MemoDatabase::class.java, "memo_db").build()
        dao = database.memoDao()

        adapter = MemoAdapter { memo -> adapter.deleteItem(memo, dao) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.loadItems(dao)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // TODO: 8번 문제 - System.currentTimeMillis() 로 id/createdAt 을 만든 Memo 를 구성하고
            //  adapter.addItem(memo, dao) 호출, 그 뒤 etTitle/etContent 를 비운다.
        }
    }
}
