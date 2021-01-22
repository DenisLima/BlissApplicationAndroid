package com.bliss.blissandroidchallenge.data.repolist

import androidx.paging.PagingSource
import com.bliss.blissandroidchallenge.data.repolist.remote.RepoListRemoteSource
import com.bliss.blissandroidchallenge.data.repolist.model.Repos
import java.lang.Exception

class RepoPagingSource(
    private val repoListRemoteSource: RepoListRemoteSource
): PagingSource<Int, Repos>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repos> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = repoListRemoteSource.getRepoList(currentLoadingPageKey)
            val responseData = mutableListOf<Repos>()
            responseData.addAll(response)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}