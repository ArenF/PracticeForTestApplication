package com.example.practicefortestapplication.p08memo

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

/**
 * 문제 8. Room 3요소 (Entity / Dao / Database)
 *
 * 이 파일은 빌드(KSP 코드 생성)를 위해 완성되어 있다.
 * Entity/Dao/Database 작성 자체를 연습하려면 본문을 지우고 다시 채워 보면 된다.
 */
@Entity(tableName = "MEMO_T")
data class Memo(
    @PrimaryKey
    var id: Long = 0L,
    var title: String = "",
    var content: String = "",
    var createdAt: Long = 0L
)

@Dao
interface MemoDao {
    @Query("SELECT * FROM MEMO_T ORDER BY createdAt DESC")
    fun getAll(): List<Memo>

    @Query("SELECT * FROM MEMO_T WHERE id = :memoId")
    fun findById(memoId: Long): Memo?

    @Insert
    fun insert(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Delete
    fun delete(memo: Memo)
}

@Database(entities = [Memo::class], version = 1, exportSchema = false)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
