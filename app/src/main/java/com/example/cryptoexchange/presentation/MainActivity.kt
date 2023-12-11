package com.example.cryptoexchange.presentation

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.data.remote.CryptoDataResponse
import com.example.cryptoexchange.data.remote.RetrofitObject
import com.example.cryptoexchange.databinding.ActivityMainBinding
import com.example.cryptoexchange.domain.CryptoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoList: RecyclerView
    private lateinit var load_button: Button
    private var fragmentCryptoInfo: FragmentContainerView? = null

    private var testList: List<CryptoItem> = listOf()

    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    private var retrofitObject: RetrofitObject? = null

    private val isPortrait: Boolean
        get() = fragmentCryptoInfo == null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentCryptoInfo = binding.cryptoItemContainer


        val isActive = true
        CoroutineScope(Dispatchers.Main).launch{
            while (isActive){
                viewModel.getRemoteCryptoItem("10", "USD")
                delay(3000)
            }
        }

        cryptoList = binding.cryptoList
        cryptoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = CryptoListAdapter()
        cryptoList.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveData.observe(this){
            adapter.cryptoItemList = it
            Log.d("XXX", "List: $it")
        }

        val tempItem = CryptoItem(
            "SMT",
            "USD",
            "",
            "34",
            "00:00:23",
            "23",
            "100")

        load_button = binding.loadButton
        load_button.setOnClickListener {
            viewModel.getRemoteCryptoItem("10", "USD")
        }

        adapter.onClickListener = {
            if (isPortrait){
                setupActivity(it.id)
            }else{
                setupFragment(CryptoFragment.newInstance(it.id))
            }
        }


    }


    override fun onStart() {
        super.onStart()
        viewModel.openRemoteRepo("https://min-api.cryptocompare.com/")
    }

    override fun onStop() {
        super.onStop()
        viewModel.closeRemoteRepo()
    }

    private fun setupActivity(id: Long){
        val intent = Intent(this, CryptoInfoActivity::class.java)
        intent.putExtra(CRYPTOITEMID, id)
        startActivity(intent)
    }

    private fun setupFragment(fragment: CryptoFragment){
        supportFragmentManager
            .beginTransaction()
//            .add(R.id.crypto_item_container, fragment)
            .replace(R.id.crypto_item_container, fragment)
            .commit()
    }

    companion object{
        const val TAG = "XXX"
    }
}