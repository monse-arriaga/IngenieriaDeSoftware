# IngenieriaDeSoftware

## Ejecución

### Importante: Aún no está implementado el docker del back.

Para ejecutar el frontend y la base de datos ejecutamos el docker compose

        docker-compose up -d

Para el back nos movemos a la carpeta back y ejecutamos

        ./mvnw spring-boot:run

Posteriormente podemos acceder al localhost.

Para el front(windows)
Despues de hacer docker-compose up -d, se tiene que acceder a la carpeta de front y correr npm run dev. Es importante tener Docker 
Desktop
