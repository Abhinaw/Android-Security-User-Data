package com.psdemo.globomanticssales.ui.clients

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.psdemo.globomanticssales.data.Client
import com.psdemo.globomanticssales.data.ClientRepository
import com.psdemo.globomanticssales.data.ClientRoomDatabase
import com.psdemo.globomanticssales.data.ClientRoomRepository

class ClientsViewModel(application: Application) : AndroidViewModel(application) {
    private val clientRepository: ClientRepository

    init {
        val clientDao = ClientRoomDatabase.getInstance(application).clientDao()
        clientRepository = ClientRoomRepository(clientDao)
    }

    val allClients: LiveData<List<Client>> = clientRepository.getAllClients()
}