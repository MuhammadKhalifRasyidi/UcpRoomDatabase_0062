package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey
    val ids: String,
    val namaS: String,
    val kontak: String,
    val alamat: String,
)
