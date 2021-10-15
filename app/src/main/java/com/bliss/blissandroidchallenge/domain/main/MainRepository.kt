package com.bliss.blissandroidchallenge.domain.main

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity
import com.bliss.blissandroidchallenge.data.main.model.DEmojiList
import com.bliss.blissandroidchallenge.data.main.model.DUserAvatar
import com.bliss.blissandroidchallenge.domain.model.EmojiList
import com.bliss.blissandroidchallenge.domain.model.UserAvatar


interface MainRepository {

    suspend fun getEmojis(): EmojiList
    suspend fun getEmojisFromDb(): List<EmojiEntity>
    suspend fun insertAll(emojis: List<EmojiEntity>)
    suspend fun getUserAvatar(username: String): UserAvatar
    suspend fun getUserAvatarFromDb(username: String): UserAvatarEntity
    suspend fun insertUserAvatar(userAvatarEntity: UserAvatarEntity)
    suspend fun getAllUserAvatar(): List<UserAvatarEntity>
    suspend fun deleteAvatar(avatarEntity: UserAvatarEntity)
}