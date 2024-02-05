package com.guillaume.bernard.mombookshelf.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LastBookViewModel(
    repository: BooksRepository
) : ViewModel() {

    val uiState: StateFlow<Book> = repository.getLastBook()
        .map {
            it ?: Book.defaultBook
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Book()
        )
}
