package com.example.krp.data.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.krp.R
import com.example.krp.data.SheetsSettings
import com.example.krp.data.room.SheetRow
import com.example.krp.presentation.App
import java.util.Date
import kotlin.coroutines.suspendCoroutine


class UpdateDbHelper(private val context: Context, private val assets: AssetManager) {
    private val db = App.getInstance().database
    private val sheetRowDao = db.sheetRowDao()


    suspend fun createOrUpdateBd() {
        return suspendCoroutine {
            val data = SheetsSettings.getSheetsService(assets).spreadsheets()
                .values().get(SheetsSettings.SHEETS_ID_READ, "Список не прибув. до ТЦК!A:R")
                .execute()
            val matrix = data.getValues()

            val sheetRowList = mutableListOf<SheetRow>()

            for (i in 0 until matrix.size) {
                val sheetRow = SheetRow(id = i + 1)
                if (matrix[i].size > 0) sheetRow.column1 = matrix[i][0] as String?
                if (matrix[i].size > 1) sheetRow.column2 = matrix[i][1] as String?
                if (matrix[i].size > 2) sheetRow.column3 = matrix[i][2] as String?
                if (matrix[i].size > 3) sheetRow.column4 = matrix[i][3] as String?
                if (matrix[i].size > 4) sheetRow.column5 = matrix[i][4] as String?
                if (matrix[i].size > 5) sheetRow.column6 = matrix[i][5] as String?
                if (matrix[i].size > 6) sheetRow.column7 = matrix[i][6] as String?
                if (matrix[i].size > 7) sheetRow.column8 = matrix[i][7] as String?
                if (matrix[i].size > 8) sheetRow.column9 = matrix[i][8] as String?
                if (matrix[i].size > 9) sheetRow.column10 = matrix[i][9] as String?
                if (matrix[i].size > 10) sheetRow.column11 = matrix[i][10] as String?
                if (matrix[i].size > 11) sheetRow.column12 = matrix[i][11] as String?
                if (matrix[i].size > 12) sheetRow.column13 = matrix[i][12] as String?
                if (matrix[i].size > 13) sheetRow.column14 = matrix[i][13] as String?
                if (matrix[i].size > 14) sheetRow.column15 = matrix[i][14] as String?
                if (matrix[i].size > 15) sheetRow.column16 = matrix[i][15] as String?
                if (matrix[i].size > 16) sheetRow.column17 = matrix[i][16] as String?
                if (matrix[i].size > 17) sheetRow.column18 = matrix[i][17] as String?
                sheetRowList.add(sheetRow)

                sheetRowDao.insert(sheetRow)
            }
            Log.i("XXX", "FINISH UPDATE")
            saveLastUpdateTime()
        }
    }

    private fun saveLastUpdateTime() {
        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putLong(context.getString(R.string.update_time__key), Date().time
        ).apply()
        Log.i("XXX", "Time is updated")
    }
}