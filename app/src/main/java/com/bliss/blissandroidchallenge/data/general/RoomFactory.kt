package com.bliss.blissandroidchallenge.data.general

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified T : RoomDatabase> createRoomDb(context: Context, dbName: String): T {
    return Room
        .databaseBuilder(context.applicationContext, T::class.java, dbName)
        .fallbackToDestructiveMigration()
        .build()
}