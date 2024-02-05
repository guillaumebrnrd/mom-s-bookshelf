package com.guillaume.bernard.mombookshelf.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AllBooksViewModel(
    repository: BooksRepository
) : ViewModel() {

    val uiState: StateFlow<List<Book>> = repository.getAllBooks()
        .map { it.ifEmpty { listOf(Book.defaultBook) } } // At least the default book
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )
}
