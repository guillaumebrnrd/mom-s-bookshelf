package com.guillaume.bernard.mombookshelf.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.Entity
import java.util.Calendar

@Entity(tableName = "book")
abstract class BookViewModel : ViewModel() {
    var bookState by mutableStateOf(Book())
        protected set

    /**
     * Set the new book's [title]
     */
    fun setTitle(title: String) {
        bookState = bookState.copy(title = title)
    }

    /**
     * Set the new book's [author]
     */
    fun setAuthor(author: String) {
        bookState = bookState.copy(author = author)
    }

    /**
     * Set the new book's [published] date
     */
    fun setPublished(published: Int) {
        bookState = bookState.copy(published = published)
    }

    /**
     * Set the new book's [description]
     */
    fun setDescription(description: String) {
        bookState = bookState.copy(description = description)
    }

    /**
     * Check validity of the book's data before inserting into DB
     */
    fun isInputValid(): Boolean {
        return bookState.title.length > 2 && bookState.author.length > 2
                && bookState.published in 1000..Calendar.getInstance().get(Calendar.YEAR)
    }
}
