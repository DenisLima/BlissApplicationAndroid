package com.bliss.blissandroidchallenge.data.main.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.UserAvatarEntity

@Dao
interface UserAvatarDao {

    @Query("SELECT * FROM useravatarentity WHERE login LIKE :username")
    suspend fun getUserAvatarByUsername(username: String): UserAvatarEntity

    @Insert
    suspend fun insertAll(userAvatarEntity: UserAvatarEntity)

    @Delete
    suspend fun delete(userAvatar: UserAvatarEntity)

    @Query("SELECT * FROM useravatarentity")
    suspend fun getAllUserAvatar(): List<UserAvatarEntity>
}