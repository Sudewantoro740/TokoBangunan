package com.anto.tokobangunan.repository

import androidx.lifecycle.LiveData
import com.anto.tkanekajaya.database.AnekaJaya
import com.anto.tkanekajaya.database.AnekaJayaDAO

class AnekaJayaRepository(private val anekaJayaDAO: AnekaJayaDAO){
    val anekaJayaBarang: LiveData<List<AnekaJaya>> = anekaJayaDAO.getanekajaya()

    suspend fun insert(anekaJaya: AnekaJaya){
        anekaJayaDAO.insert(anekaJaya)
    }

    suspend fun edit(id: Long): AnekaJaya{
        val dataedit = anekaJayaDAO.getitemanekajaya(id)
        return dataedit
    }

    suspend fun update(anekaJaya: AnekaJaya){
        anekaJayaDAO.update(anekaJaya)
    }

    suspend fun delete(id: Long){
        anekaJayaDAO.delete(id)
    }
}