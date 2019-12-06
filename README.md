Referencia para la creación de una petición hacia Place To Pay usando retrofit2

Antes de ejecutar es necesario actualizar la variable del login (ParametrosPlaceToPay.LOGIN) y de la llave secreta (ParametrosPlaceToPay.SECRET_KEY)
Y el endpoint si es necesario, se utiliza actualmente un sitio de pruebas y datos fictisios. No se filtra ningun dato que atente a la seguridad de PlaceToPay

Al iniciar la aplicación aparecerán dos botones: el primero genera una nueva petición y recolecta el url de la respuesta mientras que el segundo inicia una navegación hacia la URL del proceso.

Si retrofit devuelve un dato null significa que el login/secret-key no son correctos o que la creacion del transactkey en la autenticación ha fallado.

Para comprobar que el JSON (impreso en la consola de depuración) generado es correcto se puede utilizar: https://dnetix.co/p2p/client
o en su defecto realizar un POST online con https://reqbin.com/ apuntando al end point completo: https://test.placetopay.ec/redirection/api/session

NOTA 1: El WebView del emulador en Android debe ser capaz de ejecutar ese 'complejo' javascript. Si al pulsar en el botón que muestra el WebView con la dirección no ocurre nada entonces deberá probarse en un dispositivo real.
NOTA 2: La semilla aleatoria generada se refiere a una fecha obtenida.
NOTA 3: El nonce es un valor aleatorio generado en el momento.
