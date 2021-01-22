package com.bliss.blissandroidchallenge.data.main.datasource.local

import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getEmojis(): List<EmojiEntity> = appDatabase.emojiDao().getAll()

    override suspend fun insertAll(emojis: List<EmojiEntity>) = appDatabase.emojiDao().insertAll(emojis)
    override suspend fun getUserAvatarByUsername(username: String): UserAvatarEntity = appDatabase.userAvatarDao().getUserAvatarByUsername(username.trim())
    override suspend fun insertUserAvatar(userAvatarEntity: UserAvatarEntity) = appDatabase.userAvatarDao().insertAll(userAvatarEntity)
    override suspend fun getAllUserAvatar(): List<UserAvatarEntity> = appDatabase.userAvatarDao().getAllUserAvatar()
    override suspend fun deleteAvatar(entity: UserAvatarEntity) = appDatabase.userAvatarDao().delete(entity)

}