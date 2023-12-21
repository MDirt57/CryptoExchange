package com.example.cryptoexchange.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoList: RecyclerView
    private var fragmentCryptoInfo: FragmentContainerView? = null

    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    private var isActive: Boolean = false

    private val isPortrait: Boolean
        get() = fragmentCryptoInfo == null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentCryptoInfo = binding.cryptoItemContainer


        CoroutineScope(Dispatchers.Main).launch{
            while (isActive){
                viewModel.getRemoteCryptoItem("100", "USD")
                delay(5000)
            }
        }

        cryptoList = binding.cryptoList
        cryptoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = CryptoListAdapter()
        cryptoList.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveData.observe(this){
            adapter.submitList(it)
            Log.d("XXX", "List: $it")
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
        viewModel.openRemoteRepo(BASEURL)
        isActive = true
    }

    override fun onStop() {
        super.onStop()
        viewModel.closeRemoteRepo()
        isActive = false
    }

    private fun setupActivity(id: Long){
        val intent = Intent(this, CryptoInfoActivity::class.java)
        intent.putExtra(CRYPTOITEMID, id)
        startActivity(intent)
    }

    private fun setupFragment(fragment: CryptoFragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.crypto_item_container, fragment)
            .commit()
    }

    companion object{
        const val TAG = "XXX"
    }
}