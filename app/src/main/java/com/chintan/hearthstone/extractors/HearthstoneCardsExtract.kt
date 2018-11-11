package com.chintan.hearthstone.extractors

import com.chintan.hearthstone.models.HearthstoneCard
import java.util.*

class HearthstoneCardsExtract : Extract<HearthstoneCard> {
    override fun extract(): List<HearthstoneCard> {
        // For the time being, this is how I am testing the RecyclerView. Soon enough, it will get the cards from
        // the API JSON response and put them into a list of HearthstoneCard
        val longTitleCard = HearthstoneCard("Hearthstone Card", "Type card", "player class", "")
        val smallTitleCard = HearthstoneCard("Hearthstone", "Type card", "player class", "")
        return Arrays.asList(
            longTitleCard, smallTitleCard, longTitleCard, longTitleCard, smallTitleCard, longTitleCard,
            longTitleCard, smallTitleCard, longTitleCard, longTitleCard, smallTitleCard, smallTitleCard, smallTitleCard
        )
    }
}
