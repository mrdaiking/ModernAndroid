package com.example.mordernandroid.network

import com.example.mordernandroid.data.CommentModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/comments/{id}")
    suspend fun getComments(@Path("id") id: Int): CommentModel
}