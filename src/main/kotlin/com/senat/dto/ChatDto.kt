package com.senat.dto

import javax.persistence.*

@Entity
class ChatDto (
    @Id
    val chat_id: Long,
    var discussion: Boolean = true,
    var idea: Boolean = false,
    var vote: Boolean = false,
    var responsible: Boolean = false
)