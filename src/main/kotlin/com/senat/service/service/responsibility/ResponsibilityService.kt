package com.senat.service.service.responsibility

import com.senat.dto.IdeaDto
import com.senat.repository.IdeaRepository
import com.senat.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ResponsibilityService(
    private val ideaRepository: IdeaRepository,
    private val userRepository: UserRepository
) {
    fun setResponsible(commandParameters: List<String>): IdeaDto {
        val ideaId = commandParameters[0].toLong()
        val userId = commandParameters[1].toLong()
        val user = userRepository.findById(userId).get()
        val idea = ideaRepository.findById(ideaId).get()
        idea.responsible = user
        return ideaRepository.save(idea)
    }
}