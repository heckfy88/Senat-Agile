package com.senat.dto

import javax.persistence.*

@Entity
data class IdeaDto (
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(cascade = [CascadeType.REFRESH, CascadeType.PERSIST])
    @JoinColumn
    var discussion: DiscussionDto,
    val body: String, //TODO Отрефакторить
    var votes: Int = 0,
    @OneToOne
    val sender: UserDto,
    @ManyToOne
    var responsible: UserDto? = null,
    @OneToMany(fetch = FetchType.EAGER)
    val votedUsers: MutableList<UserDto> = mutableListOf()
)