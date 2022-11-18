package io.joelt.texttemplate.ui.screens.template_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.joelt.texttemplate.models.Template
import io.joelt.texttemplate.database.TemplatesRepository
import kotlinx.coroutines.*

class TemplateEditViewModel(
    private val repository: TemplatesRepository
) : ViewModel() {
    var screenState by mutableStateOf(TemplateEditState(null))

    fun loadTemplate(id: Long) {
        if (id == 0L) {
            screenState = TemplateEditState(Template(name = "New Template", text = ""))
        } else {
            viewModelScope.launch {
                screenState = TemplateEditState(repository.getTemplate(id))
            }
        }
    }

    fun saveTemplate() {
        viewModelScope.launch {
            screenState.template?.let { template ->
                if (template.id == 0L) {
                    val templateId = repository.createTemplate(template)
                    screenState = screenState.copy(template = template.copy(id = templateId))
                } else {
                    repository.updateTemplate(template)
                }
            }
        }
    }
}