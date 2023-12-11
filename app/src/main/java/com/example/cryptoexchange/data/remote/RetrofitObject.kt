package com.example.cryptoexchange.data.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject(baseUrl: String) {

    private val retrofit: Retrofit
    private val service: GetService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GetService::class.java)
    }

    fun get(fsyms: String, tsyms: String, callback: Callback<CryptoDataResponse>){
        val call: Call<CryptoDataResponse> = service.getData(fsyms, tsyms)
        call.enqueue(callback)
    }

}