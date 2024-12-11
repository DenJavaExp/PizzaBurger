package com.example.pizzaburger

import CategoriesListFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.pizzaburger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentContainerView = CategoriesListFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.mainContainer, fragmentContainerView)
            transaction.commit()
        }
    }
}