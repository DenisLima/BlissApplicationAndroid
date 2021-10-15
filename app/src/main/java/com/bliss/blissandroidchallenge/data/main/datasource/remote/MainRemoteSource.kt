package com.bliss.blissandroidchallenge.data.main.datasource.remote

import com.bliss.blissandroidchallenge.data.main.model.DEmojiList
import com.bliss.blissandroidchallenge.data.main.model.DUserAvatar
import retrofit2.http.GET
import retrofit2.http.Path

interface MainRemoteSource {

    @GET("emojis")
    suspend fun getEmojis(): DEmojiList

    @GET("users/{username}")
    suspend fun getUserAvatar(@Path("username") username: String): DUserAvatar

}