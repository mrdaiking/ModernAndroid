package com.example.mordernandroid.repository

import com.example.mordernandroid.data.CommentModel
import com.example.mordernandroid.network.ApiService
import com.example.mordernandroid.network.CommentApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class CommentsRepository(
    private val apiService: ApiService
) {
    suspend fun getComment(id: Int): Flow<CommentApiState<CommentModel>> {
        return flow {
            val comment = apiService.getComments(id)

            // Emit thist data wrapped in
            emit(CommentApiState.suscess(comment))
        }.flowOn(Dispatchers.IO)
    }
}