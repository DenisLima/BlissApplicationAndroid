package com.bliss.blissandroidchallenge.data.main

import com.bliss.blissandroidchallenge.domain.main.MainRepository
import com.bliss.blissandroidchallenge.data.main.datasource.local.DatabaseHelper
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.remote.MainRemoteSource
import com.bliss.blissandroidchallenge.data.main.model.EmojiList

class MainRepositoryImpl(
    private val mainRemoteSource: MainRemoteSource,
    private val databaseHelper: DatabaseHelper
): MainRepository {

    override suspend fun getEmojis(): EmojiList {
        return mainRemoteSource.getEmojis()
    }

    override suspend fun getEmojisFromDb(): List<EmojiEntity> {
        return databaseHelper.getEmojis()
    }

    override suspend fun insertAll(emojis: List<EmojiEntity>) {
        databaseHelper.insertAll(emojis)
    }

}