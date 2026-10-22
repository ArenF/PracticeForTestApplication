/*
 * 문제 10 정답 — 통합 독서 기록 앱.
 * 등록/수정은 showBookFormDialog(existingItem: BookRecord?) 하나로 (null=등록).
 * 수정 후에도 applyFilters() 를 호출해 상태 변경을 필터에 즉시 반영.
 * RoomDB.kt(Entity/Dao/Database)는 스켈레톤에 이미 완성되어 있다.
 * (참고 답안: 빌드에 포함되지 않음)
 */
package com.example.practicefortestapplication.p10book

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.practicefortestapplication.R
import com.example.practicefortestapplication.databinding.P10ActivityMainBinding
import com.example.practicefortestapplication.databinding.P10DialogBookFormBinding
import com.example.practicefortestapplication.databinding.P10ItemBookBinding
import kotlin.concurrent.thread

// ══════════════════════════ Activity ══════════════════════════
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

        val statuses = arrayOf("전체", "읽는 중", "완독")
        binding.spinnerStatusFilter.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        binding.spinnerStatusFilter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    adapter.setFilter(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    // 등록(null)과 수정을 하나의 함수로
    private fun showBookFormDialog(existingItem: BookRecord?) {
        if (existingItem != null && existingItem.ownerId != currentUser) {
            Toast.makeText(baseContext, "작성자만 수정 가능", Toast.LENGTH_LONG).show()
            return
        }

        val dialogBinding = P10DialogBookFormBinding.inflate(layoutInflater)
        dialogBinding.tvFormTitle.text = if (existingItem != null) "수정" else "등록"

        if (existingItem != null) {                        // 수정: 기존 값 채우기
            dialogBinding.etTitle.setText(existingItem.title)
            dialogBinding.etAuthor.setText(existingItem.author)
            dialogBinding.etMemo.setText(existingItem.memo)
            dialogBinding.ratingForm.rating = existingItem.rating
            if (existingItem.status == BookRecord.STATUS_DONE) dialogBinding.rbDone.isChecked = true
            else dialogBinding.rbReading.isChecked = true
        }

        val dialog = AlertDialog.Builder(this).setView(dialogBinding.root).create()
        dialogBinding.btnCancel.setOnClickListener { dialog.dismiss() }
        dialogBinding.btnSave.setOnClickListener { saveFromDialog(dialogBinding, existingItem, dialog) }
        dialog.show()
    }

    private fun saveFromDialog(
        dialogBinding: P10DialogBookFormBinding,
        existingItem: BookRecord?,
        dialog: AlertDialog
    ) {
        val title = dialogBinding.etTitle.text.toString().trim()
        if (title.isEmpty()) {
            Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
            return                                          // dismiss 하지 않음
        }
        val author = dialogBinding.etAuthor.text.toString().trim()
        val memo = dialogBinding.etMemo.text.toString().trim()
        val rating = dialogBinding.ratingForm.rating
        val status = if (dialogBinding.rbDone.isChecked) BookRecord.STATUS_DONE
                     else BookRecord.STATUS_READING

        if (existingItem == null) {                         // 등록
            val now = System.currentTimeMillis()
            val item = BookRecord(
                id = now, title = title, author = author, status = status,
                rating = rating, memo = memo, ownerId = currentUser, createdAt = now
            )
            adapter.addItem(item, dao)
        } else {                                            // 수정: 같은 객체 값 변경 후 update
            existingItem.title = title
            existingItem.author = author
            existingItem.status = status
            existingItem.rating = rating
            existingItem.memo = memo
            adapter.updateItem(existingItem, dao)
        }
        dialog.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.p10_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

// ══════════════════════════ Adapter ══════════════════════════
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
            tvOwner.text = if (item.ownerId == currentUser) "내가 쓴 글"
                           else "작성자: ${item.ownerId}"

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
    private var statusFilter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = P10ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookViewHolder(binding, currentUser, onItemClick, onItemLongClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    private fun submitList(newItems: List<BookRecord>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun loadItems(dao: BookDao) {
        thread {
            val list = dao.getAll()
            Handler(Looper.getMainLooper()).post {
                allitems = list.toMutableList()
                applyFilters()
            }
        }
    }

    fun addItem(item: BookRecord, dao: BookDao) {
        thread {
            dao.insert(item)
            Handler(Looper.getMainLooper()).post {
                allitems.add(0, item)
                applyFilters()
            }
        }
    }

    fun updateItem(item: BookRecord, dao: BookDao) {
        thread {
            dao.update(item)
            Handler(Looper.getMainLooper()).post {
                applyFilters()                     // 상태 변경을 필터에 즉시 반영
            }
        }
    }

    fun deleteItem(item: BookRecord, dao: BookDao) {
        thread {
            dao.delete(item)
            Handler(Looper.getMainLooper()).post {
                allitems.remove(item)
                applyFilters()
            }
        }
    }

    fun setFilter(statusFilter: Int) {
        this.statusFilter = statusFilter
        applyFilters()
    }

    private fun applyFilters() {
        val status = when (statusFilter) {
            1 -> BookRecord.STATUS_READING
            2 -> BookRecord.STATUS_DONE
            else -> null
        }
        submitList(allitems.filter { status == null || it.status == status })
    }
}
