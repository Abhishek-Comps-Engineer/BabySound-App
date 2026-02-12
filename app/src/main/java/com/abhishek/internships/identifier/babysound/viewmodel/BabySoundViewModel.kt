package com.abhishek.internships.identifier.babysound.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.internships.identifier.babysound.domain.model.SoundResult
import com.abhishek.internships.identifier.babysound.domain.usecase.AnalyzeBabySoundUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class BabySoundViewModel @Inject constructor(
    private val analyzeUseCase: AnalyzeBabySoundUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SoundResult?>(null)
    val state = _state.asStateFlow()

    fun analyze() {
        viewModelScope.launch {
            _state.value = analyzeUseCase()
        }
    }
}


