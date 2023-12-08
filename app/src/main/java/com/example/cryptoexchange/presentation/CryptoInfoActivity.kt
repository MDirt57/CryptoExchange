package com.example.cryptoexchange.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.ActivityCryptoInfoBinding
import com.example.cryptoexchange.databinding.ActivityMainBinding
import com.example.cryptoexchange.domain.CryptoItem

class CryptoInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCryptoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("XXX", binding.root.toString())


        val cryptoItemId = intent.getLongExtra(CRYPTOITEMID, 0L)
        if (savedInstanceState == null){
            setupFragment(CryptoFragment.newInstance(cryptoItemId))
        }

    }

    private fun setupFragment(fragment: CryptoFragment){
        supportFragmentManager
            .beginTransaction()
//            .add(R.id.crypto_item_container, fragment)
            .replace(R.id.crypto_item_container, fragment)
            .commit()
    }



}