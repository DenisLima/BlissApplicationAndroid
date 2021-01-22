package com.bliss.blissandroidchallenge.data.main.datasource.remote

import com.bliss.blissandroidchallenge.data.main.model.EmojiList
import com.bliss.blissandroidchallenge.data.main.model.UserAvatar
import retrofit2.http.GET
import retrofit2.http.Path

interface MainRemoteSource {

    @GET("emojis")
    suspend fun getEmojis(): EmojiList

    @GET("users/{username}")
    suspend fun getUserAvatar(@Path("username") username: String): UserAvatar

}