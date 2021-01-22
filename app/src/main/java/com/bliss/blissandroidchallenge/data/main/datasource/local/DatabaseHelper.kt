package com.bliss.blissandroidchallenge.data.main.datasource.local

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity

interface DatabaseHelper {

    suspend fun getEmojis(): List<EmojiEntity>
    suspend fun insertAll(emojis: List<EmojiEntity>)
}