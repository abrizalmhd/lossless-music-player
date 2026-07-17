package com.losslessmusic.player.audio.equalizer

data class EqualizerPreset(
    val name: String,
    val gains: FloatArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EqualizerPreset

        if (name != other.name) return false
        if (!gains.contentEquals(other.gains)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + gains.contentHashCode()
        return result
    }
}

object EqualizerPresets {
    val FLAT = EqualizerPreset(
        "Flat",
        floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    )

    val BASS_BOOST = EqualizerPreset(
        "Bass Boost",
        floatArrayOf(8f, 6f, 4f, 2f, 0f, -2f, -4f, -4f, -4f)
    )

    val TREBLE_BOOST = EqualizerPreset(
        "Treble Boost",
        floatArrayOf(-4f, -4f, -4f, -2f, 0f, 2f, 4f, 6f, 8f)
    )

    val BALANCED = EqualizerPreset(
        "Balanced",
        floatArrayOf(2f, 1f, 0f, 0f, 0f, 0f, 0f, 1f, 2f)
    )

    val WARM = EqualizerPreset(
        "Warm",
        floatArrayOf(4f, 3f, 2f, 1f, 0f, -1f, 0f, 1f, 2f)
    )

    val BRIGHT = EqualizerPreset(
        "Bright",
        floatArrayOf(-2f, -1f, 0f, 1f, 2f, 3f, 4f, 5f, 6f)
    )

    val CLASSICAL = EqualizerPreset(
        "Classical",
        floatArrayOf(2f, 2f, 2f, 0f, 0f, 0f, -2f, -2f, -2f)
    )

    val ROCK = EqualizerPreset(
        "Rock",
        floatArrayOf(5f, 3f, -1f, -3f, -2f, 1f, 3f, 5f, 6f)
    )

    val POP = EqualizerPreset(
        "Pop",
        floatArrayOf(-1f, -0.5f, 1f, 3f, 2f, 0f, -1f, -0.5f, -1f)
    )

    val JAZZ = EqualizerPreset(
        "Jazz",
        floatArrayOf(3f, 2f, 0f, -2f, -2f, 0f, 2f, 3f, 4f)
    )

    val ALL = listOf(FLAT, BASS_BOOST, TREBLE_BOOST, BALANCED, WARM, BRIGHT, CLASSICAL, ROCK, POP, JAZZ)
}
