package com.example.cryptoexchange.presentation

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.ActivityMainBinding
import com.example.cryptoexchange.domain.CryptoItem

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoList: RecyclerView
    private lateinit var load_button: Button

    private var testList: List<CryptoItem> = listOf()

    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cryptoList = binding.cryptoList
        cryptoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = CryptoListAdapter()
        cryptoList.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveData.observe(this){
            adapter.cryptoItemList = it
            Log.d(TAG, "MainViewModel liveData observe")
            Log.d(TAG, "$testList")
        }


//        val testList = listOf(
//            CryptoItem("Item1", "", 3441.124f, "23:12:43"),
//            CryptoItem("Item2", "", 3441.124f, "23:12:43"),
//            CryptoItem("Item3", "", 3441.124f, "23:12:43"),
//            CryptoItem("Item4", "", 3441.124f, "23:12:43"),
//            CryptoItem("Item5", "", 3441.124f, "23:12:43"),
//        )


        load_button = binding.loadButton
        load_button.setOnClickListener {
            viewModel.getLocalCryptoList()
        }

        adapter.onClickListener = {
            val intent = Intent(this, CryptoInfoActivity::class.java)
            intent.putExtra(CRYPTOITEMID, it.id)
            startActivity(intent)
        }

    }

    companion object{
        const val TAG = "XXX"
    }
}