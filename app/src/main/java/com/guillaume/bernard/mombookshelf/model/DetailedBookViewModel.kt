package com.guillaume.bernard.mombookshelf.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guillaume.bernard.mombookshelf.ui.MomsBookshelfScreen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailedBookViewModel(
    savedStateHandle: SavedStateHandle,
    repository: BooksRepository
) : ViewModel() {

    private val id: Long = checkNotNull(savedStateHandle[MomsBookshelfScreen.BookDetail.arg])
    val uiState: StateFlow<Book> = repository.getBook(id)
        .map {
            it ?: Book.defaultBook
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = Book()
        )
}
