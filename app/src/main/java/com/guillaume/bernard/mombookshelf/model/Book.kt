package com.guillaume.bernard.mombookshelf.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String = "",
    var published: Int = 0,
    var author: String = "",
    var isbn: String = "",
    var genre: String = "",
    var description: String = "",
    var cover: String = ""
) {
    companion object {
        val defaultBook: Book = Book(
            id = -1L,
            title = "Le Petit Prince",
            published = 1943,
            author = "Antoine de St-Exupéry",
            isbn = "9782070612758",
            genre = "Conte, Jeunesse",
            description = "Le narrateur, un pilote qui est tombé en panne d'essence dans le Sahara, fait la connaissance d’un prince extraordinaire venant d’une autre planète.",
            cover = "https://media.senscritique.com/media/000005682599/0/le_petit_prince.jpg"
        )
    }
}
