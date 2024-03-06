# FRONTEND PLAYBIT

Para generar el contenedor:

   - La primera vez:

      docker-compose up --build --no-recreate -d

   - Cualquiera otra iteración:

         docker-compose up -d

Accedemos al contenedor:

      docker exex -it vite_docker sh

Dentro del contenedor:

      npm i && npm run dev

