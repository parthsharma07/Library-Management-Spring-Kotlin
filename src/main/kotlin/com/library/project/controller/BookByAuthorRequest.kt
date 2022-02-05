package com.library.project.controller

data class BookByAuthorRequest(var author: String) {
    constructor(): this(
        ""
    )
}