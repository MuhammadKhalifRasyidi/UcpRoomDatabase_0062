package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    var UpdateBrgUIState by mutableStateOf(BrgUIState())
        private set

    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID])

    init {
        viewModelScope.launch {
            UpdateBrgUIState = repositoryBrg.getBrg(_id)
                .filterNotNull()
                .first()
                .toUIStateMhs()
        }
    }

    fun UpdateState(barangEvent: BarangEvent) {
        UpdateBrgUIState = UpdateBrgUIState.copy(
            barangEvent = barangEvent,
        )
    }

    fun validateFields() : Boolean {
        val event = UpdateBrgUIState.barangEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok =  if (event.stok >= 0) null else "Stok tidak boleh kosong",
            namaSuplier =  if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )

        UpdateBrgUIState = UpdateBrgUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun UpdateData() {
        val currentEvent = UpdateBrgUIState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.updateBrg(currentEvent.toBarangEntity())
                    UpdateBrgUIState = UpdateBrgUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("SnackBarMessageDiatur: ${UpdateBrgUIState.snackBarMessage}")
                } catch (e:Exception) {
                    UpdateBrgUIState = UpdateBrgUIState.copy(
                        snackBarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            UpdateBrgUIState = UpdateBrgUIState.copy(
                snackBarMessage = "Data Gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        UpdateBrgUIState = UpdateBrgUIState.copy(snackBarMessage = null)
    }
}

fun Barang.toUIStateMhs() : BrgUIState = BrgUIState (
    barangEvent = this.toDetailBrgUiEvent(),
)