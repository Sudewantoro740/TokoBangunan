package com.anto.tokobangunan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anto.tkanekajaya.database.AnekaJaya
import com.anto.tkanekajaya.helper.convertLongToDateString
import com.anto.tkanekajaya.helper.rupiah
import com.anto.tokobangunan.R
import com.anto.tokobangunan.databinding.RecyclerviewAnekajayaBinding

class AnekaJayaAdapter(private val list: List<AnekaJaya>) :RecyclerView.Adapter<AnekaJayaAdapter.AnekaJayaViewHolder>(){
    private lateinit var listener: OnClickCallBack
    fun setOnListener(listener: OnClickCallBack) {
        this.listener = listener
    }

    inner class AnekaJayaViewHolder(
        val recyclerviewAnekajayaBinding: RecyclerviewAnekajayaBinding
    ) : RecyclerView.ViewHolder(recyclerviewAnekajayaBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnekaJayaViewHolder (
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_anekajaya, parent, false)
    )

    override fun onBindViewHolder(holder: AnekaJayaViewHolder, position: Int) {
        holder.recyclerviewAnekajayaBinding.tvTgl.text = convertLongToDateString(list[position].tanggal)
        holder.recyclerviewAnekajayaBinding.tvNamapel.text = list[position].nama
        holder.recyclerviewAnekajayaBinding.tvNamabrg.text = list[position].namabrg
        holder.recyclerviewAnekajayaBinding.tvHarga.text = rupiah(list[position].harga)

        holder.itemView.setOnClickListener{
            listener.onitemcliked(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickCallBack {
        fun onitemcliked(anekaJaya: AnekaJaya)
    }
}