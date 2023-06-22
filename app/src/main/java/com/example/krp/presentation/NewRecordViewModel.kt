package com.example.krp.presentation

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krp.data.SheetsSettings
import com.example.krp.data.utils.DialogUtil
import com.example.krp.data.utils.NetworkHelper
import com.example.krp.domain.models.NewRecord
import com.google.api.services.sheets.v4.model.AppendValuesResponse
import com.google.api.services.sheets.v4.model.ValueRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NewRecordViewModel(
    private val krpName: String,
    private val dialogUtil: DialogUtil,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val result: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun getData(): LiveData<Boolean> {
        return result
    }

    fun createNewRecord(newRecord: NewRecord, assets: AssetManager) {
        viewModelScope.launch(Dispatchers.IO) {
            val valueResponse = postNewRecord(newRecord, assets)
            launch(Dispatchers.Main) { result.value = valueResponse != null }
        }
    }

    private suspend fun postNewRecord(newRecord: NewRecord, assets: AssetManager): AppendValuesResponse?{
        if (networkHelper.isOnline()) {
            return suspendCoroutine { continuation ->
                val appendBody = ValueRange().setValues(
                    listOf(
                        listOf<Any>(
                            newRecord.date,
                            newRecord.time,
                            newRecord.creator,
                            newRecord.carNumber,
                            newRecord.driverFullName,
                            newRecord.numberOfPassengers,
                            newRecord.passengersFullName,
                            newRecord.additionalInfo
                        )
                    )
                )
                val appendResult: AppendValuesResponse =
                    SheetsSettings.getSheetsService(assets).spreadsheets()
                        .values()
                        .append(SheetsSettings.SHEETS_ID_WRITE, "$krpName!A2", appendBody)
                        .setValueInputOption("USER_ENTERED")
                        .setInsertDataOption("INSERT_ROWS")
                        .setIncludeValuesInResponse(true)
                        .execute()

                continuation.resume(appendResult)
            }
        } else {
            dialogUtil.showDialog("Відсутній інтернет зв'язок")
        }
        return suspendCoroutine {null}
    }
}