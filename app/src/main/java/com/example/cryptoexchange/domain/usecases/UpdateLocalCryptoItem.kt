package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class UpdateLocalCryptoItem(
    private val repository: LocalRepository
) {
    suspend fun updateLocalCryptoItem(item: CryptoItem){
        repository.updateCryptoItem(item)
    }
}