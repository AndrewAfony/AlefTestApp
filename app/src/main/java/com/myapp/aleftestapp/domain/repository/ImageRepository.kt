package com.myapp.aleftestapp.domain.repository

import com.myapp.aleftestapp.domain.model.ImageList

interface ImageRepository {

    suspend fun getImages(): ImageList
}