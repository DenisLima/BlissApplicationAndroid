package com.bliss.blissandroidchallenge.data.main.datasource.local

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity

interface DatabaseHelper {

    suspend fun getEmojis(): List<EmojiEntity>
    suspend fun insertAll(emojis: List<EmojiEntity>)
    suspend fun getUserAvatarByUsername(username: String): UserAvatarEntity
    suspend fun insertUserAvatar(userAvatarEntity: UserAvatarEntity)
    suspend fun getAllUserAvatar(): List<UserAvatarEntity>
    suspend fun deleteAvatar(entity: UserAvatarEntity)
}