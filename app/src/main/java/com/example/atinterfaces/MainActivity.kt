package com.example.atinterfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.transition.Fade
import androidx.transition.TransitionSet

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val i = Intent(this, MenuActivity::class.java)
            TransitionSet().apply {
                addTransition(Fade())

                addTarget(R.id.drawer_layout)
            }
            startActivity(i)
            finish()
        }, 3000)
    }
}
