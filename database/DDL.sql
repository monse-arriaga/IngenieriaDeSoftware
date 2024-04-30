DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE Usuario (
  id SERIAL PRIMARY KEY,
  contraseña VARCHAR(64) NOT NULL,
  username VARCHAR(50) NOT NULL UNIQUE,
  nacimiento DATE NOT NULL,
  biografia VARCHAR(50) DEFAULT '',
  email VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Torneo (
  nombre VARCHAR(100) PRIMARY KEY,
  jugadores INT NOT NULL,
  informacion VARCHAR(250) DEFAULT '',
  estado VARCHAR(10) CHECK (estado IN ('finalizado', 'jugando', 'abierto')),
  tipo_torneo VARCHAR(50) DEFAULT 'Eliminación Directa',
  tipo_juego VARCHAR(100) DEFAULT '',
  fecha DATE NOT NULL,
  premio INT DEFAULT 0,
  inscritos INT DEFAULT 0,
  hora TIME NOT NULL,
  imagen VARCHAR(250) DEFAULT '',
  jugadores_por_equipo INT NOT NULL DEFAULT 1
);

CREATE TABLE Juego (
  nombre VARCHAR(100) PRIMARY KEY,
  tipo VARCHAR(50)
);

CREATE TABLE Inscribir (
  usuario_id INT REFERENCES Usuario(id),
  torneo_id VARCHAR(100) REFERENCES Torneo(nombre),
  PRIMARY KEY (usuario_id, torneo_id)
);
