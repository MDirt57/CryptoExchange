package com.example.cryptoexchange.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.cryptoexchange.domain.CryptoItem
import com.example.cryptoexchange.domain.LocalRepository

class DataBaseRepository(context: Context): LocalRepository{

    private val dao = CryptoItemDB.getInstance(context).getDao()

    override fun getCryptoList(): LiveData<List<CryptoItem>> {
        return MediatorLiveData<List<CryptoItem>>().apply {
            addSource(dao.getItems()){
                value = CryptoItemMapper.entitiesToCryptoList(it)
            }
        }
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
        if (dao.isCryptoItemExist(item.crypto_name, item.currency_name)){
            dao.updateCryptoItem(entity)
        }else{
            dao.addCryptoItem(entity)
        }

    }

}