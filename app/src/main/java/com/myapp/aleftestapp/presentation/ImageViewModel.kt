package com.myapp.aleftestapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.myapp.aleftestapp.domain.repository.ImageRepository
import com.myapp.aleftestapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "viewModel"

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
): ViewModel() {

    var images = MutableLiveData<List<String>>()
    private set

    var eventFlow = MutableSharedFlow<String>()
        private set

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            val res = repository.getImages()
            when (res) {
                is Resource.Success -> {
                    res.data?.let {
                        images.postValue(it)
                    }
                }
                is Resource.Error -> {
                    res.message?.let { eventFlow.emit(it) }
                }
            }
        }
    }
}