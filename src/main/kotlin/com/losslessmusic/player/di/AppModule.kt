package com.losslessmusic.player.di

import android.content.Context
import com.losslessmusic.player.data.database.AppDatabase
import com.losslessmusic.player.data.repository.MusicRepositoryImpl
import com.losslessmusic.player.data.repository.PlaylistRepositoryImpl
import com.losslessmusic.player.domain.repository.MusicRepository
import com.losslessmusic.player.domain.repository.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideMusicRepository(
        database: AppDatabase,
        @ApplicationContext context: Context
    ): MusicRepository = MusicRepositoryImpl(database, context)

    @Provides
    @Singleton
    fun providePlaylistRepository(
        database: AppDatabase
    ): PlaylistRepository = PlaylistRepositoryImpl(database)
}
