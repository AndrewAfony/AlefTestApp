package com.myapp.aleftestapp.data.repository

import com.myapp.aleftestapp.data.remote.ImageApi
import com.myapp.aleftestapp.domain.model.ImageList
import com.myapp.aleftestapp.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: ImageApi
): ImageRepository {

    override suspend fun getImages(): ImageList {
        return api.getImagesUrl()
    }
}