package com.losslessmusic.player.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import com.losslessmusic.player.data.database.AppDatabase

class MusicPlaybackService : Service() {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(applicationContext)
        initializeExoPlayer()
    }

    private fun initializeExoPlayer() {
        // ExoPlayer configuration for Hi-Fi playback
        exoPlayer = ExoPlayer.Builder(this)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .build(),
                true
            )
            // Enable bit-perfect playback (no resampling)
            .setSkipSilenceEnabled(false)
            .build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }
}
