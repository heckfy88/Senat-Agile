package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class DiscussionDto (
    @Id
    private val discussionId: Long,
    private val title: String,
    @OneToMany
    private val ideas: MutableList<IdeaDto> = mutableListOf(),
    private val startTimestamp: String,
    private val endTimestamp: String
)

