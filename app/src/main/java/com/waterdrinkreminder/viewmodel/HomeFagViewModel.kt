package com.waterdrinkreminder.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeFagViewModel : ViewModel() {

    var totalWaterDrinkTarget = 2070F

    private val _stateFlowTotalWaterDrank = MutableStateFlow(1050)
    val stateFlowTotalWaterDrank = _stateFlowTotalWaterDrank.asStateFlow()

    private val _stateFlowTotalWaterDrankPercent = MutableStateFlow(0F)
    val stateFlowTotalWaterDrankPercent = _stateFlowTotalWaterDrankPercent.asStateFlow()

    fun calculatePercent(milliLitresToAdd: Int) {
        _stateFlowTotalWaterDrank.value += milliLitresToAdd
        val totalWaterDrankPercent = (_stateFlowTotalWaterDrank.value / totalWaterDrinkTarget) * 100
        _stateFlowTotalWaterDrankPercent.value = totalWaterDrankPercent
    }
}