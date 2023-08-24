package com.example.mordernandroid.data

data class UserData(
    val isFavoriteTopic: Boolean
) {
    fun isFavoriteTopic(article: ArticleHeadline): Boolean {
        return true
    }
}
