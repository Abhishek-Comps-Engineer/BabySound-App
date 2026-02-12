package com.abhishek.internships.identifier.babysound.data.audio

import android.Manifest
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.annotation.RequiresPermission

class AudioRecorder {

    private val sampleRate = 16000
    private val bufferSize = AudioRecord.getMinBufferSize(
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )
    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun record(seconds: Int = 5): ShortArray {

        val totalSamples = sampleRate * seconds
        val buffer = ShortArray(totalSamples)

        val recorder = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )

        if (recorder.state != AudioRecord.STATE_INITIALIZED) {
            recorder.release()
            throw IllegalStateException("AudioRecord initialization failed")
        }

        recorder.startRecording()

        var offset = 0

        while (offset < totalSamples) {
            val read = recorder.read(
                buffer,
                offset,
                minOf(bufferSize, totalSamples - offset)
            )

            if (read > 0) {
                offset += read
            } else {
                break
            }
        }

        recorder.stop()
        recorder.release()

        return buffer
    }
}
