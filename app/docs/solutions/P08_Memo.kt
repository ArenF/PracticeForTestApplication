/*
 * 문제 8 정답 — Room 메모. DB 작업은 thread{}, 화면은 Handler(mainLooper).post{}.
 * RoomDB.kt(Entity/Dao/Database)는 스켈레톤에 이미 완성되어 있다.
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p08memo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.practicefortestapplication.databinding.P08ActivityMemoBinding
import com.example.practicefortestapplication.databinding.P08ItemMemoBinding
import kotlin.concurrent.thread

// ───────── Activity ─────────
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
            val now = System.currentTimeMillis()
            val memo = Memo(
                id = now,
                title = title,
                content = binding.etContent.text.toString().trim(),
                createdAt = now
            )
            adapter.addItem(memo, dao)
            binding.etTitle.setText("")
            binding.etContent.setText("")
        }
    }
}

// ───────── ViewHolder + Adapter (thread + Handler) ─────────
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

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun loadItems(dao: MemoDao) {
        thread {
            val list = dao.getAll()                       // DB: 작업 스레드
            Handler(Looper.getMainLooper()).post {        // 화면: 메인 스레드
                items = list.toMutableList()
                notifyDataSetChanged()
            }
        }
    }

    fun addItem(memo: Memo, dao: MemoDao) {
        thread {
            dao.insert(memo)
            Handler(Looper.getMainLooper()).post {
                items.add(0, memo)                        // 최신이 맨 위
                notifyDataSetChanged()
            }
        }
    }

    fun deleteItem(memo: Memo, dao: MemoDao) {
        thread {
            dao.delete(memo)
            Handler(Looper.getMainLooper()).post {
                items.remove(memo)
                notifyDataSetChanged()
            }
        }
    }
}
