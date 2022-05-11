package com.waterdrinkreminder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.waterdrinkreminder.R
import com.waterdrinkreminder.databinding.FragmentHomeBinding
import com.waterdrinkreminder.util.Constant.FIFTY_INT
import com.waterdrinkreminder.util.Constant.ONE_HUNDRED_FIFTY_INT
import com.waterdrinkreminder.util.Constant.ONE_HUNDRED_INT
import com.waterdrinkreminder.util.Constant.TWO_HUNDRED_INT
import com.waterdrinkreminder.viewmodel.HomeFagViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), View.OnClickListener {

    private val homeViewModel: HomeFagViewModel by viewModels()

    private lateinit var dataBinding: FragmentHomeBinding

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