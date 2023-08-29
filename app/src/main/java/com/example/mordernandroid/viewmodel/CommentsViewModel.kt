package com.example.mordernandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mordernandroid.data.CommentModel
import com.example.mordernandroid.network.CommentApiState
import com.example.mordernandroid.network.Status
import com.example.mordernandroid.repository.CommentsRepository
import com.example.mordernandroid.utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {

    private val repository = CommentsRepository(
        AppConfig.ApiService()
    )

    val commentState = MutableStateFlow(
        CommentApiState(
            Status.LOADING,
            CommentModel(),""
        )
    )

    init {
        getNewComment(1)
    }

    fun getNewComment(id: Int) {
        commentState.value = CommentApiState.loading()

        viewModelScope.launch {
            repository.getComment(id)
                .catch {
                    commentState.value = CommentApiState.error(it.message.toString())
                }
                .collect {
                    commentState.value = CommentApiState.suscess(it.data)
                }
        }
    }
}