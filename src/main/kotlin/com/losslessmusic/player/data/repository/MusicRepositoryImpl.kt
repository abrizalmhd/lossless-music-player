package com.losslessmusic.player.data.repository

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.room.withTransaction
import com.losslessmusic.player.data.database.AppDatabase
import com.losslessmusic.player.data.entity.MusicEntity
import com.losslessmusic.player.domain.repository.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.jaudiotagger.audio.AudioFileIO
import java.io.File

class MusicRepositoryImpl(
    private val database: AppDatabase,
    private val context: Context
) : MusicRepository {

    override fun getAllMusic(): Flow<List<MusicEntity>> =
        database.musicDao().getAllMusic()

    override fun searchMusic(query: String): Flow<List<MusicEntity>> =
        database.musicDao().searchMusic(query)

    override fun getMusicByArtist(artist: String): Flow<List<MusicEntity>> =
        database.musicDao().getMusicByArtist(artist)

    override fun getMusicByAlbum(album: String): Flow<List<MusicEntity>> =
        database.musicDao().getMusicByAlbum(album)

    override suspend fun insertMusic(music: MusicEntity) {
        database.musicDao().insert(music)
    }

    override suspend fun insertMultipleMusic(musicList: List<MusicEntity>) {
        database.musicDao().insertAll(musicList)
    }

    override suspend fun updateMusic(music: MusicEntity) {
        database.musicDao().update(music)
    }

    override suspend fun deleteMusic(music: MusicEntity) {
        database.musicDao().delete(music)
    }

    override suspend fun scanLocalMusic(): List<MusicEntity> = withContext(Dispatchers.IO) {
        val musicList = mutableListOf<MusicEntity>()
        val musicFormats = setOf("flac", "wav", "alac", "aiff", "mp3", "aac", "m4a")

        try {
            val musicDir = context.getExternalFilesDir("Music")
            if (musicDir != null && musicDir.exists()) {
                scanDirectory(musicDir, musicList, musicFormats)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return@withContext musicList
    }

    private fun scanDirectory(
        dir: File,
        musicList: MutableList<MusicEntity>,
        formats: Set<String>
    ) {
        dir.listFiles()?.forEach { file ->
            when {
                file.isDirectory -> scanDirectory(file, musicList, formats)
                file.isFile && file.extension.lowercase() in formats -> {
                    try {
                        val audioFile = AudioFileIO.read(file)
                        val tag = audioFile.tag

                        val musicEntity = MusicEntity(
                            filePath = file.absolutePath,
                            title = tag?.first("title") ?: file.nameWithoutExtension,
                            artist = tag?.first("artist") ?: "Unknown",
                            album = tag?.first("album") ?: "Unknown",
                            duration = audioFile.audioHeader.trackLength.toLong() * 1000,
                            bitrate = "${audioFile.audioHeader.bitRateAsNumber / 1000} kbps",
                            sampleRate = "${audioFile.audioHeader.sampleRateAsNumber} Hz",
                            format = file.extension.uppercase()
                        )

                        musicList.add(musicEntity)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}
