package com.example.cryptoexchange.domain

data class CryptoItem(
    val crypto_name: String,
    val currency_name: String,
    val icon: String = "",
    val price: String = "",
    val minimum: String = "",
    val maximum: String = "",
    val lastMarket: String = "",
    val changeDay: String = "",
    val changeHour: String = "",
    val supply: String = "",
    val mktcap: String = "",
    var id: Long = UNDEFINED
)

{
    companion object{
        const val UNDEFINED = 0L
    }
}