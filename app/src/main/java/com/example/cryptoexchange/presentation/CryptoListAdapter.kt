package com.example.cryptoexchange.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import com.example.cryptoexchange.domain.CryptoItem
import com.squareup.picasso.Picasso

class CryptoListAdapter() : RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    var cryptoItemList = listOf<CryptoItem>()
        set(value) {
            field = value
            notifyDataSetChanged() //FIXME
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val crypto = view.findViewById<TextView>(R.id.crypto)
        val currency = view.findViewById<TextView>(R.id.currency)
        val icon = view.findViewById<ImageView>(R.id.icon)
        val price = view.findViewById<TextView>(R.id.price)
        val card = view.findViewById<CardView>(R.id.card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_card, parent, false)
        return ViewHolder(view)
    }



    var onClickListener: ((item: CryptoItem) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CryptoListAdapter.ViewHolder, position: Int) {
        val cryptoItem = cryptoItemList[position]
        with(holder) {
            crypto.text = cryptoItem.crypto_name
            currency.text = cryptoItem.currency_name
            price.text = cryptoItem.price
            Picasso.get().load(BASEIMAGEURL+cryptoItem.icon).into(icon)

            card.setOnClickListener {
                onClickListener?.invoke(cryptoItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return cryptoItemList.size
    }
}