package com.senat.repository

import com.senat.dto.IdeaDto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IdeaRepository: CrudRepository<IdeaDto, Long> {
    fun findAllByOrderByVotesDesc(): List<IdeaDto>
}