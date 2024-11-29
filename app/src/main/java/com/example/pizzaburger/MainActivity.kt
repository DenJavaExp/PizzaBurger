package com.example.pizzaburger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pizzaburger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _bindig: ActivityMainBinding? = null
    private val binding
        get() = _bindig
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }
}