package com.losslessmusic.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.losslessmusic.player.domain.model.Music
import com.losslessmusic.player.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {

    private val _allMusic = MutableStateFlow<List<Music>>(emptyList())
    val allMusic: StateFlow<List<Music>> = _allMusic.asStateFlow()

    private val _currentMusic = MutableStateFlow<Music?>(null)
    val currentMusic: StateFlow<Music?> = _currentMusic.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _playbackPosition = MutableStateFlow(0L)
    val playbackPosition: StateFlow<Long> = _playbackPosition.asStateFlow()

    init {
        loadAllMusic()
        scanLocalMusic()
    }

    private fun loadAllMusic() {
        viewModelScope.launch {
            musicRepository.getAllMusic().collect { musicList ->
                _allMusic.value = musicList.map { entity ->
                    Music(
                        id = entity.id,
                        filePath = entity.filePath,
                        title = entity.title,
                        artist = entity.artist,
                        album = entity.album,
                        duration = entity.duration,
                        bitrate = entity.bitrate,
                        sampleRate = entity.sampleRate,
                        format = entity.format,
                        albumArtPath = entity.albumArtPath
                    )
                }
            }
        }
    }

    private fun scanLocalMusic() {
        viewModelScope.launch {
            val scannedMusic = musicRepository.scanLocalMusic()
            musicRepository.insertMultipleMusic(scannedMusic)
        }
    }

    fun playMusic(music: Music) {
        _currentMusic.value = music
        _isPlaying.value = true
    }

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }

    fun updatePlaybackPosition(position: Long) {
        _playbackPosition.value = position
    }

    fun searchMusic(query: String) {
        viewModelScope.launch {
            musicRepository.searchMusic(query).collect { musicList ->
                _allMusic.value = musicList.map { entity ->
                    Music(
                        id = entity.id,
                        filePath = entity.filePath,
                        title = entity.title,
                        artist = entity.artist,
                        album = entity.album,
                        duration = entity.duration,
                        bitrate = entity.bitrate,
                        sampleRate = entity.sampleRate,
                        format = entity.format,
                        albumArtPath = entity.albumArtPath
                    )
                }
            }
        }
    }
}
