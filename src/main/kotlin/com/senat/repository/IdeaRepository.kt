package com.senat.repository

import com.senat.dto.IdeaDto
import org.springframework.data.repository.CrudRepository

interface IdeaRepository: CrudRepository<IdeaDto, Long> {
    fun findAllByOrderByVotesDesc(): List<IdeaDto>
}