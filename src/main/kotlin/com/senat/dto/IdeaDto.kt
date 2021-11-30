package com.senat.dto

data class IdeaDto (
    private val ideaId: Long,
    private val message: String,
    private var votes: Int = 0,
    private val sender: UserDto,
    private var responsible: UserDto? = null
)
