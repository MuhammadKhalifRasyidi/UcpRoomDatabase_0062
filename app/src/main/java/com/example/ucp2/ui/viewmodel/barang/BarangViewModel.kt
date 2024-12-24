package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.launch

// menyimpan input form ke dalam entity
fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier,
)

// data class variabel yang menyimpan data input form
data class BarangEvent(
    val id: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: Int = 0,
    val namaSuplier: String = "",
)

data class FormErrorState(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null,
) {
    fun isValid(): Boolean {
        return id == null && nama == null && deskripsi == null && harga == null && stok == null && namaSuplier == null
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)


class BarangViewModel(private val repositoryBrg: RepositoryBrg) : ViewModel() {

    var uiBrgState by mutableStateOf(BrgUIState())

    // memperbarui state berdasarkan input
    fun updateState(barangEvent: BarangEvent) {
        uiBrgState = uiBrgState.copy(
            barangEvent = barangEvent
        )
    }

    // validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiBrgState.barangEvent
        val errorState = FormErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok >= 0) null else "Stok tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )

        uiBrgState = uiBrgState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //
    fun saveData() {
        val currentEvent = uiBrgState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiBrgState = uiBrgState.copy(
                        snackBarMessage = "Data berhasi disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiBrgState = uiBrgState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiBrgState = uiBrgState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }


    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiBrgState = uiBrgState.copy(snackBarMessage = null)
    }
}