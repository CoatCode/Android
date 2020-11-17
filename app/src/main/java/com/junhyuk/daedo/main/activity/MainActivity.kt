package com.junhyuk.daedo.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.junhyuk.daedo.R

class MainActivity : AppCompatActivity() {

    var userId = 0
    var userProfile = ""
    var userDescription = ""
    var userName = ""
    var followers : Int = 0
    var following : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //엑션바 숨기기
        val actionBar = supportActionBar
        actionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }

}