package com.example.cryptoexchange.data.local

import android.content.Context
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
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}