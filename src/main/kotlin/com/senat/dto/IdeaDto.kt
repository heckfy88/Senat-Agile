package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class IdeaDto (
    @Id
    val ideaId: Long = 0,
    val message: String,
    var votes: Int = 0,
    @ManyToOne
    val sender: UserDto,
    @ManyToOne
    var responsible: UserDto? = null
)
