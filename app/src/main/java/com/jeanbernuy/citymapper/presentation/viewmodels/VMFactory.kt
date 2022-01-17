package com.jeanbernuy.citymapper.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeanbernuy.citymapper.domain.StopPointRepository


class VMFactory(private val repository: StopPointRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(StopPointRepository::class.java)
            .newInstance(repository)
    }
}