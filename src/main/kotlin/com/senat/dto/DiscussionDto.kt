package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class DiscussionDto (
    @Id
    val discussionId: Long,
    val title: String,
    @OneToMany
    val ideas: MutableList<IdeaDto> = mutableListOf(),
    val startTimestamp: String,
    val endTimestamp: String
)

