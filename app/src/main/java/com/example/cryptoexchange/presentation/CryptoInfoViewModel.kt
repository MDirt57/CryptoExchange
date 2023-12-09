package com.example.cryptoexchange.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.data.local.DataBaseRepository
import com.example.cryptoexchange.data.local.LocalRepositoryImpl
import com.example.cryptoexchange.data.remote.RemoteRepositoryImpl
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoList
import kotlinx.coroutines.launch

class CryptoInfoViewModel(application: Application): AndroidViewModel(application) {

//    private val localRepository = LocalRepositoryImpl
    private val localRepository = DataBaseRepository(application)
    private val remoteRepository = RemoteRepositoryImpl

    private val _liveData = MutableLiveData<CryptoItem>()
    val liveData: LiveData<CryptoItem>
        get() = _liveData

    private val getLocalCryptoItemUseCase = GetLocalCryptoItem(localRepository)

    fun getLocalCryptoItem(id: Long){
        viewModelScope.launch {
            _liveData.value = getLocalCryptoItemUseCase.getLocalCryptoItem(id)
        }
    }

}