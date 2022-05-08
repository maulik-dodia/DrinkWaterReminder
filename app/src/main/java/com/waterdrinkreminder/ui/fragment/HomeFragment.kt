package com.waterdrinkreminder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.FragmentHomeBinding
import com.waterdrinkreminder.util.Constant.FIFTY_INT
import com.waterdrinkreminder.util.Constant.ONE_HUNDRED_FIFTY_INT
import com.waterdrinkreminder.util.Constant.ONE_HUNDRED_INT
import com.waterdrinkreminder.util.Constant.TWO_HUNDRED_INT

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding

    private var totalWaterDrank = 1050
    private var totalWaterDrinkTarget = 2070F

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        calculatePercent(0)

        dataBinding.btn50Ml.setOnClickListener {
            calculatePercent(FIFTY_INT)
        }

        dataBinding.btn100Ml.setOnClickListener {
            calculatePercent(ONE_HUNDRED_INT)
        }

        dataBinding.btn150Ml.setOnClickListener {
            calculatePercent(ONE_HUNDRED_FIFTY_INT)
        }

        dataBinding.btn200Ml.setOnClickListener {
            calculatePercent(TWO_HUNDRED_INT)
        }

        return dataBinding.root
    }

    private fun calculatePercent(milliLitresToAdd: Int) {
        totalWaterDrank += milliLitresToAdd
        val totalWaterDrankPercent = (totalWaterDrank / totalWaterDrinkTarget) * 100
        dataBinding.apply {
            tvTotalWaterDrank.text = totalWaterDrank.toString()
            tvTotalWaterDrankPercent.text =
                getString(R.string.placeholder_percentage, totalWaterDrankPercent.toFloat())
        }
    }
}