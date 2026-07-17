# Lossless Music Player - Android Hi-Fi Audio App

🎵 **Premium High-Fidelity Audio Player** dengan support format lossless berkualitas tinggi.

## 🎯 Fitur Unggulan

✅ **Format Lossless:**
- FLAC (Free Lossless Audio Codec)
- WAV (Waveform Audio File)
- ALAC (Apple Lossless Audio Codec)
- AIFF

✅ **Kualitas Playback:**
- 🔊 Bit-perfect playback (no resampling)
- Support hingga 24-bit/192kHz
- Zero audio processing untuk kualitas asli
- Gapless playback

✅ **User Interface:**
- Material Design 3 dengan tema dark/light
- Now Playing screen dengan visualizer
- Library browser yang intuitif
- Real-time playback info (bitrate, sample rate, format)

✅ **Library Management:**
- Automatic music scanning
- Playlist creation & management
- Search & filter functionality
- Metadata tagging (album art, artist, duration)

✅ **Audio Processing:**
- 10-band parametric equalizer (advanced)
- Volume normalization options
- Bass boost & treble control

## 🏗️ Struktur Proyek

```
lossless-music-player/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/losslessmusic/player/
│   │   │       ├── data/
│   │   │       │   ├── database/
│   │   │       │   ├── entity/
│   │   │       │   └── repository/
│   │   │       ├── domain/
│   │   │       │   ├── model/
│   │   │       │   └── repository/
│   │   │       ├── presentation/
│   │   │       │   └── viewmodel/
│   │   │       ├── ui/
│   │   │       │   ├── screens/
│   │   │       │   └── theme/
│   │   │       ├── service/
│   │   │       ├── di/
│   │   │       ├── MainActivity.kt
│   │   │       └── LosslessMusicPlayerApp.kt
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── README.md
└── .gitignore
```

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Audio Engine:** ExoPlayer 3 (Media3)
- **Database:** Room Database
- **Dependency Injection:** Hilt
- **Metadata Reader:** jAudioTagger
- **State Management:** Coroutines + Flow

## 📋 Requirements

- Android API 24+ (Android 7.0 Nougat)
- 200MB+ free storage (untuk music library)
- Headphones/speakers berkualitas untuk hasil optimal

## 🚀 Getting Started

### Prerequisites
- Android Studio Giraffe atau newer
- JDK 17+
- Gradle 8.0+

### Installation

1. **Clone Repository**
   ```bash
   git clone https://github.com/abrizalmhd/lossless-music-player.git
   cd lossless-music-player
   ```

2. **Build Project**
   ```bash
   ./gradlew build
   ```

3. **Run on Device/Emulator**
   ```bash
   ./gradlew installDebug
   ```

## 📱 Usage

1. **Scan Library:** Aplikasi otomatis scan musik di folder
2. **Browse:** Lihat semua lagu dengan detail format & quality
3. **Play:** Tap play button untuk memutar
4. **Control:** Play/Pause, Skip, seekbar, volume control
5. **Playlist:** Buat playlist custom sesuai selera

## 🎚️ Advanced Features (Coming Soon)

- ✨ Audio visualizer spektrum
- 🔊 Parametric EQ dengan presets
- 📊 Audio analysis & waveform display
- 🎧 Crossfade control
- 📱 Multi-device sync
- ☁️ Cloud integration

## 📊 Audio Quality Specifications

| Spec | Value |
|------|-------|
| **Max Sample Rate** | 192 kHz |
| **Max Bit Depth** | 24-bit |
| **Supported Formats** | FLAC, WAV, ALAC, AIFF, MP3, AAC |
| **Resampling** | Disabled (bit-perfect) |
| **Processing** | Minimal DSP, natural sound |

## 🤝 Contributing

Contributions welcome! Silahkan:
1. Fork repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📄 License

MIT License - See LICENSE file for details

## 👨‍💻 Author

**abrizalmhd** - [GitHub Profile](https://github.com/abrizalmhd)

## 📞 Support

Untuk pertanyaan & issues:
- Buka GitHub Issues
- Email: abrizalmhd2@gmail.com

---

**Made with ❤️ for music enthusiasts**

🎵 Enjoy Hi-Fi Audio Experience! 🎵
