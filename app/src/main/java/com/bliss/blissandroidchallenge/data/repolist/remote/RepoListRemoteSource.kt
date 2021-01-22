package com.bliss.blissandroidchallenge.data.repolist.remote

import com.bliss.blissandroidchallenge.data.repolist.model.Repos
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoListRemoteSource {

    @GET("https://api.github.com/users/google/repos")
    suspend fun getRepoList(@Query("page") page: Int): List<Repos>
}