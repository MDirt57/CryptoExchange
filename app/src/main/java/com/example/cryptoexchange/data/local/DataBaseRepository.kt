package com.example.cryptoexchange.data.local

import android.content.Context
import android.util.Log
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class DataBaseRepository(context: Context): LocalRepository{

    private val dao = CryptoItemDB.getInstance(context).getDao()

    override suspend fun getCryptoList(): List<CryptoItem> {
        val entities = dao.getItems()
        return CryptoItemMapper.entitiesToCryptoList(entities)
    }

    override suspend fun getCryptoItem(id: Long): CryptoItem {
        val entity = dao.getItem(id)
        return CryptoItemMapper.entityToCryptoItem(entity)
    }

    override suspend fun addCryptoItem(item: CryptoItem) {
        val entity = CryptoItemMapper.cryptoItemToEntity(item)
        dao.addCryptoItem(entity)
    }

    override suspend fun updateCryptoItem(item: CryptoItem) {
        val entity = CryptoItemMapper.cryptoItemToEntity(item)
        dao.updateCryptoItem(entity)
    }

}