package com.waterdrinkreminder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waterdrinkreminder.model.WaterDrankInfo
import com.waterdrinkreminder.util.Constant.DB_WATER_DRANK_INFO

@Database(entities = [WaterDrankInfo::class], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun waterDrankInfoDao(): WaterDrankInfoDao

    companion object {
        private var mInstance: AppDB? = null
        fun getInstance(context: Context): AppDB {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context, AppDB::class.java, DB_WATER_DRANK_INFO)
                    // add migrations here
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance as AppDB
        }
    }
}