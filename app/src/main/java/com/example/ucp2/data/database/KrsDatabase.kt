package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SuplierDao
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Suplier

// mendefinisikan database dengan tabel barang
@Database(entities = [Barang::class], version = 1, exportSchema = false)
abstract class KrsDatabaseBarang : RoomDatabase() {

    // Mendefinisikan fungsi untuk mengakses data Barang
    abstract fun BarangDao(): BarangDao

    companion object {
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di semua thread
        private var Instance: KrsDatabaseBarang? = null

        fun getDatabase(context: Context): KrsDatabaseBarang {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    KrsDatabaseBarang::class.java, // Class Database
                    "KrsDatabase" // Nama Databse
                )
                    .build().also { Instance = it }
            })
        }
    }
}

// mendefinisikan database dengan tabel suplier
@Database(entities = [Suplier::class], version = 1, exportSchema = false)
abstract class KrsDatabaseSuplier : RoomDatabase() {

    // Mendefinisikan fungsi untuk mengakses data Suplier
    abstract fun SuplierDao(): SuplierDao

    companion object {
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di semua thread
        private var Instance: KrsDatabaseSuplier? = null

        fun getDatabase(context: Context): KrsDatabaseSuplier {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    KrsDatabaseSuplier::class.java, // Class Database
                    "KrsDatabase" // Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }
}
