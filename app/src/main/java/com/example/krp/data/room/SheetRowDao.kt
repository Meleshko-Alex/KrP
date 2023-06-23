package com.example.krp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SheetRowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sheetRow: SheetRow)

    @Query("SELECT * FROM sheetrow")
    suspend fun getAll(): List<SheetRow>

    @Query("SELECT * FROM sheetrow WHERE id = 1")
    suspend fun getTitles(): SheetRow

    @Query("SELECT * FROM sheetrow WHERE column_1 LIKE :query || '%'")
    suspend fun search(query: String): List<SheetRow>
}