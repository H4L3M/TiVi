package com.nokhbativi.di

import com.nokhbativi.database.TiViDatabase
import com.nokhbativi.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDao(tiViDatabase: TiViDatabase) = tiViDatabase.dao

    @Singleton
    @Provides
    fun provideRepository(database: TiViDatabase) = DataRepository(database = database)

}