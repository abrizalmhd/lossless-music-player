package com.losslessmusic.player.domain.repository

import com.losslessmusic.player.data.entity.PlaylistEntity
import com.losslessmusic.player.data.entity.PlaylistItemEntity
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>
    suspend fun createPlaylist(name: String, description: String? = null): Long
    suspend fun updatePlaylist(playlist: PlaylistEntity)
    suspend fun deletePlaylist(playlist: PlaylistEntity)
    suspend fun addMusicToPlaylist(playlistId: Long, musicId: Long)
    suspend fun removeMusicFromPlaylist(playlistId: Long, musicId: Long)
    fun getPlaylistItems(playlistId: Long): Flow<List<PlaylistItemEntity>>
}
