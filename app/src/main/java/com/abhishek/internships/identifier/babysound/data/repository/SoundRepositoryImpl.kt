package com.abhishek.internships.identifier.babysound.data.repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.abhishek.internships.identifier.babysound.data.audio.AudioRecorder
import com.abhishek.internships.identifier.babysound.data.audio.Labels
import com.abhishek.internships.identifier.babysound.data.audio.MFCCExtractor
import com.abhishek.internships.identifier.babysound.data.ml.BabySoundClassifier
import com.abhishek.internships.identifier.babysound.domain.model.SoundResult
import com.abhishek.internships.identifier.babysound.domain.repository.SoundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SoundRepositoryImpl(
    private val recorder: AudioRecorder,
    private val extractor: MFCCExtractor,
    private val classifier: BabySoundClassifier
) : SoundRepository {

    @RequiresPermission(android.Manifest.permission.RECORD_AUDIO)
    override suspend fun analyze(): SoundResult =
        withContext(Dispatchers.IO) {

            val audio = recorder.record()
            val mfcc = extractor.extract(audio)
            val (index, confidence) = classifier.classify(mfcc)

            val meaning =
                if (confidence > 0.6f)
                    "Your baby is likely ${Labels.classes[index]}"
                else
                    "Sound unclear. Please try again."

            SoundResult(meaning, confidence)
        }
}
