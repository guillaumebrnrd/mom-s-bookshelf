package com.guillaume.bernard.mombookshelf.model

import com.guillaume.bernard.mombookshelf.database.BookDao
import kotlinx.coroutines.flow.Flow

class BooksRepositoryImpl(private val bookDao: BookDao) : BooksRepository {
    override fun getBook(id: Long): Flow<Book?> = bookDao.getBook(id)

    override fun getLastBook(): Flow<Book?> = bookDao.getLastBook()

    override fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks()

    override fun getBooksFromGenre(genre: String): Flow<List<Book>> = bookDao.getBooksFromGenre(genre)

    override suspend fun insertBook(book: Book): Long = bookDao.insert(book)

    override suspend fun deleteBook(book: Book) = bookDao.delete(book)

    override suspend fun updateBook(book: Book) = bookDao.update(book)

}