package com.chintan.hearthstone.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.chintan.hearthstone.R
import com.chintan.hearthstone.adapters.HearthstoneCardAdapter
import com.chintan.hearthstone.models.HearthstoneCard
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val layoutManager = GridLayoutManager(this, 2)
        val cardAdapter = HearthstoneCardAdapter(getCards())
        hearthstone_cards_recycler_view.layoutManager = layoutManager
        hearthstone_cards_recycler_view.adapter = cardAdapter
    }

    private fun getCards() : List<HearthstoneCard> {
        // For the time being, this is how I am testing the RecyclerView. Soon enough, it will get the cards from
        // the API JSON response and put them into a list of HearthstoneCard
        val longTitleCard = HearthstoneCard("Hearthstone Card", "Type card", "player class", "")
        val smallTitleCard = HearthstoneCard("Hearthstone", "Type card", "player class", "")
        return Arrays.asList(longTitleCard, smallTitleCard, longTitleCard, longTitleCard, smallTitleCard, longTitleCard,
            longTitleCard, smallTitleCard, longTitleCard, longTitleCard, smallTitleCard, smallTitleCard, smallTitleCard)
    }
}
