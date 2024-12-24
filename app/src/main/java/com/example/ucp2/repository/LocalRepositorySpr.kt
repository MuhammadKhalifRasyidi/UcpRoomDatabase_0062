package com.example.ucp2.repository

import com.example.ucp2.data.dao.SuplierDao
import com.example.ucp2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpr(
    private val suplierDao: SuplierDao
) : RepositorySpr {
    override suspend fun insertSpr(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }

    override fun getAllSpr(): Flow<List<Suplier>> = suplierDao.getAllSuplier()

    override fun getSpr(ids: String): Flow<Suplier> = suplierDao.getSuplier(ids)
}