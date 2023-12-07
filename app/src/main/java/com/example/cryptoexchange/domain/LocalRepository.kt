package com.example.cryptoexchange.domain

interface LocalRepository {
    fun getCryptoList(): List<CryptoItem>
    fun getCryptoItem(id: Long): CryptoItem
}