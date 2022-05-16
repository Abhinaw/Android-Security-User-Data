package com.psdemo.globomanticssales.data

import androidx.lifecycle.LiveData

interface ClientRepository {
    fun getAllClients(): LiveData<List<Client>>

    fun getClient(id: Int): LiveData<Client>

    suspend fun insert(client: Client)
}