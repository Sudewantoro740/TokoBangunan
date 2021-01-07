package com.anto.tokobangunan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.anto.tkanekajaya.database.AnekaJaya
import com.anto.tokobangunan.databinding.ActivityHomeBinding
import com.anto.tokobangunan.databinding.ActivityTambahTransaksiBinding
import com.anto.tokobangunan.viewmodel.AnekaJayaViewModel
import kotlinx.android.synthetic.main.activity_tambah_transaksi.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

//thread baru
@InternalCoroutinesApi
class TambahTransaksi : AppCompatActivity() {
    private lateinit var binding: ActivityTambahTransaksiBinding
    private lateinit var anekaJayaViewModel: AnekaJayaViewModel

    companion object{
        const val TRANSAKSISELESAI = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTambahTransaksiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        anekaJayaViewModel = ViewModelProvider(this@TambahTransaksi, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(AnekaJayaViewModel::class.java)

        binding.BTkirim.setOnClickListener{
            inputCheck()
        }
    }

    private fun inputCheck() {
        when {
            binding.etNamapel.text.trim().isEmpty() -> {
                binding.inputnamapel.error = getString(R.string.null_field, "Nama pelanggan")
            }
            binding.etBarang.text.trim().isEmpty() -> {
                binding.inputbarang.error = getString(R.string.null_field, "Nama barang")
            }
            binding.etJumlah.text.trim().isEmpty() -> {
                binding.inputjumlah.error = getString(R.string.null_field, "Jumlah barang")
            }
            binding.etTotalharga.text.trim().isEmpty() -> {
                binding.inputtotalharga.error = getString(R.string.null_field, "Total harga")
            }
            binding.etBayar.text.trim().isEmpty() -> {
                binding.inputbayar.error = getString(R.string.null_field, "Total bayar")
            }
            else -> doProcess()
        }
    }

    private fun doProcess() {
        val namapel = binding.etNamapel.text.toString()
        val namabrg = binding.etBarang.text.toString()
        val jumlah = binding.etJumlah.text.toString().toDouble()
        val harga = binding.etHarga.text.toString().toDouble()
        val totalharga = binding.etTotalharga.text.toString().toDouble()
        val totalBayar = binding.etBayar.text.toString().toDouble()
        val kembalian = totalBayar-totalharga

        if (kembalian>=0) {
            val anekaJaya = AnekaJaya(0, namapel, namabrg, jumlah, harga, totalharga, totalBayar, kembalian)
            GlobalScope.launch(context = Dispatchers.IO) {
                anekaJayaViewModel.insert(anekaJaya)
            }

            val intent = Intent(this, TransaksiSelesai::class.java)
            startActivityForResult(intent, TRANSAKSISELESAI)

            finish()
//            TransaksiSelesai(anekaJaya).show(childFragmentManager, "")
        } else {
            binding.inputbayar.error = getString(R.string.uang_kurang)
        }
    }
}