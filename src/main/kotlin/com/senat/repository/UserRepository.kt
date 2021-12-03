package com.senat.repository

import com.senat.dto.UserDto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<UserDto, Long> {
    fun findByName(name: String) : UserDto?
}