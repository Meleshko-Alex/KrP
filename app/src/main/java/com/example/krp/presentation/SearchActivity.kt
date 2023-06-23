package com.example.krp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krp.R
import com.example.krp.data.room.SheetRow
import com.example.krp.data.utils.DialogUtil
import com.example.krp.data.utils.NetworkHelper
import com.example.krp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var dialog: DialogUtil
    private lateinit var titles: SheetRow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = DialogUtil(this)
        viewModel = SearchViewModel(dialog, NetworkHelper(this))
        val titlesObserver = Observer<SheetRow> { titlesResult ->
            Log.i("OOO", "titlesResult: $titlesResult")
            titles = titlesResult
        }
        viewModel.titles.observe(this, titlesObserver)
        val listRowsObserver = Observer<List<SheetRow>> { listResult ->
            Log.i("OOO", "listResult: $listResult")
            updateRecycler(listResult)
        }
        viewModel.listRows.observe(this, listRowsObserver)

        initUi()
    }

    private fun updateRecycler(listResult: List<SheetRow>) {
        binding.rvResult.layoutManager = LinearLayoutManager(this)
        binding.rvResult.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        binding.rvResult.adapter = CustomRecyclerAdapter(listResult, titles)
    }

    private fun initUi() {
        binding.btnRecord.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    NewRecordActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.btnSearch.setOnClickListener {
            val searchText = binding.etPersonData.text.toString()
            if (searchText.length > 2) {
                viewModel.search(searchText)

            } else {
                dialog.showDialog(getString(R.string.wrong_search))
            }
        }
    }
}