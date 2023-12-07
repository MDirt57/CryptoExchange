package com.example.cryptoexchange.data.local

import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

object LocalRepositoryImpl : LocalRepository{

    val testList = listOf(
            CryptoItem("Item1", "", "", "54115.14", "23:12:43", 3L),
            CryptoItem("Item2", "", "", "3441.124", "23:12:43", 4L),
            CryptoItem("Item3", "", "", "0.0001431", "23:12:43", 5L),
            CryptoItem("Item4", "", "", "11.24", "23:12:43", 6L),
            CryptoItem("Item5", "", "", "774", "23:12:43", 7L),
        )

    override fun getCryptoList(): List<CryptoItem> {
        return testList
    }

    override fun getCryptoItem(id: Long): CryptoItem {
        return testList.first { it.id == id }
    }

}