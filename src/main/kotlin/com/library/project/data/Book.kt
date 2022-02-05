package com.library.project.data

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*
@Entity
@Table(name = "book")
data class Book(
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "int")
    var isbn_no: Int = 0,
    var pages: Int,
    var added_on: String,
    var name: String = "",
    var author: String = "",
    var category: ArrayList<String>,
    @Lob
    var pdf: ByteArray
) : Serializable {
    constructor() : this(
        0, 0, "", "", "", ArrayList(), ByteArray(Int.SIZE_BYTES)
    )
}