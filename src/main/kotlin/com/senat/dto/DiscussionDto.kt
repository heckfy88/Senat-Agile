package com.senat.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class DiscussionDto (
    @Id
    @GeneratedValue
    val discussionId: Long = 0,
    val title: String,
    val chatId: Long,
    var started: Byte = 1,
    @OneToMany
    val ideas: MutableList<IdeaDto> = mutableListOf(),
    val date: String? = null
)

