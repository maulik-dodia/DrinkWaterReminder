package com.waterdrinkreminder.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.FragmentSettingsBinding
import com.waterdrinkreminder.util.DateTimeUtils.checkForDoubleDigit
import com.waterdrinkreminder.util.DateTimeUtils.ddMMMyyyy
import java.time.LocalDateTime
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var dataBinding: FragmentSettingsBinding

    private var selectedDob: Long? = null
    private var selectedGetInBedHour: Int? = null
    private var selectedGetInBedMinute: Int? = null
    private var selectedWakeupHour: Int? = null
    private var selectedWakeupMinute: Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        // Date of Birth
        dataBinding.etvDob.setOnClickListener {
            val constraintsBuilder =
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointBackward.now())
                    .build()
            val prevSelectedDate = selectedDob ?: System.currentTimeMillis()
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(R.string.settings_select_date)
                    .setSelection(prevSelectedDate)
                    .setCalendarConstraints(constraintsBuilder)
                    .build()
            activity?.supportFragmentManager?.let { activityContext ->
                datePicker.apply {
                    show(activityContext, null)
                    addOnPositiveButtonClickListener {
                        selectedDob = it
                        val dateString = DateFormat.format(ddMMMyyyy, Date(it)).toString()
                        dataBinding.etvDob.setText(dateString)
                    }
                }
            }
        }

        // Get in Bed
        dataBinding.etvGetInBed.setOnClickListener {
            val hour = selectedGetInBedHour ?: LocalDateTime.now().hour
            val minute = selectedGetInBedMinute ?: LocalDateTime.now().minute
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(hour)
                .setMinute(minute)
                .build()
            materialTimePicker.addOnPositiveButtonClickListener {
                selectedGetInBedHour = materialTimePicker.hour
                selectedGetInBedMinute = materialTimePicker.minute
                val hourAsText = selectedGetInBedHour?.let { checkForDoubleDigit(it) }
                val minuteAsText = selectedGetInBedMinute?.let { checkForDoubleDigit(it) }
                dataBinding.etvGetInBed.setText(getString(R.string.time_format_hh_colon_mm, hourAsText, minuteAsText))
            }
            materialTimePicker.show(childFragmentManager, MaterialTimePicker::class.java.canonicalName)
        }

        // Wakeup
        dataBinding.etvWakeUp.setOnClickListener {
            val hour = selectedWakeupHour ?: LocalDateTime.now().hour
            val minute = selectedWakeupMinute ?: LocalDateTime.now().minute
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(hour)
                .setMinute(minute)
                .build()
            materialTimePicker.addOnPositiveButtonClickListener {
                selectedWakeupHour = materialTimePicker.hour
                selectedWakeupMinute = materialTimePicker.minute
                val hourAsText = selectedWakeupHour?.let { checkForDoubleDigit(it) }
                val minuteAsText = selectedWakeupMinute?.let { checkForDoubleDigit(it) }
                dataBinding.etvWakeUp.setText(getString(R.string.time_format_hh_colon_mm, hourAsText, minuteAsText))
            }
            materialTimePicker.show(childFragmentManager, MaterialTimePicker::class.java.canonicalName)
        }
        return dataBinding.root
    }
}