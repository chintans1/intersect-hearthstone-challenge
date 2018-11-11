package com.chintan.hearthstone.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chintan.hearthstone.R
import com.chintan.hearthstone.models.HearthstoneCard
import kotlinx.android.synthetic.main.hearthstone_card.view.*

class HearthstoneCardAdapter(private val hearthstoneCards: List<HearthstoneCard>) :
    RecyclerView.Adapter<HearthstoneCardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage = itemView.hearthstone_card_image as ImageView
        val cardName = itemView.hearthstone_card_name as TextView
        val cardType = itemView.hearthstone_card_type as TextView
        val cardPlayerClass = itemView.hearthstone_card_player_class as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hearthstone_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val card = hearthstoneCards[position]
        viewHolder.cardImage.setImageResource(R.drawable.ic_hearthstone_card)
        viewHolder.cardName.text = card.name
        viewHolder.cardType.text = card.type
        viewHolder.cardPlayerClass.text = card.playerClass
    }

    override fun getItemCount(): Int {
        return hearthstoneCards.size
    }
}
