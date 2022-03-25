package com.myapp.aleftestapp.domain.repository

import com.myapp.aleftestapp.util.Resource

interface ImageRepository {

    suspend fun getImages(): Resource<List<String>>
}