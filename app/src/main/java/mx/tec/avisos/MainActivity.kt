package mx.tec.avisos

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import mx.tec.avisos.ui.navigation.AvisosApp
import mx.tec.avisos.ui.theme.AvisosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Realmente nomas es esto?
        //Realmente solio es eso
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        setContent { AvisosTheme { AvisosApp() } }
    }
}
