package com.example.cryptoexchange.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoexchange.R
import com.example.cryptoexchange.databinding.ActivityCryptoInfoBinding
import com.example.cryptoexchange.databinding.FragmentCryptoInfoBinding
import com.example.cryptoexchange.domain.CryptoItem


class CryptoFragment() : Fragment() {


    lateinit var binding: FragmentCryptoInfoBinding
    lateinit var viewModel: CryptoInfoViewModel

    private var cryptoItemId: Long = UNDEFINED

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoInfoBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CryptoInfoViewModel::class.java]
        viewModel.liveData.observe(viewLifecycleOwner){
            load_info(it)
        }

        viewModel.getLocalCryptoItem(cryptoItemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with (requireArguments()){
            cryptoItemId = getLong(CRYPTOITEMID)
        }
    }

    private fun load_info(item: CryptoItem){
        with (binding){
            cryptoName.text = item.crypto_name
            currencyName.text = item.currency_name
            priceInfo.text = item.price
            lastUpdate.text = item.lastUpdate
        }
    }

    companion object {
        fun newInstance(id: Long) = CryptoFragment().apply {
            arguments = Bundle().apply {
                putLong(CRYPTOITEMID, id)
            }
        }
    }
}