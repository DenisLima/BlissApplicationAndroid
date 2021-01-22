package com.bliss.blissandroidchallenge.data.main.datasource.remote

import com.bliss.blissandroidchallenge.data.main.model.EmojiList
import retrofit2.http.GET

interface MainRemoteSource {

    @GET("emojis")
    suspend fun getEmojis(): EmojiList

}