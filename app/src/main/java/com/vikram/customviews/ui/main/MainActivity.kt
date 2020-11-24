package com.vikram.customviews.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vikram.customviews.R
import com.vikram.customviews.ui.circle.CircleExampleFragment1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CircleExampleFragment1.newInstance())
                    .commitNow()
        }
    }
}