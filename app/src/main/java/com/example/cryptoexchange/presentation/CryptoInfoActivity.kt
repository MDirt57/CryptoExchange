package com.example.cryptoexchange.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.ActivityCryptoInfoBinding
import com.example.cryptoexchange.databinding.ActivityMainBinding
import com.example.cryptoexchange.domain.CryptoItem

class CryptoInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCryptoInfoBinding
    lateinit var viewModel: CryptoInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[CryptoInfoViewModel::class.java]
        viewModel.liveData.observe(this){
            load_info(it)
        }

        val cryptoItemId = intent.getLongExtra(CRYPTOITEMID, 0L)
        viewModel.getLocalCryptoItem(cryptoItemId)

    }

    private fun load_info(item: CryptoItem){
        with (binding){
            cryptoName.text = item.crypto_name
            currencyName.text = item.currency_name
            priceInfo.text = item.price
            lastUpdate.text = item.lastUpdate
        }
    }

}