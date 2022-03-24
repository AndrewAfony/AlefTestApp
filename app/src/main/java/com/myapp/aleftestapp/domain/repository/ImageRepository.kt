package com.myapp.aleftestapp.domain.repository

interface ImageRepository {

    suspend fun getImages(): List<String>
}