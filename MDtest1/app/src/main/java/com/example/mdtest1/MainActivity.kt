package com.example.mdtest1

import android.R.color
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
//        when (currentNightMode) {
//            Configuration.UI_MODE_NIGHT_NO -> {
//                val view = this.application.
//                view.setBackgroundColor(R.color.gray_back)
//                Toast.makeText(this, "Тема светлая выбрана", Toast.LENGTH_SHORT).show()
//            } // Night mode is not active, we're using the light theme
//            Configuration.UI_MODE_NIGHT_YES -> {
//                Toast.makeText(this, "Тема темная выбрана", Toast.LENGTH_SHORT).show()
////            } // Night mode is active, we're using dark theme
//        }

    }
}