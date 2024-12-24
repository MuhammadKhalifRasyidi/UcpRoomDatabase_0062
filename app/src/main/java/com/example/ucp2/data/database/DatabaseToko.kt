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
@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class DatabaseToko : RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao

    companion object {
        @Volatile
        private var Instance: DatabaseToko? = null

        fun getDatabase(context: Context): DatabaseToko {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseToko::class.java,
                    "DatabaseToko"
                ).build().also { Instance = it }
            }
        }
    }
}