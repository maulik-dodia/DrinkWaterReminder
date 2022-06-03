package com.waterdrinkreminder.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.FragmentHomeBinding
import com.waterdrinkreminder.util.Constant.FIFTY_INT
import com.waterdrinkreminder.util.Constant.ONE_HUNDRED_FIFTY_INT
import com.waterdrinkreminder.util.Constant.ONE_HUNDRED_INT
import com.waterdrinkreminder.util.Constant.TWO_HUNDRED_INT
import com.waterdrinkreminder.util.DateTimeUtils
import com.waterdrinkreminder.viewmodel.HomeFagViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeFragment : Fragment(), View.OnClickListener {

    private val homeViewModel: HomeFagViewModel by viewModels()

    private lateinit var dataBinding: FragmentHomeBinding

    private var selectedStartTimeReminderHour: Int? = null
    private var selectedStartTimeReminderMinute: Int? = null
    private var selectedEndTimeReminderHour: Int? = null
    private var selectedEndTimeReminderMinute: Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.apply {
                    calculatePercent(0)
                    stateFlowTotalWaterDrankPercent.collectLatest { percent ->
                        dataBinding.tvTotalWaterDrankPercent.text =
                            getString(R.string.placeholder_percentage, percent)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.stateFlowTotalWaterDrank.collectLatest { totalWaterDrank ->
                    dataBinding.tvTotalWaterDrank.text = totalWaterDrank.toString()
                }
            }
        }
        dataBinding.apply {

            tvTotalWaterDrinkTarget.text = homeViewModel.totalWaterDrinkTarget.toInt().toString()

            // Start Time Reminder
            dataBinding.etvStartReminder.setOnClickListener {
                val hour = selectedStartTimeReminderHour ?: LocalDateTime.now().hour
                val minute = selectedStartTimeReminderMinute ?: LocalDateTime.now().minute
                val materialTimePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(hour)
                    .setMinute(minute)
                    .build()
                materialTimePicker.addOnPositiveButtonClickListener {
                    selectedStartTimeReminderHour = materialTimePicker.hour
                    selectedStartTimeReminderMinute = materialTimePicker.minute
                    val hourAsText = selectedStartTimeReminderHour?.let {
                        DateTimeUtils.checkForDoubleDigit(
                            it
                        )
                    }
                    val minuteAsText = selectedStartTimeReminderMinute?.let {
                        DateTimeUtils.checkForDoubleDigit(
                            it
                        )
                    }
                    dataBinding.etvStartReminder.setText(
                        getString(
                            R.string.time_format_hh_colon_mm,
                            hourAsText,
                            minuteAsText
                        )
                    )
                }
                materialTimePicker.show(
                    childFragmentManager,
                    MaterialTimePicker::class.java.canonicalName
                )
            }

            // End Time Reminder
            dataBinding.etvEndReminder.setOnClickListener {
                val hour = selectedEndTimeReminderHour ?: LocalDateTime.now().hour
                val minute = selectedEndTimeReminderMinute ?: LocalDateTime.now().minute
                val materialTimePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(hour)
                    .setMinute(minute)
                    .build()
                materialTimePicker.addOnPositiveButtonClickListener {
                    selectedEndTimeReminderHour = materialTimePicker.hour
                    selectedEndTimeReminderMinute = materialTimePicker.minute
                    val hourAsText = selectedEndTimeReminderHour?.let {
                        DateTimeUtils.checkForDoubleDigit(
                            it
                        )
                    }
                    val minuteAsText = selectedEndTimeReminderMinute?.let {
                        DateTimeUtils.checkForDoubleDigit(
                            it
                        )
                    }
                    dataBinding.etvEndReminder.setText(
                        getString(
                            R.string.time_format_hh_colon_mm,
                            hourAsText,
                            minuteAsText
                        )
                    )
                }
                materialTimePicker.show(
                    childFragmentManager,
                    MaterialTimePicker::class.java.canonicalName
                )
            }
        }
        setonClickListener()
        return dataBinding.root
    }

    private fun setonClickListener() {
        dataBinding.apply {
            btn50Ml.setOnClickListener(this@HomeFragment)
            btn100Ml.setOnClickListener(this@HomeFragment)
            btn150Ml.setOnClickListener(this@HomeFragment)
            btn200Ml.setOnClickListener(this@HomeFragment)
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                dataBinding.btn50Ml.id -> {
                    homeViewModel.calculatePercent(FIFTY_INT)
                }
                dataBinding.btn100Ml.id -> {
                    homeViewModel.calculatePercent(ONE_HUNDRED_INT)
                }
                dataBinding.btn150Ml.id -> {
                    homeViewModel.calculatePercent(ONE_HUNDRED_FIFTY_INT)
                }
                dataBinding.btn200Ml.id -> {
                    homeViewModel.calculatePercent(TWO_HUNDRED_INT)
                }
            }
        }
    }
}