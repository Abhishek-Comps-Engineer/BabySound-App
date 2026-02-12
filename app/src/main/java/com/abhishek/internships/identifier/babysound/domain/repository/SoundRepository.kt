package com.abhishek.internships.identifier.babysound.domain.repository

import com.abhishek.internships.identifier.babysound.domain.model.SoundResult

interface SoundRepository {
    suspend fun analyze(): SoundResult
}