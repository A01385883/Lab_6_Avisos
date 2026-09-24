package mx.tec.avisos.data.remote

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Firma cada petición con el token de acceso, si hay uno.
 *
 * Recibe una función y no el repositorio: la capa de red no necesita saber
 * dónde vive la sesión, solo cómo pedir el token. Corre en un hilo de OkHttp,
 * nunca en el principal, así que la función puede bloquear.
 */
class AuthInterceptor(
    private val tokenVigente: () -> String?,
    private val tokenActual: () -> String?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val path = original.url.encodedPath
        val esRutaDeSesion = RUTAS_DE_SESION.any { path.endsWith(it) }

        val token = (if (esRutaDeSesion) tokenActual() else tokenVigente())
            ?: return chain.proceed(original)

        val firmada = original.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(firmada)
    }

    private companion object {
        val RUTAS_DE_SESION = listOf("/auth/login", "/auth/register", "/auth/refresh", "/auth/logout")
    }
}
