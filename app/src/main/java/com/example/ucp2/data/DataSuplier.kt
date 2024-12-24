package com.example.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.suplier.HomeSprViewModel

object DataSuplier {
    @Composable
    fun options(
        HomeSprViewModel: HomeSprViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val dataNama by HomeSprViewModel.homeUIState.collectAsState()
        val namaSupplier = dataNama.listSpr.map { it.namaS }
        return namaSupplier
    }
}