package com.example.cryptoexchange.domain

interface LocalRepository {
    fun getCryptoList(): List<CryptoItem>
}