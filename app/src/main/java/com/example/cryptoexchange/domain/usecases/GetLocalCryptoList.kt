package com.example.cryptoexchange.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class GetLocalCryptoList(
    private val repository: LocalRepository
) {
    operator fun invoke(): LiveData<List<CryptoItem>>{
        return repository.getCryptoList()
    }

}