package com.example.cryptoexchange.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetService {
    @GET("data/top/totalvolfull")
    fun getData(
        @Query("limit") limit: String,
        @Query("tsym") tsym: String,
        ): Call<CryptoDataResponse>
}