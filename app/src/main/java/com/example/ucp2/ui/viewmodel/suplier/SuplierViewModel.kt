package com.example.ucp2.ui.viewmodel.suplier

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.repository.RepositorySpr
import kotlinx.coroutines.launch


// menyimpan input form ke dalam entity
fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    ids = ids,
    namaS = namaS,
    kontak = kontak,
    alamat = alamat,
)

// data class variabel yang menyimpan data input form
data class SuplierEvent(
    val ids: String = "",
    val namaS: String = "",
    val kontak: String = "",
    val alamat: String = "",
    )

data class FormErrorState(
    val ids: String? = null,
    val namaS: String? = null,
    val kontak: String? = null,
    val alamat: String? = null,
) {
    fun isValid(): Boolean {
        return ids == null && namaS == null && kontak == null && alamat == null
    }
}

data class SprUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)


class SuplierViewModel(private val repositorySpr: RepositorySpr) : ViewModel() {

    var uiSprState by mutableStateOf(SprUIState())

    fun updateSprState(suplierEvent: SuplierEvent){
        uiSprState = uiSprState.copy(
            suplierEvent = suplierEvent,
        )
    }

    // validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiSprState.suplierEvent
        val errorState = FormErrorState(
            ids = if (event.ids.isNotEmpty()) null else "ID tidak boleh kosong",
            namaS = if (event.namaS.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            )

        uiSprState = uiSprState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //
    fun saveData() {
        val currentEvent = uiSprState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySpr.insertSpr(currentEvent.toSuplierEntity())
                    uiSprState= uiSprState.copy(
                        snackBarMessage = "Data berhasi disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    Log.e("SuplierViewModel", "Error menyimpan data: ${e.message}")
                    uiSprState= uiSprState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiSprState = uiSprState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }


    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiSprState = uiSprState.copy(snackBarMessage = null)
    }
}
