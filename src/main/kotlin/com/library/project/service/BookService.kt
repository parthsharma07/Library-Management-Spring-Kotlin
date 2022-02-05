package com.library.project.service

import com.library.project.data.Book
import com.library.project.data.BookDTO
import com.library.project.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service("Book service")
class BookService {
    val logger = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var repository: BookRepository

    @Cacheable(value = ["Books"])
    fun getBooks(pageable: Pageable): Page<Book> = repository.findAll(pageable)

    @CacheEvict(value = ["Books"], allEntries = true)
    fun insertBook(book: Book): Book = repository.save(book)

    @CacheEvict(value = ["Books"], allEntries = true)
    fun updateBook(book: Book): Book = repository.save(book)

    @Cacheable(value = ["Books"])
    fun getNameSimilarToo(name: String, pageable: Pageable): Page<BookDTO> {
        logger.info("database called")
        return repository.findNameSimilarTo(name, pageable).map { it -> BookDTO(it) }
    }

    @Cacheable(value = ["Books"])
    fun getAuthorSimilarToo(author: String, pageable: Pageable): Page<BookDTO> {
        return repository.findAuthorSimilarTo(author, pageable).map { it -> BookDTO(it) }
    }

    @CacheEvict(value = ["Books"], allEntries = true)
    fun deleteNameSimilarTo(name: String) {
        repository.deleteNameSimilarTo(name)
    }

    @Cacheable(value = ["Books"])
    fun getCategorySimilarTo(category: String): Iterable<BookDTO>{
        return repository.findAll().filter { it.category.contains(category) }.map { it -> BookDTO(it) }
    }

}