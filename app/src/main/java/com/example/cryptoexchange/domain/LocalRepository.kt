package com.example.cryptoexchange.domain

interface LocalRepository {
    suspend fun getCryptoList(): List<CryptoItem>
    suspend fun getCryptoItem(id: Long): CryptoItem
    suspend fun addCryptoItem(item: CryptoItem)
    suspend fun updateCryptoItem(item: CryptoItem)
}