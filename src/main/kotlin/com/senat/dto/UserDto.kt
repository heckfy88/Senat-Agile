package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserDto (
    @Id
    private val userId: Long,
    private val name: String
)