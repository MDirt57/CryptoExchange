package com.example.cryptoexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptoTable")
data class CryptoItemEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Long,

    val crypto_name: String,
    val currency_name: String,
    val icon: String,
    val price: String,
    val minimum: String,
    val maximum: String,
    val lastMarket: String

)
