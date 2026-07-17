package com.losslessmusic.player.audio.visualizer

import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType
import kotlin.math.log10
import kotlin.math.max

class AudioVisualizer {

    private val fft = FastFourierTransformer(DftNormalization.STANDARD)
    private val bands = IntArray(32) // 32-band spectrum

    fun processPCM(pcmData: FloatArray): IntArray {
        // Pad to power of 2 for FFT
        val paddedSize = nextPowerOf2(pcmData.size)
        val paddedData = DoubleArray(paddedSize)
        
        // Copy and apply Hann window
        for (i in pcmData.indices) {
            val window = 0.5 - 0.5 * kotlin.math.cos(2.0 * Math.PI * i / (pcmData.size - 1))
            paddedData[i] = pcmData[i] * window
        }

        try {
            // Perform FFT
            val fftResult = fft.transform(paddedData, TransformType.FORWARD)
            
            // Calculate magnitude spectrum
            val magnitudes = DoubleArray(fftResult.size / 2)
            for (i in magnitudes.indices) {
                val real = fftResult[i * 2]
                val imag = fftResult[i * 2 + 1]
                magnitudes[i] = kotlin.math.sqrt(real * real + imag * imag)
            }

            // Convert to 32 bands
            updateBands(magnitudes)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bands
    }

    private fun updateBands(magnitudes: DoubleArray) {
        val bandWidth = magnitudes.size / bands.size
        
        for (b in bands.indices) {
            val start = b * bandWidth
            val end = min((b + 1) * bandWidth, magnitudes.size)
            
            var sum = 0.0
            for (i in start until end) {
                sum += magnitudes[i]
            }
            
            val avg = if (end > start) sum / (end - start) else 0.0
            // Convert to dB scale
            val db = if (avg > 0) 20 * log10(max(avg, 1e-10)) else 0.0
            bands[b] = max(0, min(100, (db + 60).toInt()))
        }
    }

    private fun nextPowerOf2(n: Int): Int {
        var p = 1
        while (p < n) p *= 2
        return p
    }

    private fun min(a: Int, b: Int) = if (a < b) a else b
}
