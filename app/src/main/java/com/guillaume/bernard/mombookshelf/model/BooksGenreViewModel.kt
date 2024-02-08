package com.guillaume.bernard.mombookshelf.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guillaume.bernard.mombookshelf.ui.MomsBookshelfScreen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BooksGenreViewModel(
    savedStateHandle: SavedStateHandle,
    repository: BooksRepository
) : ViewModel() {

    val id: String =
        checkNotNull(savedStateHandle[MomsBookshelfScreen.GenreDetail.arg]) // Should be a table with proper PK id, but it will do for the test i'm doing...

    val uiState: StateFlow<List<Book>> = repository.getBooksFromGenre(id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )
}
