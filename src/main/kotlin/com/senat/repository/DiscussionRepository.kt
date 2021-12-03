package com.senat.repository

import com.senat.dto.DiscussionDto
import org.springframework.data.repository.CrudRepository

interface DiscussionRepository: CrudRepository<DiscussionDto, Long> {
    fun findByDate(date: String): DiscussionDto
}