package com.dwisuseno.githubusernavigationdanapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dwisuseno.githubusernavigationdanapi.databinding.ActivityMainBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}