package com.senat.dto

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserDto (
    @Id
    val userId: String? = null,
    val name: String
)