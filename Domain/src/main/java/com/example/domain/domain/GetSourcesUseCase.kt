package com.example.domain.domain

import com.example.domain.model.Source
import com.example.domain.repositories.SourcesRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(val sourcesRepository: SourcesRepository) {

    suspend fun getSource(categoryId: String): List<Source> {
        return sourcesRepository.getSource(categoryId)
    }
}