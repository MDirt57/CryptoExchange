package com.example.cryptoexchange.data.remote

data class CryptoDataResponse(
    val Data: List<CryptoObject>
)

data class CryptoObject(
    val RAW: Map<String, CryptoInfo>
)

data class CryptoInfo(
    var FROMSYMBOL: String? = null,
    var PRICE: String? = null,
    var MARKET: String? = null,
    var HIGHHOUR: String? = null,
    var LOWHOUR: String? = null,
    var IMAGEURL: String? = null
)

//data class CryptoDataResponse(
//    val Message: String
//)
