package com.myapp.aleftestapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapp.aleftestapp.domain.model.Image
import androidx.lifecycle.viewModelScope
import com.myapp.aleftestapp.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
): ViewModel() {

    var images = MutableLiveData<List<Image>>()
    private set

    fun loadImages() {
        viewModelScope.launch {
            val res = repository.getImages()
            images.postValue(res.images)
        }
    }
}