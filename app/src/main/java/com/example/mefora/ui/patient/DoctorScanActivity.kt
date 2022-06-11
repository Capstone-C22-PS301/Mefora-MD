package com.example.mefora.ui.patient

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mefora.databinding.ActivityDoctorScanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter


class DoctorScanActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private var binding: ActivityDoctorScanBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorScanBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val encoder = QRCodeWriter()
        val bitMatrix = encoder.encode(firebaseAuth.currentUser?.uid.toString(), BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        binding?.qrcode?.setImageBitmap(bmp)

    }
}