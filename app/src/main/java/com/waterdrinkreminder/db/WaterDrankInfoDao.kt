package com.waterdrinkreminder.db

import androidx.room.*
import com.waterdrinkreminder.model.WaterDrankInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDrankInfoDao {

    @Query("select * from WaterDrankInfo")
    fun getAllWaterDrankInfo(): Flow<List<WaterDrankInfo>>

    @Insert
    fun insertWaterDrank(waterDrankInfo: WaterDrankInfo)

    @Update
    fun updateWaterDrank(waterDrankInfo: WaterDrankInfo)

    @Delete
    fun deleteWaterDrank(waterDrankInfo: WaterDrankInfo)
}