package com.example.cryptoexchange.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.data.local.DataBaseRepository
import com.example.cryptoexchange.data.remote.CryptoDataResponse
import com.example.cryptoexchange.data.remote.RetrofitDataMapper
import com.example.cryptoexchange.data.remote.RetrofitObject
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.usecases.AddLocalCryptoItem
import com.example.cryptoexchange.domain.usecases.GetLocalCryptoList
import com.example.cryptoexchange.domain.usecases.UpdateLocalCryptoItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val localRepository = DataBaseRepository(application)
    private var remoteRepository: RetrofitObject? = null

    private val _liveData = MutableLiveData<List<CryptoItem>>()
    val liveData: LiveData<List<CryptoItem>>
        get() = _liveData

    private val getLocalCryptoListUseCase = GetLocalCryptoList(localRepository)
    private val addLocalCryptoItemUseCase = AddLocalCryptoItem(localRepository)
    private val updateLocalCryptoItemUseCase = UpdateLocalCryptoItem(localRepository)

    fun getLocalCryptoList(){
        viewModelScope.launch {
            _liveData.value = getLocalCryptoListUseCase.getLocalCryptoList()
        }
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

    fun openRemoteRepo(baseUrl: String){
        remoteRepository = RetrofitObject(baseUrl)
    }

    fun closeRemoteRepo(){
        remoteRepository = null
    }

    fun getRemoteCryptoItem(limit: String, currency_name: String){

        remoteRepository?.get(limit, currency_name, object : Callback<CryptoDataResponse> {
            override fun onResponse(call: Call<CryptoDataResponse>, response: Response<CryptoDataResponse>) {
                val body = response.body()
                Log.d("XXX", "Retrofit response: ${body.toString()}")
                val cryptoList = RetrofitDataMapper.RetrofitDataToCryptoList(body, currency_name)
                viewModelScope.launch {
                    for (cryptoItem in cryptoList){
                        updateLocalCryptoItemUseCase.updateLocalCryptoItem(cryptoItem)
                    }
                }
                getLocalCryptoList()
            }

            override fun onFailure(call: Call<CryptoDataResponse>, t: Throwable) {
                Log.e("XXX", "Retrofit: $t")
            }
        })

    }

}