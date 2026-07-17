# Lossless Music Player - Installation Guide

## 📱 Installation Methods

### Method 1: Direct APK Installation

1. **Download APK**
   - Get `app-debug.apk` from releases or build it locally

2. **Enable Unknown Sources**
   - Go to: Settings → Security → Unknown Sources (enable)

3. **Install APK**
   ```bash
   adb install app-debug.apk
   ```

### Method 2: Android Studio

1. **Open Project**
   ```bash
   git clone https://github.com/abrizalmhd/lossless-music-player.git
   cd lossless-music-player
   ```

2. **Open in Android Studio**
   - File → Open → Select project folder

3. **Connect Device**
   - USB debugging enabled
   - Device recognized by ADB

4. **Run App**
   - Click "Run" button or press Shift+F10

### Method 3: Google Play Store

- Coming soon! App will be available on Google Play Store

## 🔧 System Requirements

| Requirement | Minimum | Recommended |
|-------------|---------|-------------|
| Android | 7.0 (API 24) | 12+ (API 31+) |
| RAM | 2GB | 4GB+ |
| Storage | 200MB | 1GB+ |
| Screen Size | 4.5" | 5.5"+  |
| Processor | Snapdragon 430 | Snapdragon 888+ |

## 📂 File Locations

### Music Library Path
```
/sdcard/Music/
/storage/emulated/0/Music/
/sdcard/DCIM/Music/
```

### App Data
```
/data/data/com.losslessmusic.player/
/data/data/com.losslessmusic.player/databases/  (Room Database)
/data/data/com.losslessmusic.player/files/       (App Files)
```

## 🎵 Supported Formats

✅ **Lossless**
- FLAC (.flac) - Free Lossless Audio Codec
- WAV (.wav) - Waveform Audio File
- ALAC (.alac) - Apple Lossless Audio Codec
- AIFF (.aiff) - Audio Interchange File Format

✅ **Compressed (for compatibility)**
- MP3 (.mp3)
- AAC (.aac, .m4a)

## 🔊 Audio Setup

### Optimal Hi-Fi Experience

1. **Use High-Quality Headphones/Speakers**
   - Recommended: Studio monitors or audiophile headphones
   - Impedance: 25Ω - 600Ω
   - Driver: 30mm or larger

2. **Enable USB Audio (Optional)**
   - Connect external DAC via USB-C
   - Provides bit-perfect output
   - Brands: FiiO, iBasso, AudioQuest

3. **Disable System Audio Effects**
   - Settings → Sound → Audio Effects (disable)
   - Disable Dolby Atmos
   - Disable any equalizer enhancements

4. **App Settings**
   - Equalizer: Start with "Flat" preset
   - Volume: Keep at 80% system + 100% app
   - Playback: Enable gapless playback

## ⚙️ First Launch Setup

1. **Grant Permissions**
   - Storage access (for music library scanning)
   - Audio focus (for playback control)

2. **Scan Library**
   - Auto-scan on first launch
   - Manual scan: Settings → Rescan Library

3. **Configure Equalizer**
   - Select "Flat" preset for lossless quality
   - Or choose preferred preset

4. **Customize UI**
   - Choose light/dark theme
   - Set default playback options

## 🆘 Troubleshooting

### Songs Not Found
- Check music folder location: `/sdcard/Music/`
- Verify file format is supported
- Try manual library rescan

### No Sound Output
- Ensure volume is not muted
- Check Bluetooth connection (if using wireless)
- Restart app and try again

### Slow Performance
- Clear app cache: Settings → Apps → Clear Cache
- Close background apps
- Reduce visualizer quality

### Battery Drain
- Disable equalizer if not needed
- Reduce screen brightness
- Disable WiFi/Bluetooth when not needed

## 📊 Storage Requirements

| Content | Size |
|---------|------|
| App APK | ~85MB |
| Databases | ~5-10MB |
| Cache | Variable |
| FLAC Music (1 album) | ~300-500MB |
| FLAC Music (100 songs) | ~2-3GB |

## 🔐 Privacy & Permissions

- **Storage Access**: Read music files only
- **No Internet**: App doesn't connect to internet
- **No Tracking**: No analytics or tracking
- **No Ads**: 100% ad-free
- **Open Source**: Code available on GitHub

## 🆙 Updates

### Check for Updates
- In-app: Settings → About → Check Updates
- GitHub: Watch releases page
- Changelog: Available in About section

## 📞 Support

- **Issues**: GitHub Issues page
- **Suggestions**: GitHub Discussions
- **Email**: abrizalmhd2@gmail.com
- **Twitter**: @abrizalmhd

---

**Enjoy Hi-Fidelity Audio! 🎵🔊**
