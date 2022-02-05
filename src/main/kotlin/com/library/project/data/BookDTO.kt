package com.library.project.data

import java.io.Serializable

class BookDTO(var pages: Int, var added_on: String, var name: String, var author: String, var category: ArrayList<String>) : Serializable {

    var isbn_no: Int = 0
    constructor(book: Book) : this(
        book.pages,
        book.added_on,
        book.name,
        book.author,
        book.category
    ) {
        isbn_no = book.isbn_no
    }
}