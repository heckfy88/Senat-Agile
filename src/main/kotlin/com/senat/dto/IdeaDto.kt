package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class IdeaDto (
    @Id
    private val ideaId: Long,
    private val message: String,
    private var votes: Int = 0,
    @ManyToOne
    private val sender: UserDto,
    @ManyToOne
    private var responsible: UserDto? = null
)
