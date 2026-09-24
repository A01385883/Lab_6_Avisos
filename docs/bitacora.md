Preguntas Ciberseguridad
¿Por qué la app no debe guardar la contraseña, si con eso pudiera entrar sola cada vez?

Ya que es un problema de seguridad, se puede obtener por actores maliciosos fácilmente

Cualquiera puede leer el payload de un JWT. ¿Por qué eso no es un problema? 

Porque manda datos los cuales realmente no importa que sean descubiertos (Ej Rol no se puede cambiar client side)

401 y 403: ¿qué le dice cada uno a tu app que haga? 

401 indica que no se pudo verificar quien eres, 403 indica que, si se verifico, pero no tienes permisos necesarios

Si el token de acceso se fuga, ¿qué puede hacer el servidor? ¿Y si se fuga el de refresco? 

El de acceso caduca en poco tiempo, pero no puede hacer mucho mas al respecto, el de refresco si lo puede revocar y retirar.

Un tutorial te dice que uses EncryptedSharedPreferences. ¿Qué haces?

Programar yo mismo esa version que son aproximadamente 40 lineas

Ejercicio 0

Dato	                      ¿Se guadra?	                                        ¿Con Que Protección?
Contraseña	                En la mente del usuario, y solamente ahí	          El usuario
Token de Acceso	            Si	                                                Cifrados
Token de Refresco	          Si	                                                Cifrados
Usuario y Rol	              Si	                                                Pueden ser públicos
El código del profesor	    No	                                                Realmente no se ocupa
Fecha Exp.	                Si	                                                Baja, no importa que se filtre


Ejercicio B3

Logcat, que se resuelve con el “redactHeader()”

Capturas de pantalla, se pueden mostrar como contraseñas (Osea que no muestre el dato real, pero si que hay uno)

Urls, se debe tener cuidado como se mandan

Reportes de error (Por ejemplo, si crashea la app), también se resuelven con redactHeader

Ejercicio C2

Primero el momento que presione el botón viewmodel detecta mi input, se intenta publicar los datos que tengo, se llama a la api, y al pedir el token actual se detecta la discrepancia entre datos causando el 403 con el exeption que muestra el UI.

No hubiera cambiado mucho donde se guarde el dato, ya que el servidor ya sabe el rol del usuario, así que desde ahí esta la seguridad que evita tener acceso a funciones que no corresponden
