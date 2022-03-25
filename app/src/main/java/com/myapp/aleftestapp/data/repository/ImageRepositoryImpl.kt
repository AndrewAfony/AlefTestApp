package com.myapp.aleftestapp.data.repository

import com.myapp.aleftestapp.data.remote.ImageApi
import com.myapp.aleftestapp.domain.repository.ImageRepository
import com.myapp.aleftestapp.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: ImageApi
): ImageRepository {

    override suspend fun getImages(): Resource<List<String>> {
        return try {
            val data = api.getImagesUrl()
            Resource.Success(data = data)
        } catch (e: HttpException) {
            Resource.Error(message = e.localizedMessage ?: "Unknown error")
        } catch (e: IOException) {
            Resource.Error(message = e.localizedMessage ?: "Unknown error")
        }
    }
}