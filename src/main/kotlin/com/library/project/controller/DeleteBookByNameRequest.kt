package com.library.project.controller

data class DeleteBookByNameRequest(var name: String) {
    constructor(): this(
        ""
    )
}