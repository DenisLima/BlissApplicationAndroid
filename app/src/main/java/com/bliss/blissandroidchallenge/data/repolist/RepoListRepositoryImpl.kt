package com.bliss.blissandroidchallenge.data.repolist

import com.bliss.blissandroidchallenge.domain.repolist.RepoListRepository
import com.bliss.blissandroidchallenge.data.repolist.remote.RepoListRemoteSource
import com.bliss.blissandroidchallenge.data.repolist.model.Repos

class RepoListRepositoryImpl(
    private val repoListRemoteSource: RepoListRemoteSource
): RepoListRepository {
    override suspend fun getRepoList(page: Int): List<Repos> {
        return repoListRemoteSource.getRepoList(page)
    }

    override fun getRepoRemoteSource(): RepoListRemoteSource {
        return repoListRemoteSource
    }
}