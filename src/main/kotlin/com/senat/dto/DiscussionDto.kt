package com.senat.dto

data class DiscussionDto (
    private val discussionId: Long,
    private val title: String,
    private val ideas: MutableList<IdeaDto> = mutableListOf(),
    private val startTimestamp: String,
    private val endTimestamp: String
)

