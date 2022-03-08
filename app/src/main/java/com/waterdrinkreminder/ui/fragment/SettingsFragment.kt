package com.waterdrinkreminder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var dataBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        dataBinding.tvDob.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(R.string.settings_select_date)
                    .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
            activity?.supportFragmentManager?.let { activityContext ->
                datePicker.apply {
                    show(activityContext, null)
                    addOnPositiveButtonClickListener {

                    }
                }
            }
        }

        return dataBinding.root
    }
}