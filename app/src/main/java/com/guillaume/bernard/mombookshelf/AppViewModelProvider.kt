package com.guillaume.bernard.mombookshelf

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.guillaume.bernard.mombookshelf.model.AllBooksViewModel
import com.guillaume.bernard.mombookshelf.model.BooksGenreViewModel
import com.guillaume.bernard.mombookshelf.model.DetailedBookViewModel
import com.guillaume.bernard.mombookshelf.model.EditBookViewModel
import com.guillaume.bernard.mombookshelf.model.LastBookViewModel
import com.guillaume.bernard.mombookshelf.model.NewBookViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // LastBookViewModel
        initializer {
            LastBookViewModel(mbApplication().repository)
        }
        // AllBooksViewModel
        initializer {
            AllBooksViewModel(mbApplication().repository)
        }
        // DetailBookViewModel
        initializer {
            DetailedBookViewModel(
                this.createSavedStateHandle(),
                mbApplication().repository
            )
        }
        // NewBookViewModel
        initializer {
            NewBookViewModel(mbApplication().repository)
        }
        // EditBookViewModel
        initializer {
            EditBookViewModel(
                this.createSavedStateHandle(),
                mbApplication().repository
            )
        }
        // BooksGenreViewModel
        initializer {
            BooksGenreViewModel(
                this.createSavedStateHandle(),
                mbApplication().repository
            )
        }
    }
}


/**
 * Extension function to queries for [Application] object and returns an instance of
 * [MomsBookShelfApplication].
 */
fun CreationExtras.mbApplication(): MomsBookShelfApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MomsBookShelfApplication)