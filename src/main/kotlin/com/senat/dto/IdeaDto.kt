package com.senat.dto

import javax.persistence.*

@Entity
data class IdeaDto (
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val ideaId: Long = 0,

//    val discussionId: Long,

    @ManyToOne
    val discussion: DiscussionDto,

    val message: String, //TODO Отрефакторить
    var votes: Int = 0,
    @OneToOne
    val sender: UserDto,
    @ManyToOne
    var responsible: UserDto? = null,
    @OneToMany(fetch = FetchType.EAGER)
    val votedUsers: MutableList<UserDto> = mutableListOf()
)