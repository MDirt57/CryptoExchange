package com.example.cryptoexchange.domain

interface RemoteRepository {
    fun getCryptoList(): List<CryptoItem>
}