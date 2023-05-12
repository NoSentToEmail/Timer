package com.example.timertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.example.timer.R
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