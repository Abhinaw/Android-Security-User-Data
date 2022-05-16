package com.psdemo.globomanticssales.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var date: Long,
    var name: String,
    var order: String,
    var terms: String
)