package com.example.krp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SheetRow (
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "column_1") var column1: String?= "",
    @ColumnInfo(name = "column_2") var column2: String?= "",
    @ColumnInfo(name = "column_3") var column3: String?= "",
    @ColumnInfo(name = "column_4") var column4: String?= "",
    @ColumnInfo(name = "column_5") var column5: String?= "",
    @ColumnInfo(name = "column_6") var column6: String?= "",
    @ColumnInfo(name = "column_7") var column7: String?= "",
    @ColumnInfo(name = "column_8") var column8: String?= "",
    @ColumnInfo(name = "column_9") var column9: String?= "",
    @ColumnInfo(name = "column_10") var column10: String?= "",
    @ColumnInfo(name = "column_11") var column11: String?= "",
    @ColumnInfo(name = "column_12") var column12: String?= "",
    @ColumnInfo(name = "column_13") var column13: String?= "",
    @ColumnInfo(name = "column_14") var column14: String?= "",
    @ColumnInfo(name = "column_15") var column15: String?= "",
    @ColumnInfo(name = "column_16") var column16: String?= "",
    @ColumnInfo(name = "column_17") var column17: String?= "",
    @ColumnInfo(name = "column_18") var column18: String? = ""
)