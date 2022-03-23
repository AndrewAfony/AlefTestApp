package com.myapp.aleftestapp.domain.model

import java.util.*

data class Image(
    val id: UUID = UUID.randomUUID(),
    val imageUrl: String
)
