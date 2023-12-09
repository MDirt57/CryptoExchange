package com.example.cryptoexchange.data.local

import android.content.Context
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class DataBaseRepository(context: Context): LocalRepository{

    private val dao = CryptoItemDB.getInstance(context).getDao()

    override fun getCryptoList(): List<CryptoItem> {
        TODO("Not yet implemented")
    }

    override fun getCryptoItem(id: Long): CryptoItem {
        TODO("Not yet implemented")
    }

}