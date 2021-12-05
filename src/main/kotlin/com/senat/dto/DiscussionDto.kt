package com.senat.dto

import javax.persistence.*

@Entity
data class DiscussionDto (
    @Id
    @GeneratedValue
    val id: Long = 0,
    val title: String,
    val chatId: Long,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val ideas: MutableList<IdeaDto> = mutableListOf(),
    val date: String? = null
) {
    fun addIdea(ideaDto: IdeaDto) {
        ideaDto.discussion = this
        this.ideas.add(ideaDto)
    }
}

