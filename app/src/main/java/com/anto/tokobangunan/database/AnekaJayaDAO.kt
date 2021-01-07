package com.anto.tkanekajaya.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AnekaJayaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anekaJaya: AnekaJaya)

    @Update
    suspend fun update(anekaJaya: AnekaJaya)

    @Query("SELECT * FROM anekajaya")
    fun getanekajaya(): LiveData<List<AnekaJaya>>

    @Query("DELETE FROM anekajaya")
    fun clear()

    @Query("DELETE FROM anekajaya WHERE id = :anekajayaID")
    suspend fun delete(anekajayaID: Long)

    @Query("SELECT * FROM anekajaya WHERE id = :anekajayaID")
    suspend fun getitemanekajaya(anekajayaID: Long): AnekaJaya
}