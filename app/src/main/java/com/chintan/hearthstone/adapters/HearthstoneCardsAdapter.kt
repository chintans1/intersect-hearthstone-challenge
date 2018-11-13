package com.chintan.hearthstone.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.chintan.hearthstone.R
import com.chintan.hearthstone.models.HearthstoneCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hearthstone_card.view.*

class HearthstoneCardsAdapter(private val allHearthstoneCards: List<HearthstoneCard>) :
    RecyclerView.Adapter<HearthstoneCardsAdapter.HearthstoneCardViewHolder>(), Filterable {

    private var filteredHearthstoneCards: List<HearthstoneCard> = ArrayList(allHearthstoneCards)

    class HearthstoneCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardImage = itemView.hearthstone_card_image as ImageView
        val cardName = itemView.hearthstone_card_name as TextView
        val cardType = itemView.hearthstone_card_type as TextView
        val cardPlayerClass = itemView.hearthstone_card_player_class as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HearthstoneCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hearthstone_card, parent, false)
        return HearthstoneCardViewHolder(view)
    }

    override fun onBindViewHolder(hearthstoneCardViewHolder: HearthstoneCardViewHolder, position: Int) {
        val card = filteredHearthstoneCards[position]
        hearthstoneCardViewHolder.cardName.text = card.name
        hearthstoneCardViewHolder.cardType.text = card.type
        hearthstoneCardViewHolder.cardPlayerClass.text = card.playerClass

        if (card.imageUrl.isEmpty()) {
            hearthstoneCardViewHolder.cardImage.setImageResource(R.drawable.ic_hearthstone_card)
        } else {
            // No need to try to get the image if the imageUrl is empty anyways
            // Also use default hearthstone cardback while grabbing the image or if it failed
            Picasso.get()
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_hearthstone_card)
                .error(R.drawable.ic_hearthstone_card)
                .into(hearthstoneCardViewHolder.cardImage)
        }
    }

    override fun getItemCount(): Int {
        return filteredHearthstoneCards.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = Filter.FilterResults()
                if (constraint != null && constraint.isNotEmpty()) {
                    // NOTE: using contains() as the filter here because if there is a list of {"aa", "ab", "cb"}
                    // and you search for "a", you most likely want to see both "aa" and "ab" instead of nothing
                    val filteredCards = allHearthstoneCards.filter {
                            hearthstoneCard -> hearthstoneCard.name.contains(constraint.toString(), true)
                    }

                    filterResults.values = filteredCards
                    filterResults.count = filteredCards.size
                } else {
                    filterResults.values = allHearthstoneCards
                    filterResults.count = allHearthstoneCards.size
                }

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    filteredHearthstoneCards = results.values as List<HearthstoneCard>
                    notifyDataSetChanged()
                }
            }
        }
    }
}
