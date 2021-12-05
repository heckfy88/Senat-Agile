package com.senat.repository

import com.senat.dto.UserDto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: CrudRepository<UserDto, Long> {
    fun findByUserId(id: String): Optional<UserDto>
    fun findByName(name: String): UserDto
}