package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.RemoteRepository

class GetRemoteCryptoList(
    private val repository: RemoteRepository
){
    operator fun invoke(): List<CryptoItem>{
        return repository.getCryptoList()
    }
}