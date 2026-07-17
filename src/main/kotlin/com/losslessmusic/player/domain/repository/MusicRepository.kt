package com.losslessmusic.player.domain.repository

import com.losslessmusic.player.data.entity.MusicEntity
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getAllMusic(): Flow<List<MusicEntity>>
    fun searchMusic(query: String): Flow<List<MusicEntity>>
    fun getMusicByArtist(artist: String): Flow<List<MusicEntity>>
    fun getMusicByAlbum(album: String): Flow<List<MusicEntity>>
    suspend fun insertMusic(music: MusicEntity)
    suspend fun insertMultipleMusic(musicList: List<MusicEntity>)
    suspend fun updateMusic(music: MusicEntity)
    suspend fun deleteMusic(music: MusicEntity)
    suspend fun scanLocalMusic(): List<MusicEntity>
}
