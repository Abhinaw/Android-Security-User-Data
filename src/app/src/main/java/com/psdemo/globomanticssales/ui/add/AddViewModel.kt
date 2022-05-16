package com.psdemo.globomanticssales.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.psdemo.globomanticssales.data.Client
import com.psdemo.globomanticssales.data.ClientRepository
import com.psdemo.globomanticssales.data.ClientRoomDatabase
import com.psdemo.globomanticssales.data.ClientRoomRepository
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val clientRepository: ClientRepository

    init {
        val clientDao = ClientRoomDatabase.getInstance(application).clientDao()
        clientRepository = ClientRoomRepository(clientDao)
    }

    fun insert(client: Client) = viewModelScope.launch {
        clientRepository.insert(client)
    }
}