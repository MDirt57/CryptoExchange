package com.example.cryptoexchange.domain

import androidx.lifecycle.LiveData

interface LocalRepository {
    fun getCryptoList(): LiveData<List<CryptoItem>>
    suspend fun getCryptoItem(id: Long): CryptoItem
    suspend fun addCryptoItem(item: CryptoItem)
    suspend fun updateCryptoItem(item: CryptoItem)
}