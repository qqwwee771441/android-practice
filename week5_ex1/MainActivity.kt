package com.example.week51

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: FragmentManager 및 fragButton 변수 선언
        val fragButton = findByViewId<Button>(R.id.fragBut1)
        val fragmentManager: FragmentManager = supportFragmentManager

        var onClicked = false
        fragButton.setOnClickListener{
            if (onClicked) {
                onClicked = false
                // TODO: FragmentManager를 사용하여 Fragment를 제거
                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                val frameLayout = supportFragmentManager.findFragmentById(R.id.fragment_content)
                transaction.remove(frameLayout!!).commit()
            }
            else {
                onClicked=true
                // TODO: FragmentManager를 사용하여 Fragment를 추가
                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content, OneFragment()).commit()
            }
        }
    }
}

