package com.senat.repository

import com.senat.dto.DiscussionDto
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscussionRepository: CrudRepository<DiscussionDto, Long> {
    fun findByDate(date: String): DiscussionDto
    fun findFirstByChatIdOrderByDiscussionIdDesc(chatId: Long): DiscussionDto
}