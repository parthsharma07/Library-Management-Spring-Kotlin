package com.library.project.controller

data class BookByNameRequest(var name: String) {
    constructor(): this(
        ""
    )
}