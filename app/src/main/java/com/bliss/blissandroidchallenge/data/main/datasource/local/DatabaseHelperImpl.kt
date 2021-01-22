package com.bliss.blissandroidchallenge.data.main.datasource.local

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getEmojis(): List<EmojiEntity> = appDatabase.emojiDao().getAll()

    override suspend fun insertAll(emojis: List<EmojiEntity>) = appDatabase.emojiDao().insertAll(emojis)


}