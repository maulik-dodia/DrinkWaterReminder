package com.waterdrinkreminder.repository

import com.waterdrinkreminder.db.WaterDrankInfoDao
import com.waterdrinkreminder.model.WaterDrankInfo

class WaterDrankInfoRepo(
    private val dao: WaterDrankInfoDao
) {
    fun getAllWaterDrankInfo() = dao.getAllWaterDrankInfo()

    fun insertWaterDrank(waterDrankInfo: WaterDrankInfo) = dao.insertWaterDrank(waterDrankInfo)

    fun updateWaterDrank(waterDrankInfo: WaterDrankInfo) = dao.updateWaterDrank(waterDrankInfo)

    fun deleteWaterDrank(waterDrankInfo: WaterDrankInfo) = dao.deleteWaterDrank(waterDrankInfo)
}