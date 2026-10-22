package com.example.practicefortestapplication.p09contact

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/** 문제 9. 연락처 데이터. */
data class Contact(val id: Int, val name: String, val phone: String)

/**
 * 문제 9. SQLiteOpenHelper. DB 파일 경로/버전 관리를 담당.
 *  - onCreate: DB 파일이 처음 만들어질 때 테이블 생성
 *  - onUpgrade: DB_VERSION 이 올라갈 때 기존 테이블 drop 후 재생성
 */
class DBHelper(context: Context?) : SQLiteOpenHelper(context, DB_FILE, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // TODO: 9번 문제 - ContactContract.SQL_CREATE_TABLE 을 execSQL 로 실행
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // TODO: 9번 문제 - SQL_DROP_TABLE 실행 후 onCreate(db) 재호출
    }

    companion object {
        const val DB_FILE = "contact.db"
        const val DB_VERSION = 1
    }
}
