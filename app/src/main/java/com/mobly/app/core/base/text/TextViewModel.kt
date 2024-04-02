package com.mobly.app.core.base.text

import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.mobly.app.core.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TextViewModel(
    private val generativeModel: GenerativeModel) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun generateStory(inputText: String) {
        _uiState.value = UiState.Loading

    }
}
