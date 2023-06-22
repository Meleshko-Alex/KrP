package com.example.krp.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.krp.R
import com.example.krp.data.utils.DialogUtil
import com.example.krp.data.utils.NetworkHelper
import com.example.krp.databinding.ActivityMainBinding
import com.example.krp.domain.GetKrpListUseCase


const val PASSWORD = "Krp2203+"

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    private val networkHelper = NetworkHelper(this)
    private val dialogUtil by lazy { DialogUtil(this) }
    private val getKrpListUseCase by lazy {
        GetKrpListUseCase(
            networkHelper,
            dialogUtil,
            assets
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val viewModel = BaseTableViewModel(getKrpListUseCase)
        val listObserver = Observer<List<String>> { newList ->
            Log.i("OOO", "onCreate: $newList")
            updateSpinner(newList)
        }
        viewModel.baseTable.observe(this, listObserver)
        viewModel.getData()

        initUi()
    }

    private fun updateSpinner(list: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.spinner_item,
            list
        )
        adapter.setDropDownViewResource(R.layout.spinner_item_list)
        binding.spinner.adapter = adapter

        binding.progressBar.visibility = View.GONE
    }

    private fun initUi() {
        binding.btnEnter.setOnClickListener {
            if (validDate()) {
                saveUserName()
                saveKrpName()

                startActivity(
                    Intent(
                        this,
                        NewRecordActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            }
        }

        binding.btnExit.setOnClickListener {
            finish()
        }
    }

    private fun saveUserName() {
        val editor = sharedPref.edit()
        editor.putString(
            getString(R.string.user_key),
            binding.etUserName.text.toString()
        ).apply()
    }

    private fun saveKrpName() {
        val editor = sharedPref.edit()
        editor.putString(
            getString(R.string.kpr_key),
            binding.spinner.selectedItem?.toString()
        ).apply()
    }

    private fun validDate(): Boolean {
        /*val dialog = DialogUtil(this)
        val userName = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()

        if (binding.spinner.selectedItem == null) {
            dialog.showDialog(getString(R.string.no_krp))
            return false
        }
        if (userName.isBlank()) {
            dialog.showDialog(getString(R.string.no_user))
            return false
        }
        if (password != PASSWORD) {
            dialog.showDialog(getString(R.string.wrong_password))
            return false
        }*/

        return true
    }
}