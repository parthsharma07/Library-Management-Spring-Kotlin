package com.library.project.data

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*
@Entity
@Table(name = "author")
data class Author(
    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(36)")
//    var id: Int = 0,
    var author: String = ""
) : Serializable {
    constructor() : this(
        ""
    )
}