package com.example.cryptoexchange.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface CryptoItemDao {

    @Update
    fun updateCryptoItem(item: CryptoItemEntity)

    @Query("SELECT * FROM cryptoTable WHERE id==:id LIMIT 1")
    fun getItem(id: Long): CryptoItemEntity

    @Query("SELECT * FROM cryptoTable ORDER BY id ASC")
    fun getItems(): List<CryptoItemEntity>
}