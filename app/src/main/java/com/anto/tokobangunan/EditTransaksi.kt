package com.anto.tokobangunan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.anto.tkanekajaya.database.AnekaJaya
import com.anto.tokobangunan.databinding.ActivityEditTransaksiBinding
import com.anto.tokobangunan.databinding.ActivityHomeBinding
import com.anto.tokobangunan.viewmodel.AnekaJayaViewModel
import kotlinx.coroutines.*

@InternalCoroutinesApi
class EditTransaksi : AppCompatActivity() {
    private lateinit var binding: ActivityEditTransaksiBinding
    private lateinit var anekaJayaViewModel: AnekaJayaViewModel
    private var anekaJayaitemrv: AnekaJaya?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditTransaksiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        anekaJayaViewModel = ViewModelProvider(this@EditTransaksi, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(
            AnekaJayaViewModel::class.java)

        val idanekaJaya = intent.getLongExtra("idanekajaya", 1)
        GlobalScope.launch(context = Dispatchers.IO) {
            anekaJayaitemrv = anekaJayaViewModel.edit(idanekaJaya)

            GlobalScope.launch(context = Dispatchers.Main) {
                delay(500)
                binding.etEtnamapel.setText(anekaJayaitemrv?.nama)
                binding.etEtbarang.setText(anekaJayaitemrv?.namabrg)
                binding.etEtjumlah.setText(anekaJayaitemrv?.jumlah.toString())
                binding.etEtharga.setText(anekaJayaitemrv?.harga.toString())
                binding.etEttotalharga.setText(anekaJayaitemrv?.totalharga.toString())
                binding.etEtbayar.setText(anekaJayaitemrv?.bayar.toString())
                binding.etEtkembalian.setText(anekaJayaitemrv?.kembalian.toString())
            }
        }

        binding.BTupdate.setOnClickListener {
            inputCheck()
        }
    }

    private fun inputCheck() {
        when {
            binding.etEtnamapel.text.trim().isEmpty() -> {
                binding.inputetnamapel.error = getString(R.string.null_field, "Nama pelanggan")
            }
            binding.etEtbarang.text.trim().isEmpty() -> {
                binding.inputetbarang.error = getString(R.string.null_field, "Nama barang")
            }
            binding.etEtjumlah.text.trim().isEmpty() -> {
                binding.inputetjumlah.error = getString(R.string.null_field, "Jumlah barang")
            }
            binding.etEttotalharga.text.trim().isEmpty() -> {
                binding.inputettotalharga.error = getString(R.string.null_field, "Total harga")
            }
            binding.etEtbayar.text.trim().isEmpty() -> {
                binding.inputetbayar.error = getString(R.string.null_field, "Total bayar")
            }
            else -> doProcess()
        }
    }

    private fun doProcess() {
        val namapel = binding.etEtnamapel.text.toString()
        val namabrg = binding.etEtbarang.text.toString()
        val jumlah = binding.etEtjumlah.text.toString().toDouble()
        val harga = binding.etEtharga.text.toString().toDouble()
        val totalharga = harga*jumlah
        val totalBayar = binding.etEtbayar.text.toString().toDouble()
        val kembalian = totalBayar-totalharga

        if (kembalian>=0) {
            val anekaJaya = AnekaJaya(0, namapel, namabrg, jumlah, harga, totalharga, totalBayar, kembalian)
            GlobalScope.launch(context = Dispatchers.IO) {
                anekaJayaViewModel.insert(anekaJaya)
                anekaJayaitemrv?.id?.let { anekaJayaViewModel.delete(it) }
            }

            Toast.makeText(this, R.string.update, Toast.LENGTH_SHORT).show()
            finish()
//            TransaksiSelesai(anekaJaya).show(childFragmentManager, "")
        } else {
            binding.inputetbayar.error = getString(R.string.uang_kurang)
        }
    }

}