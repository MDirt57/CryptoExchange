package com.example.cryptoexchange.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.ActivityCryptoInfoBinding


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
            .replace(R.id.crypto_item_container, fragment)
            .commit()
    }



}