package com.psdemo.globomanticssales.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClientDao {

    @Query("SELECT * FROM client ORDER BY date DESC")
    fun getAllClients(): LiveData<List<Client>>

    @Query("SELECT * FROM client WHERE id = :id")
    fun getClient(id: Int): LiveData<Client>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(client: Client)

}