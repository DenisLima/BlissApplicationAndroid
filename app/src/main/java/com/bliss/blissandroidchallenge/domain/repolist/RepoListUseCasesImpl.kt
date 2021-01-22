package com.bliss.blissandroidchallenge.domain.repolist

import com.bliss.blissandroidchallenge.data.repolist.model.Repos
import com.bliss.blissandroidchallenge.data.repolist.remote.RepoListRemoteSource

class RepoListUseCasesImpl(
    private val repoListRepository: RepoListRepository
): RepoListUseCases {

    override suspend fun getRepoList(page: Int): List<Repos> {
        return repoListRepository.getRepoList(page)
    }

    override fun getRepoRemoteSource(): RepoListRemoteSource {
        return repoListRepository.getRepoRemoteSource()
    }
}