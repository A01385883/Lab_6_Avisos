package mx.tec.avisos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.tec.avisos.domain.Aviso
import mx.tec.avisos.ui.theme.AvisosTheme

@Composable
fun AvisoCard(
    aviso: Aviso,
    modifier: Modifier = Modifier,
    puedeBorrar: Boolean = false,
    onBorrar: () -> Unit = {}
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = aviso.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                if (puedeBorrar) {
                    IconButton(onClick = onBorrar) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Borrar aviso",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(aviso.cuerpo, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            Text(
                text = "${aviso.autor} · ${aviso.creadoEn}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AvisoCardPreview() {
    AvisosTheme {
        AvisoCard(
            Aviso(1, "Examen parcial", "El parcial es el jueves a las 10:00 en el salón tonoto.", "profesor", "2026-09-21 10:00:00")
        )
    }
}