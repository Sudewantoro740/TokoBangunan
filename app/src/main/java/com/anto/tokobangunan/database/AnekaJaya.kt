package com.anto.tkanekajaya.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anekajaya")
data class AnekaJaya (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "namabarang")
    var namabrg: String,

    @ColumnInfo(name = "jumlah")
    var jumlah: Double,

    @ColumnInfo(name = "harga")
    var harga: Double,

    @ColumnInfo(name = "totalharga")
    var totalharga: Double,

    @ColumnInfo(name = "bayar")
    var bayar: Double,

    @ColumnInfo(name = "kembalian")
    var kembalian: Double,

    @ColumnInfo(name = "tanggal")
    var tanggal: Long = System.currentTimeMillis()

)