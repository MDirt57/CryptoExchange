package com.example.cryptoexchange.data.remote

import android.util.Log
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.presentation.UNDEFINED

//object RetrofitDataMapper {
//
//    fun RetrofitDataToCryptoItem(data: CryptoDataResponse?, item: CryptoItem): CryptoItem?{
//        data?.let{
//            val crypto = item.crypto_name
//            val currency = item.currency_name
//            return CryptoItem(
//                item.crypto_name,
//                item.currency_name,
//                item.icon,
//                data.RAW[crypto]?.get(currency)?.PRICE ?: "",
//                item.lastUpdate,
//                data.RAW[crypto]?.get(currency)?.LOWHOUR ?: "",
//                data.RAW[crypto]?.get(currency)?.HIGHHOUR ?: "",
//                data.RAW[crypto]?.get(currency)?.MARKET ?: "",
//                item.id
//            )
//        }
//        return null
//    }
//}

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