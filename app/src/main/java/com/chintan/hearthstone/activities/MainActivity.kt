package com.chintan.hearthstone.activities

import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chintan.hearthstone.R
import com.chintan.hearthstone.adapters.HearthstoneCardsAdapter
import com.chintan.hearthstone.extractors.HearthstoneCardsExtract
import com.chintan.hearthstone.models.HearthstoneCard
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Workaround to bypass Android's policy of having network (non-UI) tasks on a different async thread
        val policy = StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)

        // Display a progress bar until we can load all the cards from the API and into the RecyclerView
        // The extract is done in the background so the UI is not locking up which can disturb the user
        // Unfortunately, there is some rather large load times for the cards and I cannot pinpoint why
        GetHearthstonesTask(this).execute()
    }

    class GetHearthstonesTask(private val mainActivity: MainActivity) : AsyncTask<Void, Int, List<HearthstoneCard>>() {
        override fun doInBackground(vararg params: Void?): List<HearthstoneCard>? {
            return HearthstoneCardsExtract().extract()
        }

        override fun onPostExecute(result: List<HearthstoneCard>?) {
            super.onPostExecute(result)

            val layoutManager = GridLayoutManager(mainActivity.applicationContext, 2)
            val cardAdapter = HearthstoneCardsAdapter(HearthstoneCardsExtract().extract())
            mainActivity.hearthstone_cards_recycler_view.layoutManager = layoutManager
            mainActivity.hearthstone_cards_recycler_view.adapter = cardAdapter

            mainActivity.progress_bar_linear_layout.visibility = View.GONE
            mainActivity.hearthstone_cards_recycler_view.visibility = View.VISIBLE
        }
    }
}
