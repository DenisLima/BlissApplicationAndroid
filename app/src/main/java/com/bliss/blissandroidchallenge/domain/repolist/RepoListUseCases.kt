package com.bliss.blissandroidchallenge.domain.repolist

import com.bliss.blissandroidchallenge.data.repolist.model.Repos
import com.bliss.blissandroidchallenge.data.repolist.remote.RepoListRemoteSource

interface RepoListUseCases {

    suspend fun getRepoList(page: Int): List<Repos>
    fun getRepoRemoteSource(): RepoListRemoteSource
}