package com.anto.tokobangunan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anto.tokobangunan.databinding.ActivityTambahTransaksiBinding
import com.anto.tokobangunan.databinding.ActivityTransaksiSelesaiBinding

class TransaksiSelesai : AppCompatActivity() {
    private lateinit var binding: ActivityTransaksiSelesaiBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityTransaksiSelesaiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSelesai.setOnClickListener {

            finish()
        }
    }
}