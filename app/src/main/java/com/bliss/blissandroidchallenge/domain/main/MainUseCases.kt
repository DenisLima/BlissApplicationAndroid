package com.bliss.blissandroidchallenge.domain.main

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.model.EmojiList

interface MainUseCases {

    suspend fun getEmojis(): EmojiList
    suspend fun getEmojisFromDb(): List<EmojiEntity>
    suspend fun insertAll(emojis: List<EmojiEntity>)

}