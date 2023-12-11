package com.example.cryptoexchange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CryptoItemDao {

    @Update
    suspend fun updateCryptoItem(item: CryptoItemEntity)

    @Query("SELECT EXISTS(SELECT * FROM cryptoTable WHERE crypto_name==:crypto_name AND currency_name==:currency_name)")
    suspend fun isCryptoItemExist(crypto_name: String, currency_name: String): Boolean

    @Insert
    suspend fun addCryptoItem(item: CryptoItemEntity)

    @Query("SELECT * FROM cryptoTable WHERE id==:id LIMIT 1")
    suspend fun getItem(id: Long): CryptoItemEntity

    @Query("SELECT * FROM cryptoTable ORDER BY id ASC")
    suspend fun getItems(): List<CryptoItemEntity>
}