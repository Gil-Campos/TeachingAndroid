package com.example.networking

import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getAllPost() : List<PostStructure>
}