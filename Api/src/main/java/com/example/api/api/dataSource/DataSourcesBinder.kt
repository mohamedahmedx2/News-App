package com.example.api.api.dataSource

import com.example.data.dataSource.news.NewsOnlineDataSource
import com.example.data.dataSource.search.SearchOnlineDataSource
import com.example.data.dataSource.source.SourcesOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsOnlineDataSourcesBinder {
    @Binds
    abstract fun bindNewsOnlineDataSource(newsOnlineDataSourcesImpl: NewsOnlineDataSourcesImpl): NewsOnlineDataSource

    @Binds
    abstract fun bindSourcesOnlineDataSource(sourcesOnlineDataSourceImpl: SourcesOnlineDataSourceImpl): SourcesOnlineDataSource

    @Binds
    abstract fun bindSearchOnlineDataSource(searchOnlineDataSourceImpl: SearchOnlineDataSourceImpl): SearchOnlineDataSource

}