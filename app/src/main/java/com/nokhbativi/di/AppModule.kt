package com.nokhbativi.di

import com.nokhbativi.data.database.AppDatabase
import com.nokhbativi.data.repository.DataRepository
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
    fun provideDao(tiViDatabase: AppDatabase) = tiViDatabase.appDao

    @Singleton
    @Provides
    fun provideRepository(database: AppDatabase) = DataRepository(database = database)

}