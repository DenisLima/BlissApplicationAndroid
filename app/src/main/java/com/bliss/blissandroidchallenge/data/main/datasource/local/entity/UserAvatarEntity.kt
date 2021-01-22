package com.bliss.blissandroidchallenge.data.main.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAvatarEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "avatarUrl") val avatarUrl: String
)