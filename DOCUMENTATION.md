# Lossless Music Player - Complete Documentation

## 📖 Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Installation](#installation)
5. [Usage](#usage)
6. [API Reference](#api-reference)
7. [Contributing](#contributing)
8. [FAQ](#faq)

---

## Overview

**Lossless Music Player** adalah aplikasi Android premium untuk pemutaran musik dengan kualitas audiophile. Aplikasi ini dirancang khusus untuk mendengarkan format lossless dengan kualitas asli tanpa kompresi atau pemrosesan yang merusak kualitas audio.

### Key Highlights

✅ **Hi-Fi Audio Playback** - Bit-perfect, 24-bit/192kHz support
✅ **Multiple Lossless Formats** - FLAC, WAV, ALAC, AIFF
✅ **Advanced Equalizer** - 9-band parametric with 10 presets
✅ **Real-time Visualizer** - 32-band FFT spectrum analyzer
✅ **Clean Architecture** - MVVM + Repository pattern
✅ **Modern UI** - Material Design 3 with Jetpack Compose

---

## Features

### Core Playback
- Bit-perfect audio output (no resampling)
- Support for 24-bit/192kHz audio
- Gapless playback
- Queue management
- Shuffle and repeat modes
- Playback position memory

### Library Management
- Automatic music scanning
- Search by title/artist/album
- Browse by artist or album
- Create and manage playlists
- Add to queue functionality
- Recently played tracking

### Audio Processing
- 9-band parametric equalizer
- 10 professional presets
- Manual EQ adjustment (-12dB to +12dB)
- Bass boost and treble control
- Crossfade support

### Visualization
- Real-time 32-band spectrum analyzer
- FFT-based frequency visualization
- Customizable visualization intensity
- Smooth animation transitions

### UI/UX
- Material Design 3 theme
- Dark and light modes
- Now Playing screen with album art
- Mini player in library view
- Responsive layout
- Smooth transitions

---

## Architecture

### Project Structure

```
src/main/
├── kotlin/
│   └── com/losslessmusic/player/
│       ├── ui/
│       │   ├── screens/
│       │   │   ├── HomeScreen.kt
│       │   │   ├── NowPlayingScreen.kt
│       │   │   ├── EqualizerScreen.kt
│       │   │   └── PlaylistScreen.kt
│       │   └── theme/
│       │       └── Theme.kt
│       ├── data/
│       │   ├── database/
│       │   │   ├── AppDatabase.kt
│       │   │   ├── MusicDao.kt
│       │   │   ├── PlaylistDao.kt
│       │   │   └── PlaylistItemDao.kt
│       │   ├── entity/
│       │   │   └── Entities.kt
│       │   └── repository/
│       │       ├── MusicRepositoryImpl.kt
│       │       └── PlaylistRepositoryImpl.kt
│       ├── domain/
│       │   ├── model/
│       │   │   └── Music.kt
│       │   └── repository/
│       │       ├── MusicRepository.kt
│       │       └── PlaylistRepository.kt
│       ├── presentation/
│       │   └── viewmodel/
│       │       └── MusicViewModel.kt
│       ├── audio/
│       │   ├── equalizer/
│       │   │   ├── ParametricEqualizer.kt
│       │   │   └── EqualizerPresets.kt
│       │   └── visualizer/
│       │       └── AudioVisualizer.kt
│       ├── service/
│       │   └── MusicPlaybackService.kt
│       ├── di/
│       │   └── AppModule.kt
│       └── MainActivity.kt
├── res/
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│   └── ...
└── AndroidManifest.xml
```

### Architecture Pattern

```
UI Layer (Compose)
    ↓
Presentation Layer (ViewModel)
    ↓
Domain Layer (Repository Interface)
    ↓
Data Layer (Repository Implementation + Database)
    ↓
Audio Layer (ExoPlayer + DSP)
```

---

## Installation

See [INSTALLATION_GUIDE.md](INSTALLATION_GUIDE.md)

---

## Usage

### Basic Playback

1. **Browse Library**
   - Open app → See all songs
   - Search bar untuk mencari lagu spesifik
   - Tap lagu untuk play

2. **Playback Controls**
   - Play/Pause button center
   - Skip Next/Previous buttons
   - Seek bar untuk jump to position

3. **Queue Management**
   - Current queue displayed
   - Swipe to next/previous song
   - Auto-play next song

### Using Equalizer

1. **Open Equalizer**
   - From Now Playing → Menu → Equalizer
   - Or from Settings

2. **Apply Preset**
   - Select from 10 presets
   - Preset auto-applies

3. **Manual Adjustment**
   - Drag sliders for each band
   - Real-time audio adjustment
   - Save custom preset (coming soon)

### Creating Playlists

1. **New Playlist**
   - Library → Playlists → Create New
   - Enter playlist name

2. **Add Songs**
   - Browse library
   - Long-press song → Add to Playlist
   - Select target playlist

3. **Manage Playlist**
   - Remove songs
   - Reorder songs
   - Delete playlist

---

## API Reference

### MusicViewModel

```kotlin
// Play music
fun playMusic(music: Music)

// Player controls
fun togglePlayPause()
fun nextTrack()
fun previousTrack()

// Search and filter
fun searchMusic(query: String)
fun filterByArtist(artist: String)
fun filterByAlbum(album: String)
fun clearFilter()

// Equalizer
fun setBandGain(bandIndex: Int, gain: Float)
fun applyPreset(preset: EqualizerPreset)
fun toggleEqualizer()
fun resetEqualizer()

// Visualizer
fun updateVisualizerBands(bands: IntArray)
```

### ParametricEqualizer

```kotlin
// Create instance
val equalizer = ParametricEqualizer()

// Set band gain
equalizer.setBandGain(bandIndex = 0, gain = 6f)

// Get band info
val bands = equalizer.getAllBands()

// Process audio
val output = equalizer.processAudio(inputSamples)

// Reset
equalizer.reset()
```

### AudioVisualizer

```kotlin
// Create instance
val visualizer = AudioVisualizer()

// Process PCM data (from audio stream)
val spectrumBands = visualizer.processPCM(pcmData)

// Returns: IntArray of 32 bands (0-100 scale)
```

---

## Contributing

Kontribusi sangat diterima! Silahkan:

1. Fork repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

### Development Setup

```bash
# Clone repo
git clone https://github.com/abrizalmhd/lossless-music-player.git
cd lossless-music-player

# Open in Android Studio
# File → Open → Select project

# Build project
./gradlew build

# Run on device
./gradlew installDebug
```

---

## FAQ

### Q: Apa bedanya dengan aplikasi musik lain?
**A:** Lossless Music Player dirancang khusus untuk audio berkualitas tinggi tanpa kompresi. Fitur bit-perfect playback memastikan kualitas audio asli didengar.

### Q: Format apa yang didukung?
**A:** FLAC, WAV, ALAC, AIFF (primary), plus MP3 & AAC untuk kompatibilitas.

### Q: Apakah perlu internet?
**A:** Tidak! App 100% offline. Tidak ada cloud, streaming, atau tracking.

### Q: Bagaimana dengan baterai?
**A:** Audio playback relatif hemat energi. Visualizer real-time bisa sedikit drain baterai.

### Q: Dapatkah saya menggunakan equalizer sambil bermain?
**A:** Ya! Adjustment real-time tanpa henti.

### Q: Bagaimana dengan lirik lagu?
**A:** Coming soon! Fitur lyrics display sedang dalam pengembangan.

### Q: Apakah ada versi premium?
**A:** Tidak! Aplikasi sepenuhnya gratis dan open-source.

---

## License

MIT License - See [LICENSE](LICENSE) file

## Author

**abrizalmhd** - [GitHub](https://github.com/abrizalmhd)

## Support

- 📧 Email: abrizalmhd2@gmail.com
- 🐛 Issues: [GitHub Issues](https://github.com/abrizalmhd/lossless-music-player/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/abrizalmhd/lossless-music-player/discussions)

---

**Made with ❤️ for music lovers**

🎵 Enjoy Hi-Fidelity Audio Experience! 🎵
