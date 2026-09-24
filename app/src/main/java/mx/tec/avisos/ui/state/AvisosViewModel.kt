package mx.tec.avisos.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.tec.avisos.data.AvisosRepository
import mx.tec.avisos.domain.Aviso
import retrofit2.HttpException
import java.io.IOException



/** La lista del tablón. La misma forma que la lista de la Práctica 4. */
class AvisosViewModel(private val repository: AvisosRepository) : ViewModel() {

    var avisos by mutableStateOf<UiState<List<Aviso>>>(UiState.Cargando)
        private set

    fun cargar() {
        viewModelScope.launch {
            avisos = UiState.Cargando
            avisos = try {
                UiState.Exito(repository.obtener())
            } catch (e: IOException) {
                UiState.Error("No hay conexión. Revisa tu internet.")
            } catch (e: HttpException) {
                UiState.Error(mensajeDe(e))
            }
        }
    }

    var mensaje by mutableStateOf<String?>(null)
        private set

    fun borrar(id: Int) {
        viewModelScope.launch {
            try {
                repository.borrar(id)
                quitarDeLaLista(id)
            } catch (e: IOException) {
                mensaje = "No hay conexión. El aviso no se borró."
            } catch (e: HttpException) {
                // 404: ya no existe en el servidor, la lista estaba desactualizada.
                if (e.code() == 404) quitarDeLaLista(id)
                mensaje = mensajeDe(e)
            }
        }
    }

    // La pantalla ya mostró el mensaje; se limpia para que no reaparezca
    fun mensajeMostrado() {
        mensaje = null
    }

    private fun quitarDeLaLista(id: Int) {
        val actual = avisos
        if (actual is UiState.Exito) avisos = UiState.Exito(actual.datos.filterNot { it.id == id })
    }
}
