package com.example.cryptoexchange.data.remote

import com.example.cryptoexchange.domain.CryptoItem

object RetrofitDataMapper {

    fun RetrofitDataToCryptoItem(data: CryptoDataResponse?, item: CryptoItem): CryptoItem?{
        data?.let{
            val crypto = item.crypto_name
            val currency = item.currency_name
            return CryptoItem(
                item.crypto_name,
                item.currency_name,
                item.icon,
                data.RAW[crypto]?.get(currency)?.PRICE ?: "",
                item.lastUpdate,
                data.RAW[crypto]?.get(currency)?.LOWHOUR ?: "",
                data.RAW[crypto]?.get(currency)?.HIGHHOUR ?: "",
                data.RAW[crypto]?.get(currency)?.MARKET ?: "",
                item.id
            )
        }
        return null
    }
}