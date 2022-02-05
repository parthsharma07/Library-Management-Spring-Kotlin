package com.library.project.repository

import com.library.project.data.Author
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface AuthorRepository : PagingAndSortingRepository<Author, String>{



}