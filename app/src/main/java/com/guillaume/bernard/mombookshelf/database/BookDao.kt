package com.guillaume.bernard.mombookshelf.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.guillaume.bernard.mombookshelf.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book): Long

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBook(id: Long): Flow<Book>

    @Query("SELECT * FROM book ORDER BY id DESC LIMIT 1")
    fun getLastBook(): Flow<Book> // auto-increment : higher id is last record

    @Query("SELECT * FROM book ORDER BY id DESC")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE genre = :genre ORDER BY id DESC")
    fun getBooksFromGenre(genre: String): Flow<List<Book>>
}