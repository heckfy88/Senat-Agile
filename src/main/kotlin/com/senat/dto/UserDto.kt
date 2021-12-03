package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserDto (
    @Id
    val userId: Long = 0,
    val name: String? = null
)