package com.example.practicefortestapplication.p09contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicefortestapplication.databinding.P09ActivityContactBinding

/**
 * 문제 9. SQLiteOpenHelper 로 연락처 저장/조회/삭제.
 *  - 쓰기: writableDatabase + ContentValues
 *  - 읽기: readableDatabase + rawQuery + Cursor (while moveToNext)
 *  - 삭제: delete(table, "ID = ?", arrayOf(id))
 *  - 종료: onDestroy 에서 helper.close()
 */
class ContactActivity : AppCompatActivity() {

    private lateinit var binding: P09ActivityContactBinding
    private lateinit var dbHelper: DBHelper
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P09ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(baseContext)
        adapter = ContactAdapter(contacts) { contact -> deleteContact(contact) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        loadContacts()

        binding.btnAdd.setOnClickListener {
            // TODO: 9번 문제 - writableDatabase 획득, ContentValues 에 ID/NAME/PHONE put,
            //  db.insert(ContactContract.TABLE_NAME, null, values) 후 loadContacts() 호출
        }
    }

    private fun loadContacts() {
        contacts.clear()
        // TODO: 9번 문제 - readableDatabase.rawQuery(SQL_LOAD, null) 로 Cursor 획득,
        //  while (cursor.moveToNext()) 로 getInt(0)/getString(1)/getString(2) 읽어 contacts 에 추가,
        //  cursor.close() 후 adapter.notifyDataSetChanged()
    }

    private fun deleteContact(contact: Contact) {
        // TODO: 9번 문제 - writableDatabase.delete(TABLE_NAME, "ID = ?", arrayOf(id)) 후 loadContacts()
    }

    override fun onDestroy() {
        // TODO: 9번 문제 - dbHelper.close()
        super.onDestroy()
    }
}
