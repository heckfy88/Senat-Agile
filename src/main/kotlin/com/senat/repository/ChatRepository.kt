package com.senat.repository

import com.senat.dto.ChatDto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ChatRepository: CrudRepository<ChatDto, Long> {
}