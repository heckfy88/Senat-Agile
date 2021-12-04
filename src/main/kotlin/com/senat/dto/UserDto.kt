package com.senat.dto

import javax.persistence.*

@Entity
data class UserDto (
    @Id
    val userId: Long = 0,
    val name: String,

    @Embedded
    val permissions: PermissionDto
)