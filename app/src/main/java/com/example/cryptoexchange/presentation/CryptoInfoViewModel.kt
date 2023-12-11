package com.example.cryptoexchange.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.data.local.DataBaseRepository
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoList
import kotlinx.coroutines.launch

class CryptoInfoViewModel(application: Application): AndroidViewModel(application) {

    private val localRepository = DataBaseRepository(application)
    val liveData: LiveData<List<CryptoItem>>
        get() = getLocalCryptoListUseCase()


    private val getLocalCryptoItemUseCase = GetLocalCryptoItem(localRepository)
    private val getLocalCryptoListUseCase = GetLocalCryptoList(localRepository)

}