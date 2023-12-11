package com.example.cryptoexchange.data.remote

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.presentation.UNDEFINED

object RetrofitDataMapper {

    fun RetrofitDataToCryptoList(data: CryptoDataResponse?, currency: String): List<CryptoItem> {
        val cryptoList = mutableListOf<CryptoItem>()
        data?.let {
            for (raw in data.Data) {
//                Log.d("XXX", "Raw: ${raw.toString()}")
                raw.RAW?.let {
                    with(it[currency]!!) {
                        cryptoList.add(
                            CryptoItem(
                                FROMSYMBOL ?: "",
                                currency,
                                IMAGEURL ?: "",
                                PRICE ?: "",
                                LOWHOUR ?: "",
                                HIGHHOUR ?: "",
                                MARKET ?: "",
                                UNDEFINED
                            ).apply {
                                id = (crypto_name.hashCode() + currency_name.hashCode())%65521.toLong()
                            }
                        )
                    }
                }
            }
        }
        return cryptoList
    }

}