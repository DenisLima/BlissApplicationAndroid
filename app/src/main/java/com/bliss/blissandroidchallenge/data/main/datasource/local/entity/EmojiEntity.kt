package com.bliss.blissandroidchallenge.data.main.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmojiEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "emojiName") val emojiName: String,
    @ColumnInfo(name = "urlEmoji") val urlEmoji: String
)