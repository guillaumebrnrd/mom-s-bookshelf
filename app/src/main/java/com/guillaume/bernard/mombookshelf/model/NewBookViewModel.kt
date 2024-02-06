package com.guillaume.bernard.mombookshelf.model

class NewBookViewModel(private val repository: BooksRepository) : BookViewModel() {

    /**
     * Save the book into DB
     */
    suspend fun saveBook() {
        bookState.id = repository.insertBook(bookState)
    }
}
