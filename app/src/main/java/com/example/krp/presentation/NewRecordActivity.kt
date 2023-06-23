package com.example.krp.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.krp.R
import com.example.krp.data.utils.DialogUtil
import com.example.krp.data.utils.NetworkHelper
import com.example.krp.databinding.ActivityNewRecordBinding
import com.example.krp.domain.models.NewRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NewRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewRecordBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var viewModel: NewRecordViewModel
    private val dialog = DialogUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        val krpName = sharedPref.getString(getString(R.string.kpr_key), "")

        viewModel = NewRecordViewModel(krpName!!, DialogUtil(this), NetworkHelper(this))
        val listObserver = Observer<Boolean> { result ->
            Log.i("OOO", "result: $result")
            updateUi(result)
        }
        viewModel.result.observe(this, listObserver)

        initUi()
    }

    private fun initUi() {
        binding.btnSearch.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SearchActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.chbPassengers.setOnCheckedChangeListener { _, isChecked ->
            binding.clAdditionalInfo.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        binding.btnAdd.setOnClickListener {
            var count = Integer.parseInt(binding.tvNumber.text.toString())
            count++
            binding.tvNumber.text = count.toString()
        }

        binding.btnRemove.setOnClickListener {
            var count = Integer.parseInt(binding.tvNumber.text.toString())
            if (count != 0) {
                count--
                binding.tvNumber.text = count.toString()
            }
        }

        binding.btnRecord.setOnClickListener {
            if (isValidRecord()) {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnRecord.isEnabled = false
                val record = createNewRecord()
                viewModel.createNewRecord(record, assets)
            }
        }
    }

    private fun isValidRecord(): Boolean {
        if (binding.etAutoNum.text.toString().isBlank()) {
            dialog.showDialog(getString(R.string.wrong_auto_number))
            return false
        }
        if (binding.etDriverName.text.toString().isBlank()) {
            dialog.showDialog(getString(R.string.wrong_driver_name))
            return false
        }

        return true
    }

    private fun updateUi(isSuccess: Boolean) {
        Log.i("AAA", "isSuccess: $isSuccess")
        binding.btnRecord.isEnabled = true

        binding.progressBar.visibility = View.GONE
        if (isSuccess) {
            binding.etAutoNum.setText("")
            binding.etDriverName.setText("")
            binding.tvNumber.text = ""
            binding.etPassengersNames.setText("")
            binding.etAdditionalInfo.setText("")
        }
    }

    private fun createNewRecord(): NewRecord {
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        binding.progressBar.visibility = View.VISIBLE

        val carNumber = binding.etAutoNum.text.toString()
        Log.i("AAA", "carNumber = $carNumber")
        val driverFullName = binding.etDriverName.text.toString()
        val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val creator = sharedPref.getString(getString(R.string.user_key), "")
        val record = NewRecord(
            carNumber = carNumber,
            driverFullName = driverFullName,
            date = date,
            time = time,
            creator = creator!!
        )
        if (binding.chbPassengers.isChecked) {
            record.numberOfPassengers = binding.tvNumber.text.toString()
            record.passengersFullName = binding.etPassengersNames.text.toString()
            record.additionalInfo = binding.etAdditionalInfo.text.toString()
        }
        Log.i("AAA", "$record")
        return record
    }

}