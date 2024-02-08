package com.guillaume.bernard.mombookshelf.model

import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBook(id: Long): Flow<Book?>

    fun getLastBook(): Flow<Book?>

    fun getAllBooks(): Flow<List<Book>>

    fun getBooksFromGenre(genre: String): Flow<List<Book>>

    suspend fun insertBook(book: Book): Long
    suspend fun deleteBook(book: Book)
    suspend fun updateBook(book: Book)
}