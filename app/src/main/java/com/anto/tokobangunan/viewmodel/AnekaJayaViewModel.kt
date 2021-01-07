package com.anto.tokobangunan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anto.tkanekajaya.database.AnekaJaya
import com.anto.tkanekajaya.database.AnekaJayaDatabase
import com.anto.tokobangunan.repository.AnekaJayaRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class AnekaJayaViewModel(application: Application) : AndroidViewModel(application){
    private val repository : AnekaJayaRepository
    private val allBarang: LiveData<List<AnekaJaya>>

    init {
        val barangDao = AnekaJayaDatabase.getInstance(application).anekaJayaDAO
        repository = AnekaJayaRepository(barangDao)
        allBarang = repository.anekaJayaBarang
    }

    suspend fun insert (anekaJaya: AnekaJaya) {
        repository.insert(anekaJaya)
    }

    fun getAnekaJayaBarang(): LiveData<List<AnekaJaya>>{
        return allBarang
    }

    suspend fun edit(id: Long): AnekaJaya{
        val dataedit = repository.edit(id)
        return dataedit
    }

    suspend fun update(anekaJaya: AnekaJaya){
        repository.update(anekaJaya)
    }

    suspend fun delete(id: Long){
        repository.delete(id)
    }
}