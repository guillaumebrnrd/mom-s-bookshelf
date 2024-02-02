package com.guillaume.bernard.mombookshelf.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BookState())
    val uiState: StateFlow<BookState> = _uiState.asStateFlow()

    /**
     * Set the [title] of the book for this book's state
     */
    fun setTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(
                title = title
            )
        }
    }

    /**
     * Set the published [year] for this book's state
     */
    fun setPublished(year: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                published = year
            )
        }
    }

    /**
     * Set the [author] for this book's state
     */
    fun setAuthor(author: Author) {
        _uiState.update { currentState ->
            currentState.copy(
                author = author
            )
        }
    }

    /**
     * Set the [isbn] code for this book's state
     */
    fun setIsbn(isbn: String) {
        _uiState.update { currentState ->
            currentState.copy(
                isbn = isbn
            )
        }
    }

    /**
     * Set the [isbn] for this book's state
     */
    fun setGenre(genre: String) {
        _uiState.update { currentState ->
            currentState.copy(
                genre = genre
            )
        }
    }

    /**
     * Set the [description] for this book's state
     */
    fun setDescription(description: String) {
        _uiState.update { currentState ->
            currentState.copy(
                description = description
            )
        }
    }
}
