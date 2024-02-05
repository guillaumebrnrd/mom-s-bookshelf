package com.guillaume.bernard.mombookshelf

import android.app.Application
import com.guillaume.bernard.mombookshelf.database.AppDatabase
import com.guillaume.bernard.mombookshelf.model.BooksRepository
import com.guillaume.bernard.mombookshelf.model.BooksRepositoryImpl

class MomsBookShelfApplication : Application() {
    val repository: BooksRepository by lazy {
        BooksRepositoryImpl(AppDatabase.getDatabase(this).bookDao())
    }
}
