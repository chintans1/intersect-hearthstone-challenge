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

        val simpleDateFormatter = SimpleDateFormat("MMMM dd, YYYY", Locale.getDefault())
        current_date.text = simpleDateFormatter.format(Date())

        // For now, we just wait a couple seconds and then start the MainActivity
        val startMainActivity = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        Handler().postDelayed(startMainActivity, splashScreenDelay)
    }
}
