package com.example.cryptoexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoexchange.domain.CryptoItem

@Entity(tableName = "cryptoTable")
data class CryptoItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val crypto_name: String,
    val currency_name: String,
    val icon: String,
    val price: String,
    val lastUpdate: String,
    val minimum: String,
    val maximum: String,
    val lastMarket: String

)
