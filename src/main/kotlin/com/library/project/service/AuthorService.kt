package com.library.project.service

import com.library.project.data.Author
import com.library.project.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service("Author service")
class AuthorService {
    @Autowired
    lateinit var repository: AuthorRepository

    @Cacheable(value = ["Authors"])
    fun getAuthors(pageable: Pageable): Page<Author> = repository.findAll(pageable)

    @CacheEvict(value = ["Authors"], allEntries = true)
    fun insertAuthor(author: Author): Author = repository.save(author)
    fun deleteAuthor(author: String) = repository.deleteById(author)
}