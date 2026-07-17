package com.losslessmusic.player.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.losslessmusic.player.data.entity.PlaylistItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistItemDao {
    @Insert
    suspend fun insert(item: PlaylistItemEntity)

    @Insert
    suspend fun insertAll(items: List<PlaylistItemEntity>)

    @Delete
    suspend fun delete(item: PlaylistItemEntity)

    @Query("SELECT * FROM playlist_items WHERE playlistId = :playlistId ORDER BY position")
    fun getPlaylistItems(playlistId: Long): Flow<List<PlaylistItemEntity>>

    @Transaction
    @Query("DELETE FROM playlist_items WHERE playlistId = :playlistId")
    suspend fun deleteAllItemsInPlaylist(playlistId: Long)

    @Query("DELETE FROM playlist_items")
    suspend fun deleteAll()
}
