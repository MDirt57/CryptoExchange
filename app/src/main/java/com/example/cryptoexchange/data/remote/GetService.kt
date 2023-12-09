package com.example.cryptoexchange.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetService {
    @GET("data/pricemultifull")
    fun getData(
        @Query("fsyms") fsyms: String,
        @Query("tsyms") tsyms: String,
        ): Call<CryptoDataResponse>
}