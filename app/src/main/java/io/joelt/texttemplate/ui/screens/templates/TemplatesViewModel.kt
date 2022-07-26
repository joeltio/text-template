package io.joelt.texttemplate.ui.screens.templates

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.joelt.texttemplate.models.Template
import io.joelt.texttemplate.database.TemplatesRepository
import kotlinx.coroutines.launch

class TemplatesViewModel(
    private val repository: TemplatesRepository
) : ViewModel() {
    var templates: List<Template>? by mutableStateOf(null)
        private set

    fun loadTemplates() {
        viewModelScope.launch {
            templates = repository.getTemplates()
        }
    }
}