package com.example.domain.repositories

import com.example.domain.model.Source

interface SourcesRepository {

    suspend fun getSource(categoryId: String): List<Source>
}