package com.psdemo.globomanticssales.data

import androidx.lifecycle.LiveData

class ClientRoomRepository(private val clientDao: ClientDao) : ClientRepository {
    override fun getAllClients(): LiveData<List<Client>> {
        return clientDao.getAllClients()
    }

    override fun getClient(id: Int): LiveData<Client> {
        return clientDao.getClient(id)
    }

    override suspend fun insert(client: Client) {
        clientDao.insert(client)
    }
}