package com.waterdrinkreminder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WaterDrankInfo(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "water_drank") val waterDrank: Int?,
    @ColumnInfo(name = "date_and_time") val dateAndTime: String?
)