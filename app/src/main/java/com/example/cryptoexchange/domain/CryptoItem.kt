package com.example.cryptoexchange.domain

data class CryptoItem(
    val name: String,
    val icon: String,
    val price: Float,
    val lastUpdate: String,
//    val minimum: Float,
//    val maximum: Float,
//    val lastMarket: String,
    var id: Long = UNDEFINED
)

{
    companion object{
        const val UNDEFINED = 0L
    }
}