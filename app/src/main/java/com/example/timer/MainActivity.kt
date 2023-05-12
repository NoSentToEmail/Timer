package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {

    private var second = 0
    private var isRunning = false

    private lateinit var timer: TextView
    private lateinit var start : Button
    private lateinit var stop: Button
    private lateinit var reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            second =  savedInstanceState.getInt("second")
        }
        if (savedInstanceState != null) {
            isRunning = savedInstanceState.getBoolean("isRunning")
        }



        timer = findViewById(R.id.TimerView)
        start = findViewById(R.id.buttonStart)
        stop = findViewById(R.id.buttonStop)
        reset = findViewById(R.id.buttonReset)
        runTimer()

        start.setOnClickListener(){
            isRunning = true

        }
        stop.setOnClickListener(){
            isRunning = false
        }

        reset.setOnClickListener(){
            isRunning = false
            second = 0
        }



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("second", second)
        outState.putBoolean("isRunning", isRunning)
    }

    private fun runTimer() {
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                val hours = second / 3600
                val minutes = (second % 3600) / 60
                val secs = second % 60

                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                timer.text = time

                if (isRunning) second++

                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

}