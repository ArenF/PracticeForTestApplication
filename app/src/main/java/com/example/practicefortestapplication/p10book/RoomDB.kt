package com.example.practicefortestapplication.p10book

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
 * 문제 10. 독서 기록 Room 3요소.
 * 빌드를 위해 완성되어 있다. 어노테이션 작성을 연습하려면 본문을 지우고 다시 채운다.
 */
@Entity(tableName = "BOOK_T")
data class BookRecord(
    @PrimaryKey
    var id: Long = 0L,
    var title: String = "",
    var author: String = "",
    var status: String = STATUS_READING,
    var rating: Float = 0f,
    var memo: String = "",
    var ownerId: String = "",
    var createdAt: Long = 0L
) {
    companion object {
        const val STATUS_READING = "READING"
        const val STATUS_DONE = "DONE"
    }
}

@Dao
interface BookDao {
    @Query("SELECT * FROM BOOK_T ORDER BY createdAt DESC")
    fun getAll(): List<BookRecord>

    @Query("SELECT * FROM BOOK_T WHERE id = :bookId")
    fun findById(bookId: Long): BookRecord?

    @Insert
    fun insert(item: BookRecord)

    @Update
    fun update(item: BookRecord)

    @Delete
    fun delete(item: BookRecord)
}

@Database(entities = [BookRecord::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
