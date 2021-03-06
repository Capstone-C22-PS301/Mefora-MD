package com.example.mefora.ui.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mefora.R
import com.example.mefora.databinding.ActivityHomeDoctorBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeDoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navViewDoctor
        val navController = findNavController(R.id.nav_host_fragment_activity_home_doctor)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_doctor,
                R.id.navigation_patient,
                R.id.navigation_profile_doctor
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}