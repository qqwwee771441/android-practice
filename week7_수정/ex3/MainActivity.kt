package com.choi.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.choi.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.DecimalFormat
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.activityMain)
        var isrunning = false
        var ispause = false
        val channel = Channel<String>()
        var h = 0; var m = 0; var s = 0
        binding.start.setOnClickListener {
            "///////todo////////"
            if (!isrunning) {
                isrunning=true
            } else {
                Toast.makeText(this,"Stopwatch is not stopped now!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.stop.setOnClickListener {
            "///////todo////////"
            if (isrunning) {
                isrunning=false
                h = 0
                m = 0
                s = 0
                binding.time.text = "00:00:00"
            } else {
                Toast.makeText(this,"Stopwatch is stopped now!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.pause.setOnClickListener {
            "///////todo////////"
            if (!ispause) {
                ispause=true
            } else {
                Toast.makeText(this,"Stopwatch is paused now!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.resume.setOnClickListener {
            "///////todo////////"
            if (ispause) {
                ispause=false
            } else {
                Toast.makeText(this,"Stopwatch is not paused now!!", Toast.LENGTH_SHORT).show()
            }
        }
        val subScope = CoroutineScope(Dispatchers.Default + Job())
        subScope.launch {
            "///////todo////////"
            while (true) {

                    try {
                        delay(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    if (!isrunning || ispause) continue
                    s += 1
                    if (s == 60) {
                        s = 0
                        m += 1
                        if (m == 60) {
                            m = 0
                            h += 1
                        }
                    }
                    channel.send(String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s))

            }
        }
        var mainScope = GlobalScope.launch(Dispatchers.Main) {
            channel.consumeEach {
                binding.time.text = "$it"
            }
        }
    }
}