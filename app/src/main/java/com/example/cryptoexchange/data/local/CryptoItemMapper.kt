package com.example.cryptoexchange.data.local

import com.example.cryptoexchange.domain.CryptoItem

object CryptoItemMapper {

    fun entityToCryptoItem(entity: CryptoItemEntity): CryptoItem {
        return CryptoItem(
            entity.crypto_name,
            entity.currency_name,
            entity.icon,
            entity.price,
            entity.minimum,
            entity.maximum,
            entity.lastMarket,
            entity.changeDay,
            entity.changeHour,
            entity.supply,
            entity.mktcap,
            id = entity.id,
        )
    }

    fun cryptoItemToEntity(item: CryptoItem): CryptoItemEntity{
        return CryptoItemEntity(
            item.id,
            item.crypto_name,
            item.currency_name,
            item.icon,
            item.price,
            item.minimum,
            item.maximum,
            item.lastMarket,
            item.changeDay,
            item.changeHour,
            item.supply,
            item.mktcap,
        )
    }

    fun entitiesToCryptoList(list: List<CryptoItemEntity>): List<CryptoItem>{
        return list.map{ cryptoItemEntity ->  entityToCryptoItem(cryptoItemEntity) }
    }

}