# IngenieriaDeSoftware

## Ejecución

- **Para la correcta ejecución del sistema es necesario un documento .env situado en la raiz con lo siguiente: **

        DB=Playbit
        USERNAME=myUsername
        PASSWORD=myPassword

   Tanto USERNAME como PASSWORD son elección del usuario.

- Para ejecutar la pagina es necesario primero construirla.

        docker-compose build

- Si se quiere ejecutar y visualizar como es que el codigo se ve afectado por los cambios ejecutar:

        docker-compose watch

- Si se busca unicamente ejecutar la version actual del programa sin visualizar cambios al momento ejecutar:

        docker-compose up -d
