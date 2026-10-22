package com.example.practicefortestapplication.p09contact

/**
 * 문제 9. 테이블 이름/컬럼/SQL 을 한 곳에 모으는 Contract 객체.
 * 이름이 바뀌어도 여기만 고치면 된다.
 */
object ContactContract {
    const val TABLE_NAME = "CONTACT_T"
    const val COL_ID = "ID"
    const val COL_NAME = "NAME"
    const val COL_PHONE = "PHONE"

    const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "$COL_ID INTEGER NOT NULL PRIMARY KEY, " +
            "$COL_NAME TEXT NOT NULL, " +
            "$COL_PHONE TEXT)"
    const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    const val SQL_LOAD = "SELECT * FROM $TABLE_NAME"
}
