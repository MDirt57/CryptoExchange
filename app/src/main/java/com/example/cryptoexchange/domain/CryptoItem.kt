package com.example.cryptoexchange.domain

data class CryptoItem(
    val name: String,
    val icon: String,
    val price: Long,
    val lastUpdate: String,
    val minimum: Long,
    val maximum: Long,
    val lastMarket: String,
    var id: Long = UNDEFINED
)

{
    companion object{
        const val UNDEFINED = 0L
    }
}