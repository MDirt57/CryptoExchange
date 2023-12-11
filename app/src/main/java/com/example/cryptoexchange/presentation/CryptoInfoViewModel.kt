package com.example.cryptoexchange.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.data.local.DataBaseRepository
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoItem
import kotlinx.coroutines.launch

class CryptoInfoViewModel(application: Application): AndroidViewModel(application) {

    private val localRepository = DataBaseRepository(application)

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