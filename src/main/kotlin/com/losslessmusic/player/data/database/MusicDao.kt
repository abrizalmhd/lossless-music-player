package com.losslessmusic.player.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.losslessmusic.player.data.entity.MusicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {
    @Insert
    suspend fun insert(music: MusicEntity)

    @Insert
    suspend fun insertAll(musicList: List<MusicEntity>)

    @Update
    suspend fun update(music: MusicEntity)

    @Delete
    suspend fun delete(music: MusicEntity)

    @Query("SELECT * FROM music WHERE id = :id")
    fun getMusic(id: Long): Flow<MusicEntity>

    @Query("SELECT * FROM music ORDER BY dateAdded DESC")
    fun getAllMusic(): Flow<List<MusicEntity>>

    @Query("SELECT * FROM music WHERE title LIKE '%' || :query || '%' OR artist LIKE '%' || :query || '%'")
    fun searchMusic(query: String): Flow<List<MusicEntity>>

    @Query("SELECT * FROM music WHERE artist = :artist")
    fun getMusicByArtist(artist: String): Flow<List<MusicEntity>>

    @Query("SELECT * FROM music WHERE album = :album")
    fun getMusicByAlbum(album: String): Flow<List<MusicEntity>>

    @Query("DELETE FROM music")
    suspend fun deleteAll()
}
