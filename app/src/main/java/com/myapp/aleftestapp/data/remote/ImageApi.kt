package com.myapp.aleftestapp.data.remote

import retrofit2.http.GET

interface ImageApi {

    @GET("/task-m-001/list.php")
    suspend fun getImagesUrl(): List<String>
}