package com.bliss.blissandroidchallenge.data.main.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bliss.blissandroidchallenge.data.main.datasource.local.dao.EmojiDao
import com.bliss.blissandroidchallenge.data.main.datasource.local.entity.EmojiEntity

@Database(entities = [EmojiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun emojiDao(): EmojiDao

}