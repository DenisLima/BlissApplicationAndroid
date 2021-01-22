package com.bliss.blissandroidchallenge.data.main.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity

@Dao
interface EmojiDao {

    @Query("SELECT * FROM emojientity")
    suspend fun getAll(): List<EmojiEntity>

    @Insert
    suspend fun insertAll(emojis: List<EmojiEntity>)

    @Delete
    suspend fun delete(emoji: EmojiEntity)

}