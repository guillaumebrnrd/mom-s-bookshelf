package com.guillaume.bernard.mombookshelf.model

data class Book(
    var title: String,
    var published: Int,
    var author: Author,
    var isbn: String,
    var genre: String = "",
    var description: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

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
)