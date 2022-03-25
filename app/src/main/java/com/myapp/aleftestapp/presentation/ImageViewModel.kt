package com.myapp.aleftestapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.aleftestapp.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "viewModel"

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
): ViewModel() {

    var images = MutableLiveData<List<String>>()
    private set

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            try {
                val res = repository.getImages()
                images.postValue(res)
            } catch (e: HttpException) {
                Log.d(TAG, "loadImages: ${e.localizedMessage}")
            } catch (e: IOException) {
                Log.d(TAG, "loadImages: ${e.localizedMessage}")
            }
        }
    }
}