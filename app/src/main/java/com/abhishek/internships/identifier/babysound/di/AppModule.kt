package com.abhishek.internships.identifier.babysound.di


import android.content.Context
import com.abhishek.internships.identifier.babysound.data.audio.AudioRecorder
import com.abhishek.internships.identifier.babysound.data.audio.MFCCExtractor
import com.abhishek.internships.identifier.babysound.data.ml.BabySoundClassifier
import com.abhishek.internships.identifier.babysound.data.repository.SoundRepositoryImpl
import com.abhishek.internships.identifier.babysound.domain.repository.SoundRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAudioRecorder(): AudioRecorder = AudioRecorder()

    @Provides
    fun provideMFCCExtractor(): MFCCExtractor = MFCCExtractor()

    @Provides
    fun provideClassifier(
        @ApplicationContext context: Context
    ): BabySoundClassifier = BabySoundClassifier(context)

    @Provides
    fun provideRepository(
        recorder: AudioRecorder,
        extractor: MFCCExtractor,
        classifier: BabySoundClassifier
    ): SoundRepository =
        SoundRepositoryImpl(recorder, extractor, classifier)
}