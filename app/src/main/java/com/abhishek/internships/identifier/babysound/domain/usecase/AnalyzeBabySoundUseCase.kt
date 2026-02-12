package com.abhishek.internships.identifier.babysound.domain.usecase

import com.abhishek.internships.identifier.babysound.domain.model.SoundResult
import com.abhishek.internships.identifier.babysound.domain.repository.SoundRepository
import javax.inject.Inject


class AnalyzeBabySoundUseCase @Inject constructor(
    private val repository: SoundRepository
) {
    suspend operator fun invoke(): SoundResult =
        repository.analyze()
}
