package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.Agen
import com.example.ucp2.ui.viewmodel.barang.BarangViewModel
import com.example.ucp2.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.ucp2.ui.viewmodel.suplier.HomeSprViewModel
import com.example.ucp2.ui.viewmodel.suplier.SuplierViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            BarangViewModel(
                krsApp().containerApp.repositoryBrg
            )
        }

        initializer {
            HomeBrgViewModel(
                krsApp().containerApp.repositoryBrg
            )
        }

        initializer {
            HomeSprViewModel(
                krsApp().containerApp.repositorySpr
            )
        }

        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryBrg
            )
        }

        initializer {
            SuplierViewModel(
                krsApp().containerApp.repositorySpr
            )
        }

        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryBrg
            )
        }
    }
}

fun CreationExtras.krsApp(): Agen =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Agen)