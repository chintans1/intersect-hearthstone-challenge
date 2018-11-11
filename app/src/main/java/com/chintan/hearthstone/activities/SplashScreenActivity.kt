package com.chintan.hearthstone.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.chintan.hearthstone.R
import kotlinx.android.synthetic.main.splash_screen_activity.*
import java.text.SimpleDateFormat
import java.util.*

class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenDelay = 1000L // 1 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        // Set the current_date text to be today's date
        val simpleDateFormatter = SimpleDateFormat("MMMM dd, YYYY", Locale.getDefault())
        current_date.text = simpleDateFormatter.format(Date())


        // TODO: Wait until all the cards are loaded (API request is successful) and then go to the MainActivity
        // For now, we just wait few seconds and then move onto the MainActivity
        val startMainActivity = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        Handler().postDelayed(startMainActivity, splashScreenDelay)
    }
}
