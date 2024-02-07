package com.guillaume.bernard.mombookshelf.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AllBooksViewModel(
    repository: BooksRepository
) : ViewModel() {

    private var searchQuery by mutableStateOf("")

    private val uiState: Flow<List<Book>> = repository.getAllBooks()
        .map { it.ifEmpty { listOf(Book.defaultBook) } } // At least the default book


    val searchResults: StateFlow<List<Book>> = snapshotFlow { searchQuery }
        .combine(uiState) { searchQuery, uiState ->
            when {
                searchQuery.isNotEmpty() -> uiState.filter {
                    it.title.contains(searchQuery, false) || it.author.contains(searchQuery, false)
                }
                else -> uiState
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )

    fun applyFilter(filter: String) {
        searchQuery = filter
    }
}
