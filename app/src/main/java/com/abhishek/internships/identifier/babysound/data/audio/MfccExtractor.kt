package com.abhishek.internships.identifier.babysound.data.audio

import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType
import kotlin.math.*

class MFCCExtractor {

    private val sampleRate = 16000

    private val numCoefficients = 40
    private val melFilters = 40
    private val fftSize = 512

    fun extract(audio: ShortArray): FloatArray {

        val floatSignal = audio.map { it / 32768f }.toFloatArray()
        val frames = frameSignal(floatSignal)

        val mfccList = mutableListOf<FloatArray>()

        for (frame in frames) {
            val spectrum = fft(frame)
            val melEnergy = melFilterBank(spectrum)

            // ✅ Correct log10 implementation
            val logMel = melEnergy.map {
                log10(it + 1e-10)
            }.toDoubleArray()

            val mfcc = dct(logMel)
            mfccList.add(mfcc)
        }

        return mean(mfccList)
    }

    private fun frameSignal(signal: FloatArray): List<FloatArray> {
        val hop = fftSize / 2
        val frames = mutableListOf<FloatArray>()

        var i = 0
        while (i + fftSize <= signal.size) {
            frames.add(signal.copyOfRange(i, i + fftSize))
            i += hop
        }

        return frames
    }

    private fun fft(frame: FloatArray): DoubleArray {
        val fft = FastFourierTransformer(DftNormalization.STANDARD)

        val complex = fft.transform(
            frame.map { it.toDouble() }.toDoubleArray(),
            TransformType.FORWARD
        )

        return complex.map { it.abs() }.toDoubleArray()
    }

    private fun melFilterBank(spectrum: DoubleArray): DoubleArray {
        val mel = DoubleArray(melFilters)

        val binSize = spectrum.size / melFilters

        for (i in 0 until melFilters) {
            val start = i * binSize
            val end = min(start + binSize, spectrum.size)

            mel[i] = spectrum.slice(start until end).average()
        }

        return mel
    }

    // ✅ Manual DCT-II (Correct MFCC)
    private fun dct(input: DoubleArray): FloatArray {
        val result = FloatArray(numCoefficients)

        for (k in 0 until numCoefficients) {
            var sum = 0.0
            for (n in input.indices) {
                sum += input[n] *
                        kotlin.math.cos(
                            Math.PI * k * (2 * n + 1) / (2 * input.size)
                        )
            }
            result[k] = sum.toFloat()
        }

        return result
    }

    private fun mean(vectors: List<FloatArray>): FloatArray {
        val mean = FloatArray(numCoefficients)

        for (v in vectors) {
            for (i in v.indices) {
                mean[i] += v[i]
            }
        }

        for (i in mean.indices) {
            mean[i] /= vectors.size
        }

        return mean
    }
}