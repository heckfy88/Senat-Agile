package com.senat.dto

import javax.persistence.*

@Entity
data class IdeaDto (
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val ideaId: Long = 0,

    private val message: String,
    var votes: Int = 0,
    @OneToOne
    private val sender: UserDto,
    @ManyToOne
    private var responsible: UserDto? = null
)


