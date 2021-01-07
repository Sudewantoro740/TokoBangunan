package com.anto.tokobangunan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anto.tkanekajaya.database.AnekaJaya
import com.anto.tokobangunan.adapter.AnekaJayaAdapter
import com.anto.tokobangunan.databinding.ActivityHomeBinding
import com.anto.tokobangunan.databinding.ActivityHomeBinding.inflate
import com.anto.tokobangunan.databinding.RecyclerviewAnekajayaBinding
import com.anto.tokobangunan.viewmodel.AnekaJayaViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var anekaJayaViewModel: AnekaJayaViewModel
    private lateinit var adapter: AnekaJayaAdapter

    companion object{
        const val ADDTRANSAKSI = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvAnekajaya.setHasFixedSize(true)
        binding.rvAnekajaya.layoutManager = LinearLayoutManager(this)

        anekaJayaViewModel = ViewModelProvider(this@Home, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(AnekaJayaViewModel::class.java)
        anekaJayaViewModel.getAnekaJayaBarang().observe(this, Observer<List<AnekaJaya>> {
            adapter = AnekaJayaAdapter(it)

            binding.rvAnekajaya.adapter = adapter

            adapter.setOnListener(object: AnekaJayaAdapter.OnClickCallBack{
                override fun onitemcliked(anekaJaya: AnekaJaya) {
                    val intent = Intent(this@Home, EditTransaksi::class.java)
                    intent.putExtra("idanekajaya", anekaJaya.id)
                    startActivityForResult(intent, 2)
                }
            })
        })

        binding.fabAddTransaksi.setOnClickListener{
            val intent = Intent(this, TambahTransaksi::class.java)
            startActivityForResult(intent, ADDTRANSAKSI)
        }

        binding.BTinfo.setOnClickListener{
            val intent = Intent(this, MyProfil::class.java)
            startActivity(intent)
        }
    }
}