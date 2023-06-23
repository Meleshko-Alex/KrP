package com.example.krp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SheetRow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sheetRowDao(): SheetRowDao
}