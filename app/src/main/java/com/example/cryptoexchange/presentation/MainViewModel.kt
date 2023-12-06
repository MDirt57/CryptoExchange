package com.example.cryptoexchange.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoexchange.data.local.LocalRepositoryImpl
import com.example.cryptoexchange.data.remote.RemoteRepositoryImpl
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository
import com.example.cryptoexchange.domain.RemoteRepository
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoList
import com.example.cryptoexchange.domain.usecases.GetRemoteCryptoList

class MainViewModel : ViewModel() {

    private val localRepository: LocalRepository = LocalRepositoryImpl
    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl

    private val _liveData = MutableLiveData<List<CryptoItem>>()
    val liveData: LiveData<List<CryptoItem>>
        get() = _liveData

    private val getLocalCryptoListUseCase = GetLocalCryptoList(localRepository)
    private val getRemoteCryptoListUseCase = GetRemoteCryptoList(remoteRepository)

    fun getLocalCryptoList(){
        _liveData.value = getLocalCryptoListUseCase()
    }

    fun getRemoteCryptoList(){
        _liveData.value = getRemoteCryptoListUseCase()
    }

}