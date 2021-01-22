package com.bliss.blissandroidchallenge.ui.reposlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bliss.blissandroidchallenge.data.repolist.RepoPagingSource
import com.bliss.blissandroidchallenge.domain.repolist.RepoListUseCases

class RepoListActivityViewModel(
    private val repoListUseCases: RepoListUseCases
): ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 30)) {
        RepoPagingSource(repoListUseCases.getRepoRemoteSource())
    }.flow.cachedIn(viewModelScope)

}