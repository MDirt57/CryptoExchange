package com.example.cryptoexchange.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.domain.CryptoItem

class CryptoListAdapter() : RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    var cryptoItemList = listOf<CryptoItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val icon = view.findViewById<ImageView>(R.id.icon)
        val price = view.findViewById<TextView>(R.id.price)
        val lastUpdate = view.findViewById<TextView>(R.id.lastUpdate)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_card, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CryptoListAdapter.ViewHolder, position: Int) {
        val cryptoItem = cryptoItemList[position]
        with(holder) {
            name.text = cryptoItem.name
            price.text = cryptoItem.price.toString()
            lastUpdate.text = "Час останнього оновлення: ${cryptoItem.lastUpdate}"
        }
    }

    override fun getItemCount(): Int {
        return cryptoItemList.size
    }
}