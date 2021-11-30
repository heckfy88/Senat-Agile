package com.senat.repository

import com.senat.dto.UserDto
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserDto, Long> {
}