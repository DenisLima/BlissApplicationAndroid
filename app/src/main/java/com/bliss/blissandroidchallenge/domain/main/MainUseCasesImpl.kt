package com.bliss.blissandroidchallenge.domain.main

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.model.EmojiList


class MainUseCasesImpl(
    private val mainRepository: MainRepository
): MainUseCases {

    override suspend fun getEmojis(): EmojiList {
        return mainRepository.getEmojis()
    }

    override suspend fun getEmojisFromDb(): List<EmojiEntity> {
        return mainRepository.getEmojisFromDb()
    }

    override suspend fun insertAll(emojis: List<EmojiEntity>) {
        mainRepository.insertAll(emojis)
    }

}