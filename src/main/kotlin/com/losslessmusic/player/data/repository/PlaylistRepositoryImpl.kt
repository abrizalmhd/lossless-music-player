package com.losslessmusic.player.data.repository

import com.losslessmusic.player.data.database.AppDatabase
import com.losslessmusic.player.data.entity.PlaylistEntity
import com.losslessmusic.player.data.entity.PlaylistItemEntity
import com.losslessmusic.player.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow

class PlaylistRepositoryImpl(
    private val database: AppDatabase
) : PlaylistRepository {

    override fun getAllPlaylists(): Flow<List<PlaylistEntity>> =
        database.playlistDao().getAllPlaylists()

    override suspend fun createPlaylist(name: String, description: String?): Long {
        val playlist = PlaylistEntity(name = name, description = description)
        return database.playlistDao().insert(playlist)
    }

    override suspend fun updatePlaylist(playlist: PlaylistEntity) {
        database.playlistDao().update(playlist)
    }

    override suspend fun deletePlaylist(playlist: PlaylistEntity) {
        database.playlistDao().delete(playlist)
    }

    override suspend fun addMusicToPlaylist(playlistId: Long, musicId: Long) {
        val position = database.playlistItemDao().getPlaylistItems(playlistId)
            .let { /*TODO*/ 0 }
        val item = PlaylistItemEntity(playlistId, musicId, position)
        database.playlistItemDao().insert(item)
    }

    override suspend fun removeMusicFromPlaylist(playlistId: Long, musicId: Long) {
        val item = PlaylistItemEntity(playlistId, musicId, 0)
        database.playlistItemDao().delete(item)
    }

    override fun getPlaylistItems(playlistId: Long): Flow<List<PlaylistItemEntity>> =
        database.playlistItemDao().getPlaylistItems(playlistId)
}
