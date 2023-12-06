package com.example.cryptoexchange.presentation

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.domain.CryptoItem

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cryptoList = findViewById(R.id.cryptoList)
        cryptoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val testList = listOf(
            CryptoItem("Item1", "", 3441.124f, "23:12:43"),
            CryptoItem("Item2", "", 3441.124f, "23:12:43"),
            CryptoItem("Item3", "", 3441.124f, "23:12:43"),
            CryptoItem("Item4", "", 3441.124f, "23:12:43"),
            CryptoItem("Item5", "", 3441.124f, "23:12:43"),
        )

        val adapter = CryptoListAdapter()
        cryptoList.adapter = adapter
        adapter.cryptoItemList = testList


    }
}