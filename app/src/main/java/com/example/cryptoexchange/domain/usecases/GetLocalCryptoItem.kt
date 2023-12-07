package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class GetLocalCryptoItem(
    private val repository: LocalRepository
) {
    operator fun invoke(id: Long): CryptoItem{
        return repository.getCryptoItem(id)
    }
}