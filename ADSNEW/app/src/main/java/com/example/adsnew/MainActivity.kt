package com.example.adsnew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_download.*
import kotlinx.android.synthetic.main.fragment_search.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val choosePK = intent.getStringExtra("choosePK")

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navView.setOnNavigationItemSelectedListener { item ->
            val bundle = Bundle()
            bundle.putString("choosePK", choosePK)
            when (item.itemId) {
                R.id.navigation_search -> navController.navigate(R.id.searchFragment, bundle)
                R.id.navigation_add -> navController.navigate(R.id.addFragment, bundle)
                R.id.navigation_download -> navController.navigate(R.id.downloadFragment, bundle)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        val navHostFragment = supportFragmentManager.fragments.first() as? NavHostFragment
//        val childFragments = navHostFragment?.childFragmentManager?.fragments
        if (result != null) {
            if (result.contents == null) {
                    Toast.makeText(layoutInflater.context, "Ошибка!", Toast.LENGTH_LONG).show()
            } else {
                    otf_input_add_barcode.editText?.setText(result.contents)
                    Toast.makeText(
                            layoutInflater.context,
                            "Успешно!" + result.contents,
                            Toast.LENGTH_LONG
                    ).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}