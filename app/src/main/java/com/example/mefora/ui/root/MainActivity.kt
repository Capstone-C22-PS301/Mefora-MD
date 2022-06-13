package com.example.mefora.ui.root

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mefora.R
import com.example.mefora.ui.authentication.AuthenticationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this@MainActivity, AuthenticationActivity::class.java).also {
            startActivity(it)
        }
    }
}