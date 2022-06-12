package com.example.mefora.ui.doctor

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.mefora.R
import com.example.mefora.databinding.ActivityQrscannerBinding
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.doctor.DoctorMainViewModel
import com.google.firebase.auth.FirebaseAuth

class QRScannerActivity : AppCompatActivity() {

    private lateinit var viewModel: DoctorMainViewModel

    val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityQrscannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscannerBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[DoctorMainViewModel::class.java]
        setContentView(binding.root)
        getCameraPermission()
        qrScanner()
    }

    private fun getCameraPermission() {
        val permission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQ
            )
        }
    }

    private fun qrScanner() {
        codeScanner = CodeScanner(this, binding.qrScanner)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            autoFocusMode = AutoFocusMode.SAFE
            formats = CodeScanner.ALL_FORMATS
            scanMode = ScanMode.SINGLE
            decodeCallback = DecodeCallback {
                viewModel.addPatientToList(it.text)
                viewModel.createPatientListData.observe(this@QRScannerActivity) { response ->
                    when (response) {
                        is DataResponse.Success -> {
                            finish()
                        }
                        is DataResponse.Failed -> {
                            Toast.makeText(
                                this@QRScannerActivity,
                                "Scan Failed!",
                                Toast.LENGTH_SHORT
                            ).show()
                            reScan()
                        }
                    }
                }
            }
        }
    }

    private fun reScan() {
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var codeScanner: CodeScanner
        private const val CAMERA_REQ = 101
    }
}