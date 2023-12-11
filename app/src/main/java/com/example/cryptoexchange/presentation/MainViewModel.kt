package com.example.cryptoexchange.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.data.local.DataBaseRepository
import com.example.cryptoexchange.data.remote.RemoteRepositoryImpl
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.RemoteRepository
import com.example.cryptoexchange.domain.usecases.AddLocalCryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoList
import com.example.cryptoexchange.domain.usecases.GetRemoteCryptoList
import com.example.cryptoexchange.domain.usecases.UpdateLocalCryptoItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

//    private val localRepository: LocalRepository = LocalRepositoryImpl
    private val localRepository = DataBaseRepository(application)
    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl

    private val _liveData = MutableLiveData<List<CryptoItem>>()
    val liveData: LiveData<List<CryptoItem>>
        get() = _liveData

    private val getLocalCryptoListUseCase = GetLocalCryptoList(localRepository)
    private val getRemoteCryptoListUseCase = GetRemoteCryptoList(remoteRepository)
    private val addLocalCryptoItemUseCase = AddLocalCryptoItem(localRepository)
    private val updateLocalCryptoItemUseCase = UpdateLocalCryptoItem(localRepository)

    fun getLocalCryptoList(){
        viewModelScope.launch {
            _liveData.value = getLocalCryptoListUseCase.getLocalCryptoList()
        }
    }

    fun getRemoteCryptoList(){
        _liveData.value = getRemoteCryptoListUseCase()
    }

    fun addLocalCryptoItem(item: CryptoItem){
        viewModelScope.launch {
            addLocalCryptoItemUseCase.addLocalCryptoItem(item)
        }
    }

    fun updateLocalCryptoItem(item: CryptoItem){
        viewModelScope.launch {
            updateLocalCryptoItemUseCase.updateLocalCryptoItem(item)
        }
    }


}