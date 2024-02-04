package com.guillaume.bernard.mombookshelf.model

data class BookState(
    var id: Long = 0L,
    var title: String = "",
    var published: Int = 0,
    var author: Author = emptyAuthor,
    var isbn: String = "",
    var genre: String = "",
    var description: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookState

        if (title != other.title) return false
        if (published != other.published) return false
        return author == other.author
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + published
        result = 31 * result + author.hashCode()
        return result
    }
}

data class Author(
    val firstName: String,
    val lastName: String,
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}

val emptyAuthor = Author("", "")