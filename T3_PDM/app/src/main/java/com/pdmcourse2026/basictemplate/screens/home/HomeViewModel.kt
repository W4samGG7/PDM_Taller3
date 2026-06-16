package com.pdmcourse2026.basictemplate.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmcourse2026.basictemplate.data.repositories.OptionRepository.OptionApiRepository
import com.pdmcourse2026.basictemplate.data.repositories.OptionRepository.OptionRepository
import com.pdmcourse2026.basictemplate.model.Option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val optionRepository: OptionRepository = OptionApiRepository()

    private val _options = MutableStateFlow<List<Option>>(emptyList())

    val options = _options.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)

    val loading = _loading.asStateFlow()

    private val _saving = MutableStateFlow<Boolean>(false)

    val saving = _saving.asStateFlow()


    private val _error = MutableStateFlow<String?>(null)

    val error = _error.asStateFlow()

    private val _saveMessage = MutableStateFlow<String?>(null)

    val saveMessage = _saveMessage.asStateFlow()

    fun loadOptions(){
        viewModelScope.launch {
            _error.value = null
            _loading.value = true

            optionRepository.getOption()
                .onSuccess { options ->
                _options.value = options}
                .onFailure { error ->
                    _error.value = "Error al cargar opciones: ${error}"
                }

            _loading.value = false
        }
    }

    init {
        loadOptions()
    }

    fun vote(optionId: Int){
        viewModelScope.launch {
            _saving.value = true

            optionRepository.vote(optionId)
                .onSuccess {
                }
                .onFailure {

                }

            _saving.value = false
        }
    }




}