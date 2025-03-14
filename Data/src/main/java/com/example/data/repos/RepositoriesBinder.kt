package com.example.data.repos

import com.example.domain.repositories.NewsRepository
import com.example.domain.repositories.SearchRepository
import com.example.domain.repositories.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesBinder {

    @Binds
    abstract fun bindSourcesRepo(
        sourcesRepoImpl: SourcesRepoImpl
    ): SourcesRepository

    @Binds
    abstract fun bindNewsRepo(newsRepoImpl: NewsRepoImpl): NewsRepository

    @Binds
    abstract fun bindNewsRepoSearch(searchRepoImpl: SearchRepoImpl): SearchRepository
}