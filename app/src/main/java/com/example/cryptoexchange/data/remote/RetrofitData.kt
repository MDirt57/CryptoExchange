package com.example.cryptoexchange.data.remote

data class CryptoDataResponse(
    val RAW: Map<String, Map<String, CryptoInfo>>
)

data class CryptoInfo(
    var PRICE: String? = null,
    var MARKET: String? = null,
    var HIGHHOUR: String? = null,
    var LOWHOUR: String? = null
)