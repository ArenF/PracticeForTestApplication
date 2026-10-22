/*
 * 문제 9 정답 — SQLiteOpenHelper + Contract. (참고 답안: 빌드에 포함되지 않음)
 *  - 쓰기 writableDatabase + ContentValues, 읽기 readableDatabase + rawQuery + Cursor
 *  - DBHelper.onCreate/onUpgrade 채우기
 */
package com.example.practicefortestapplication.p09contact

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicefortestapplication.databinding.P09ActivityContactBinding

// ───────── DBHelper (onCreate/onUpgrade) ─────────
class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_FILE, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(ContactContract.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(ContactContract.SQL_DROP_TABLE)
        onCreate(db)
    }

    companion object {
        const val DB_FILE = "contact.db"
        const val DB_VERSION = 1
    }
}

// ───────── Activity (insert / load(Cursor) / delete / close) ─────────
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
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put(ContactContract.COL_ID, (System.currentTimeMillis() % 1000000).toInt())
            values.put(ContactContract.COL_NAME, binding.etName.text.toString())
            values.put(ContactContract.COL_PHONE, binding.etPhone.text.toString())
            db.insert(ContactContract.TABLE_NAME, null, values)
            loadContacts()
        }
    }

    private fun loadContacts() {
        contacts.clear()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(ContactContract.SQL_LOAD, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)          // 컬럼 순서: ID, NAME, PHONE (0부터)
            val name = cursor.getString(1)
            val phone = cursor.getString(2)
            contacts.add(Contact(id, name, phone))
        }
        cursor.close()
        adapter.notifyDataSetChanged()
    }

    private fun deleteContact(contact: Contact) {
        val db = dbHelper.writableDatabase
        db.delete(
            ContactContract.TABLE_NAME,
            "${ContactContract.COL_ID} = ?",
            arrayOf(contact.id.toString())
        )
        loadContacts()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
