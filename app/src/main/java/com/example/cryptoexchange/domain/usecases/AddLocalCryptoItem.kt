package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class AddLocalCryptoItem(
    private val repository: LocalRepository
) {
    suspend fun addLocalCryptoItem(item: CryptoItem){
        repository.addCryptoItem(item)
    }
}