package com.losslessmusic.player.audio.equalizer

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class ParametricEqualizer {
    
    data class Band(val frequency: Float, var gain: Float = 0f, val q: Float = 0.707f)

    private val bands = listOf(
        Band(60f),      // Sub-bass
        Band(125f),     // Bass
        Band(250f),     // Bass-mid
        Band(500f),     // Midrange
        Band(1000f),    // Midrange
        Band(2000f),    // Upper-mid
        Band(4000f),    // Presence
        Band(8000f),    // Presence
        Band(16000f)    // Treble
    )

    private val coefficients = mutableListOf<BiquadCoefficients>()
    private val sampleRate = 48000f

    init {
        updateCoefficients()
    }

    fun setBandGain(bandIndex: Int, gain: Float) {
        if (bandIndex in bands.indices) {
            bands[bandIndex].gain = gain.coerceIn(-12f, 12f)
            updateCoefficients()
        }
    }

    fun getBandGain(bandIndex: Int): Float {
        return if (bandIndex in bands.indices) bands[bandIndex].gain else 0f
    }

    fun getAllBands(): List<Band> = bands.map { it.copy() }

    fun processAudio(samples: FloatArray): FloatArray {
        var output = samples.copyOf()
        for (coeff in coefficients) {
            output = coeff.process(output)
        }
        return output
    }

    fun reset() {
        bands.forEach { it.gain = 0f }
        updateCoefficients()
    }

    private fun updateCoefficients() {
        coefficients.clear()
        for (band in bands) {
            coefficients.add(createPeakFilter(band.frequency, band.q, band.gain))
        }
    }

    private fun createPeakFilter(frequency: Float, q: Float, gain: Float): BiquadCoefficients {
        val w0 = 2 * PI * frequency / sampleRate
        val sinW0 = sin(w0)
        val cosW0 = cos(w0)
        val alpha = sinW0 / (2 * q)
        val a = (10.0.pow(gain / 40.0)).toFloat()

        val b0 = 1 + alpha * a
        val b1 = -2 * cosW0
        val b2 = 1 - alpha * a
        val a0 = 1 + alpha / a
        val a1 = -2 * cosW0
        val a2 = 1 - alpha / a

        return BiquadCoefficients(
            b0 / a0, b1 / a0, b2 / a0,
            a1 / a0, a2 / a0
        )
    }

    private fun Double.pow(exp: Double): Double {
        return Math.pow(this, exp)
    }
}

class BiquadCoefficients(
    val b0: Float, val b1: Float, val b2: Float,
    val a1: Float, val a2: Float
) {
    private var x1 = 0f
    private var x2 = 0f
    private var y1 = 0f
    private var y2 = 0f

    fun process(samples: FloatArray): FloatArray {
        val output = FloatArray(samples.size)
        for (i in samples.indices) {
            val x0 = samples[i]
            val y0 = b0 * x0 + b1 * x1 + b2 * x2 - a1 * y1 - a2 * y2
            
            output[i] = y0
            x2 = x1
            x1 = x0
            y2 = y1
            y1 = y0
        }
        return output
    }
}
