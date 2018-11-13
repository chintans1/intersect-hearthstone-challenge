package com.chintan.hearthstone.activities

import android.app.SearchManager
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.chintan.hearthstone.R
import com.chintan.hearthstone.adapters.HearthstoneCardsAdapter
import com.chintan.hearthstone.extractors.HearthstoneCardsExtract
import com.chintan.hearthstone.models.HearthstoneCard
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    // Have to declare this here so it can be accessed when the searchView has its text listener set
    // Declared as lateinit since Kotlin does not allow class level variables to be left uninitialized otherwise
    lateinit var cardAdapter: HearthstoneCardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        // Workaround to bypass Android's policy of having network (non-UI) tasks on a different async thread
        val policy = StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)

        // Display a progress bar until we can load all the cards from the API and into the RecyclerView
        // The extract is done in the background so the UI is not locking up which can disturb the user
        // Unfortunately, there is some rather large load times for the cards and I cannot pinpoint why
        GetHearthstonesTask(this).execute()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                cardAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cardAdapter.filter.filter(newText)
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.app_bar_search -> return true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class GetHearthstonesTask(private val mainActivity: MainActivity) : AsyncTask<Void, Int, List<HearthstoneCard>>() {
        override fun doInBackground(vararg params: Void?): List<HearthstoneCard>? {
            return HearthstoneCardsExtract().extract()
        }

        override fun onPostExecute(result: List<HearthstoneCard>) {
            super.onPostExecute(result)

            val layoutManager = GridLayoutManager(mainActivity.applicationContext, 2)
            mainActivity.cardAdapter = HearthstoneCardsAdapter(result)
            mainActivity.hearthstone_cards_recycler_view.layoutManager = layoutManager
            mainActivity.hearthstone_cards_recycler_view.adapter = mainActivity.cardAdapter

            mainActivity.progress_bar_linear_layout.visibility = View.GONE
            mainActivity.hearthstone_cards_recycler_view.visibility = View.VISIBLE
        }
    }

}
