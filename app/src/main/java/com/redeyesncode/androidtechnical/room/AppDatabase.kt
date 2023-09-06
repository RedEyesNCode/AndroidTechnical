package com.redeyesncode.androidtechnical.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun commentDao(): PostDao
}