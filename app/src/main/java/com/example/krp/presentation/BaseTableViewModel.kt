package com.example.krp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krp.domain.usecase.GetKrpListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseTableViewModel(private val getKrpListUseCase: GetKrpListUseCase) : ViewModel() {
    val baseTable: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }

    fun getData(): LiveData<List<String>> {
        getBaseTable()
        return baseTable
    }

    private fun getBaseTable() {
        viewModelScope.launch(Dispatchers.IO) {
            val baseTableData = getKrpListUseCase.execute()

            launch(Dispatchers.Main) { baseTable.value = baseTableData }
        }
    }
}