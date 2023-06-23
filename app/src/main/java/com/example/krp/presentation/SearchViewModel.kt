package com.example.krp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krp.data.room.SheetRow
import com.example.krp.data.utils.DialogUtil
import com.example.krp.data.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val dialogUtil: DialogUtil,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val titles: MutableLiveData<SheetRow> by lazy { MutableLiveData<SheetRow>() }
    val listRows: MutableLiveData<List<SheetRow>> by lazy { MutableLiveData<List<SheetRow>>() }

    private val db = App.getInstance().database
    private val sheetRowDao = db.sheetRowDao()

    fun search(searchText: String) {
        if (networkHelper.isOnline()) {

            viewModelScope.launch(Dispatchers.IO) {
                val titleResult = sheetRowDao.getTitles()
                Log.i("FFF", "titleResult: $titleResult")
                launch(Dispatchers.Main) { titles.value = titleResult }

                val resultList = sheetRowDao.search(searchText)
                Log.i("FFF", "search: $resultList")
                launch(Dispatchers.Main) { listRows.value = resultList }
            }
        } else {
            dialogUtil.showDialog("Відсутній інтернет зв'язок")
        }
    }
}