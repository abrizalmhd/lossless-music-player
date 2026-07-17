package com.losslessmusic.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.losslessmusic.player.audio.equalizer.EqualizerPreset
import com.losslessmusic.player.audio.equalizer.ParametricEqualizer
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

    private val _currentPlaylistId = MutableStateFlow(-1L)
    val currentPlaylistId: StateFlow<Long> = _currentPlaylistId.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _playbackPosition = MutableStateFlow(0L)
    val playbackPosition: StateFlow<Long> = _playbackPosition.asStateFlow()

    private val _queue = MutableStateFlow<List<Music>>(emptyList())
    val queue: StateFlow<List<Music>> = _queue.asStateFlow()

    private val _currentQueueIndex = MutableStateFlow(0)
    val currentQueueIndex: StateFlow<Int> = _currentQueueIndex.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredMusic = MutableStateFlow<List<Music>>(emptyList())
    val filteredMusic: StateFlow<List<Music>> = _filteredMusic.asStateFlow()

    // Equalizer
    private val equalizer = ParametricEqualizer()
    private val _equalizerBands = MutableStateFlow(equalizer.getAllBands())
    val equalizerBands: StateFlow<List<ParametricEqualizer.Band>> = _equalizerBands.asStateFlow()

    private val _equalizerEnabled = MutableStateFlow(false)
    val equalizerEnabled: StateFlow<Boolean> = _equalizerEnabled.asStateFlow()

    private val _currentPreset = MutableStateFlow<EqualizerPreset?>(null)
    val currentPreset: StateFlow<EqualizerPreset?> = _currentPreset.asStateFlow()

    // Visualizer
    private val _visualizerBands = MutableStateFlow(IntArray(32))
    val visualizerBands: StateFlow<IntArray> = _visualizerBands.asStateFlow()

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
                _filteredMusic.value = _allMusic.value
            }
        }
    }

    private fun scanLocalMusic() {
        viewModelScope.launch {
            try {
                val scannedMusic = musicRepository.scanLocalMusic()
                musicRepository.insertMultipleMusic(scannedMusic)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun playMusic(music: Music) {
        _currentMusic.value = music
        _isPlaying.value = true
        _queue.value = _allMusic.value
        _currentQueueIndex.value = _queue.value.indexOf(music)
    }

    fun playMusicFromPlaylist(music: Music, playlist: List<Music>) {
        _currentMusic.value = music
        _isPlaying.value = true
        _queue.value = playlist
        _currentQueueIndex.value = playlist.indexOf(music)
    }

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }

    fun nextTrack() {
        val currentIndex = _currentQueueIndex.value
        if (currentIndex < _queue.value.size - 1) {
            val nextIndex = currentIndex + 1
            _currentQueueIndex.value = nextIndex
            _currentMusic.value = _queue.value[nextIndex]
            _isPlaying.value = true
        }
    }

    fun previousTrack() {
        val currentIndex = _currentQueueIndex.value
        if (currentIndex > 0) {
            val prevIndex = currentIndex - 1
            _currentQueueIndex.value = prevIndex
            _currentMusic.value = _queue.value[prevIndex]
            _isPlaying.value = true
        }
    }

    fun updatePlaybackPosition(position: Long) {
        _playbackPosition.value = position
    }

    fun searchMusic(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            if (query.isBlank()) {
                _filteredMusic.value = _allMusic.value
            } else {
                musicRepository.searchMusic(query).collect { musicList ->
                    _filteredMusic.value = musicList.map { entity ->
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

    fun filterByArtist(artist: String) {
        viewModelScope.launch {
            musicRepository.getMusicByArtist(artist).collect { musicList ->
                _filteredMusic.value = musicList.map { entity ->
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

    fun filterByAlbum(album: String) {
        viewModelScope.launch {
            musicRepository.getMusicByAlbum(album).collect { musicList ->
                _filteredMusic.value = musicList.map { entity ->
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

    fun clearFilter() {
        _searchQuery.value = ""
        _filteredMusic.value = _allMusic.value
    }

    // Equalizer Functions
    fun setBandGain(bandIndex: Int, gain: Float) {
        equalizer.setBandGain(bandIndex, gain)
        _equalizerBands.value = equalizer.getAllBands()
        _currentPreset.value = null // Clear preset when manually adjusting
    }

    fun applyPreset(preset: EqualizerPreset) {
        preset.gains.forEachIndexed { index, gain ->
            equalizer.setBandGain(index, gain)
        }
        _equalizerBands.value = equalizer.getAllBands()
        _currentPreset.value = preset
    }

    fun toggleEqualizer() {
        _equalizerEnabled.value = !_equalizerEnabled.value
    }

    fun resetEqualizer() {
        equalizer.reset()
        _equalizerBands.value = equalizer.getAllBands()
        _currentPreset.value = null
    }

    // Visualizer Functions
    fun updateVisualizerBands(bands: IntArray) {
        _visualizerBands.value = bands
    }
}
