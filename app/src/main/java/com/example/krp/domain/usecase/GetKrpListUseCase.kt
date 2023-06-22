package com.example.krp.domain.usecase

import android.content.res.AssetManager
import android.util.Log
import com.example.krp.data.SheetsSettings
import com.example.krp.data.utils.DialogUtil
import com.example.krp.data.utils.NetworkHelper
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetKrpListUseCase(
    private val networkHelper: NetworkHelper,
    private val dialogUtil: DialogUtil,
    private val assets: AssetManager
) {

    suspend fun execute(): List<String> {
        if (networkHelper.isOnline()) {
            return suspendCoroutine { continuation ->
                val service = SheetsSettings.getSheetsService(assets)
                val response = service.spreadsheets().get(SheetsSettings.SHEETS_ID_WRITE).execute()

                val titleList = mutableListOf<String>()
                response.sheets.forEach {titleList.add(it.properties.title)}

                Log.i("OOO", "titleList: $titleList")
                val title0 = response.sheets[0].properties.title
                Log.i("OOO", "title0 = $title0")
                continuation.resume(titleList)
            }

        } else {
            dialogUtil.showDialog("Відсутній інтернет зв'язок")
        }
        return suspendCoroutine {listOf("")}
    }
}