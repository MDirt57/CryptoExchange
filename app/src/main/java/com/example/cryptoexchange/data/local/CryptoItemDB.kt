package com.example.cryptoexchange.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoItemEntity::class], version = 1)
abstract class CryptoItemDB : RoomDatabase(){

    abstract fun getDao(): CryptoItemDao

    companion object {
        @Volatile
        private var INSTANCE: CryptoItemDB? = null

        fun getInstance(context: Context): CryptoItemDB{
            return INSTANCE ?: synchronized(this){
                INSTANCE?.let {return it}
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoItemDB::class.java,
                    "crypto_database"
                )
                    .apply {
                        if (isDatabaseInit(context)){
                            Log.d("XXX", "database will be created")
                            fallbackToDestructiveMigration()
                            createFromAsset("database/base_cryptodata.db")
                            markDBInit(context)
                        }
                    }
                    .build()
                INSTANCE = instance
                Log.d("XXX", instance.toString())
                instance
            }
        }

        private val DBPREFS = "database"
        private val DATABASE_INIT = "db_init"

        private fun isDatabaseInit(context: Context): Boolean{
            val prefs = context.getSharedPreferences(DBPREFS, Context.MODE_PRIVATE)
            return prefs.getBoolean(DATABASE_INIT, false)
        }

        private fun markDBInit(context: Context){
            val prefs = context.getSharedPreferences(DBPREFS, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(DATABASE_INIT, true).apply()
        }
    }


}