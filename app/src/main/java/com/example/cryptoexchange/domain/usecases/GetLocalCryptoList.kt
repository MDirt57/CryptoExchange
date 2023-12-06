package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class GetLocalCryptoList(
    private val repository: LocalRepository
) {
    operator fun invoke(): List<CryptoItem>{
        return repository.getCryptoList()
    }
}