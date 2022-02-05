package com.library.project.controller

data class BookByCategoryRequest(var category: String) {
    constructor(): this(
        ""
    )
}