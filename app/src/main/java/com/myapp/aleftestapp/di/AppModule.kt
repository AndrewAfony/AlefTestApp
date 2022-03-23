package com.myapp.aleftestapp.di

import com.myapp.aleftestapp.data.remote.ImageApi
import com.myapp.aleftestapp.data.repository.ImageRepositoryImpl
import com.myapp.aleftestapp.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideImageApi(): ImageApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dev-tasks.alef.im/")
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(api: ImageApi): ImageRepository {
        return ImageRepositoryImpl(api)
    }
}