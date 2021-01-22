package com.bliss.blissandroidchallenge.domain.main

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.data.main.model.EmojiList
import com.bliss.blissandroidchallenge.data.main.model.UserAvatar


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

    override suspend fun getUserAvatar(username: String): UserAvatar {
        return mainRepository.getUserAvatar(username)
    }

    override suspend fun getUserAvatarFromDb(username: String): UserAvatarEntity {
        return mainRepository.getUserAvatarFromDb(username)
    }

    override suspend fun insertUserAvatar(userAvatarEntity: UserAvatarEntity) {
        mainRepository.insertUserAvatar(userAvatarEntity)
    }

    override suspend fun getAllUserAvatarFromDb(): List<UserAvatarEntity> {
        return mainRepository.getAllUserAvatar()
    }

    override suspend fun deleteAvatar(avatarEntity: UserAvatarEntity) {
        mainRepository.deleteAvatar(avatarEntity)
    }

}