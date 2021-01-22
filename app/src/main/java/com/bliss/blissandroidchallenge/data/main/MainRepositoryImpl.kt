package com.bliss.blissandroidchallenge.data.main

import com.bliss.blissandroidchallenge.domain.main.MainRepository
import com.bliss.blissandroidchallenge.data.main.datasource.local.DatabaseHelper
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.data.main.datasource.remote.MainRemoteSource
import com.bliss.blissandroidchallenge.data.main.model.EmojiList
import com.bliss.blissandroidchallenge.data.main.model.UserAvatar

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

    override suspend fun getUserAvatar(username: String): UserAvatar {
        return mainRemoteSource.getUserAvatar(username)
    }

    override suspend fun getUserAvatarFromDb(username: String): UserAvatarEntity {
        return databaseHelper.getUserAvatarByUsername(username)
    }

    override suspend fun insertUserAvatar(userAvatarEntity: UserAvatarEntity) {
        databaseHelper.insertUserAvatar(userAvatarEntity)
    }

    override suspend fun getAllUserAvatar(): List<UserAvatarEntity> {
        return databaseHelper.getAllUserAvatar()
    }

    override suspend fun deleteAvatar(avatarEntity: UserAvatarEntity) {
        databaseHelper.deleteAvatar(avatarEntity)
    }
}