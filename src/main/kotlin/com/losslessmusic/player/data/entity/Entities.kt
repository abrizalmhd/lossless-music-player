package com.losslessmusic.player.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class MusicEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val filePath: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val bitrate: String,
    val sampleRate: String,
    val format: String,
    val albumArtPath: String? = null,
    val dateAdded: Long = System.currentTimeMillis()
)

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "playlist_items",
    primaryKeys = ["playlistId", "musicId"]
)
data class PlaylistItemEntity(
    val playlistId: Long,
    val musicId: Long,
    val position: Int
)
