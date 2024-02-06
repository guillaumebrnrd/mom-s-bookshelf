package com.guillaume.bernard.mombookshelf.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class EditBookViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: BooksRepository
) : BookViewModel() {

    private val id: Long = checkNotNull(savedStateHandle["bookId"])
    init {
        viewModelScope.launch {
            bookState = repository.getBook(id).filterNotNull().firstOrNull() ?: Book.defaultBook
        }
    }
    /**
     * Save the book into DB
     */
    suspend fun updateBook() {
        repository.updateBook(bookState)
    }
}
