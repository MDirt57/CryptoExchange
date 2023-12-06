package com.example.cryptoexchange.data.local

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

object LocalRepositoryImpl : LocalRepository{

    val testList = listOf(
            CryptoItem("Item1", "", 3441.124f, "23:12:43"),
            CryptoItem("Item2", "", 3441.124f, "23:12:43"),
            CryptoItem("Item3", "", 3441.124f, "23:12:43"),
            CryptoItem("Item4", "", 3441.124f, "23:12:43"),
            CryptoItem("Item5", "", 3441.124f, "23:12:43"),
        )

    override fun getCryptoList(): List<CryptoItem> {
        return testList
    }

}