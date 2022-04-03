package com.waterdrinkreminder.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.FragmentSettingsBinding
import com.waterdrinkreminder.util.DateUtils.ddMMMyyyy
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var dataBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        dataBinding.etvDob.setOnClickListener {
            val constraintsBuilder =
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointBackward.now()).build()
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(R.string.settings_select_date)
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setCalendarConstraints(constraintsBuilder)
                    .build()
            activity?.supportFragmentManager?.let { activityContext ->
                datePicker.apply {
                    show(activityContext, null)
                    addOnPositiveButtonClickListener {
                        val dateString: String =
                            DateFormat.format(ddMMMyyyy, Date(it)).toString()
                        dataBinding.etvDob.setText(dateString)
                    }
                }
            }
        }
        return dataBinding.root
    }
}