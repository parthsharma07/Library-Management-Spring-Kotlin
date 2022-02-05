package com.library.project.controller

import com.library.project.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/authors")
class AuthorController {
    @Autowired
    private lateinit var service: AuthorService

    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun getAuthors(pageable: Pageable): Any{
        try {
            return service.getAuthors(pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @DeleteMapping(
        value = ["/{id}"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun deleteNote(
        @PathVariable(name = "id") id: String ) = service.deleteAuthor(id)

}