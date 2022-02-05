package com.library.project.controller

import com.library.project.data.Author
import com.library.project.data.Book
import com.library.project.data.BookDTO
import com.library.project.service.AuthorService
import com.library.project.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import kotlin.collections.ArrayList

@RestController
@RequestMapping("/books")
class BookController {
    @Autowired
    private lateinit var service: BookService
    @Autowired
    private lateinit var authorService: AuthorService

    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun getBooks(pageable: Pageable): Any{
        try {
            return service.getBooks(pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @PutMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
//        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
        consumes = ["multipart/form-data"] )
    fun insertBook(
        @RequestParam(value = "file") file: MultipartFile, @RequestParam(value = "pages") pages: Int,
        @RequestParam(value = "addedOn") addedOn: String, @RequestParam(value = "name") name: String,
        @RequestParam(value = "author") author: String, @RequestParam(value = "category") category: ArrayList<String>): Any {
        try {
            authorService.insertAuthor(Author(author))
            return service.insertBook(Book(0, pages, addedOn, name, author, category, file.bytes))
        }catch (e: Exception){
            return e.localizedMessage
        }

    }

    @DeleteMapping(
        value = ["/{name}"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun deleteNote(
        @PathVariable(name = "name") name: String ): Any {
        try {
            return service.deleteNameSimilarTo(name)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @PostMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = ["multipart/form-data"] )
    fun updateBook(
        @RequestParam(value = "file") file: MultipartFile, @RequestParam(value = "id") id: Int, @RequestParam(value = "pages") pages: Int,
        @RequestParam(value = "addedOn") addedOn: String, @RequestParam(value = "name") name: String,
        @RequestParam(value = "author") author: String, @RequestParam(value = "category") category: ArrayList<String>): Any {
        try {
            return service.updateBook(Book(id, pages, addedOn, name, author, category, file.bytes))
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/name_like"])
    fun getNameSimilarTo(
        @RequestBody payload: BookByNameRequest, pageable: Pageable
    ): Any{
        try {
            return service.getNameSimilarToo(payload.name, pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/author_like"])
    fun getAuthorSimilarTo(
        @RequestBody payload: BookByAuthorRequest, pageable: Pageable
    ): Any{
        try {
            return service.getAuthorSimilarToo(payload.author, pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/category_like"])
    fun getCategorySimilarTo(
        @RequestBody payload: BookByCategoryRequest
    ): Any{
        try {
            return service.getCategorySimilarTo(payload.category)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

}