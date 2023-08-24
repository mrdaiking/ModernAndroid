package com.example.mordernandroid.repository

import com.example.mordernandroid.data.ArticleHeadline
import com.example.mordernandroid.data.UserData
import com.example.mordernandroid.resource.NewsRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class NewsRepository(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val userData: UserData,
    private val defaultDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) {
    /**
     * Returns the favorite latest news applying transformations on the flow.
     * These operations are lazy and don't trigger the flow. They just transform
     * the current value emitted by the flow at that point in time.
     */
    val favoriteLatestNews: Flow<List<ArticleHeadline>> =
        newsRemoteDataSource.latestNews
    // Intermediate operation to filter the list of favorite topics
            .map { news -> news.filter { userData.isFavoriteTopic(it) } }
    // Intermediate operation to save the latest news in the cache
            .onEach { news -> saveInCache(news) }
            // flowOn affects the upstream flow
            .flowOn(defaultDispatcher)
    //If an error happens, emit the last cached values
            .catch { exception -> emit(lastCachedNews()) }

    val latestNews: Flow<List<ArticleHeadline>> = flow<List<ArticleHeadline>> {

    }.flowOn(ioDispatcher)

    private fun lastCachedNews(): List<ArticleHeadline> {
        return mutableListOf<ArticleHeadline>()
    }

    private fun saveInCache(news: List<ArticleHeadline>) {

    }
}