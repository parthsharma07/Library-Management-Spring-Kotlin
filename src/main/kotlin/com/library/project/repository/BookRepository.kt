package com.library.project.repository

import com.library.project.data.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import javax.transaction.Transactional

interface BookRepository : PagingAndSortingRepository<Book, String>{
    @Query("from Book b where b.name like ?1")
    fun findNameSimilarTo(name: String, pageable: Pageable): Page<Book>

    @Query("from Book b where b.author like ?1")
    fun findAuthorSimilarTo(author: String, pageable: Pageable): Page<Book>

    @Transactional
    @Modifying
    @Query("delete from Book where name like ?1")
    fun deleteNameSimilarTo(name: String)

}