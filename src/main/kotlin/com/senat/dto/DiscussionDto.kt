package com.senat.dto

import javax.persistence.*

@Entity
data class DiscussionDto (
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val discussionId: Long = 0,
    val title: String,
    @OneToMany
    val ideas: MutableList<IdeaDto> = mutableListOf(),

    val discussionStarted: Boolean
)

