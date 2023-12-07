package com.example.cryptoexchange.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoexchange.data.local.LocalRepositoryImpl
import com.example.cryptoexchange.data.remote.RemoteRepositoryImpl
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoList

class CryptoInfoViewModel: ViewModel() {

    private val localRepository = LocalRepositoryImpl
    private val remoteRepository = RemoteRepositoryImpl

    private val _liveData = MutableLiveData<CryptoItem>()
    val liveData: LiveData<CryptoItem>
        get() = _liveData

    private val getLocalCryptoItemUseCase = GetLocalCryptoItem(localRepository)

    fun getLocalCryptoItem(id: Long){
        _liveData.value = getLocalCryptoItemUseCase(id)
    }

}