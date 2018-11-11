package com.chintan.hearthstone.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.chintan.hearthstone.R
import com.chintan.hearthstone.adapters.HearthstoneCardAdapter
import com.chintan.hearthstone.extractors.HearthstoneCardsExtract
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val layoutManager = GridLayoutManager(this, 2)
        val cardAdapter = HearthstoneCardAdapter(HearthstoneCardsExtract().extract())
        hearthstone_cards_recycler_view.layoutManager = layoutManager
        hearthstone_cards_recycler_view.adapter = cardAdapter
    }
}
