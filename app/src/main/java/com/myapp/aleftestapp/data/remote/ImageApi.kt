package com.myapp.aleftestapp.data.remote

import com.myapp.aleftestapp.domain.model.ImageList
import retrofit2.http.GET

interface ImageApi {

    @GET("/task-m-001/list.php")
    fun getImagesUrl(): ImageList
}