package com.losslessmusic.player.domain.model

data class Music(
    val id: Long = 0,
    val filePath: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val bitrate: String,
    val sampleRate: String,
    val format: String,
    val albumArtPath: String? = null
)

data class Playlist(
    val id: Long = 0,
    val name: String,
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
