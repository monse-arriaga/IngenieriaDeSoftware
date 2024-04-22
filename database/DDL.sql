DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE Usuario (
  id SERIAL,  
  contraseña VARCHAR(64) NOT NULL,  
  username VARCHAR(50) NOT NULL UNIQUE, 
  nacimiento DATE NOT NULL,  
  biografia VARCHAR(50) DEFAULT '', 
  email VARCHAR(50) NOT NULL UNIQUE,
  CONSTRAINT user_pkey PRIMARY KEY (id)  
);

CREATE TABLE Torneo (
  id SERIAL,
  nombre VARCHAR(100) NOT NULL,
  noJugadores INT NOT NULL,
  informacion VARCHAR(250) DEFAULT '',
  estado VARCHAR(10) CHECK (status IN ('finalizado', 'jugando', 'abierto')),
  tipoTorneo VARCHAR(50) DEFAULT 'Eliminación Directa', 
  tipoParticipante VARCHAR(10) CHECK (tipoParticipante in ('equipo', 'solitario'))
  fecha DATE NOT NULL,
  premio INT DEFAULT 0.
  inscritos INT DEFAULT 0,
  CONSTRAINT torneo_pkey PRIMARY KEY (id)
);

CREATE TABLE juego (
  nombre VARCHAR(100) NOT NULL,
  tipo VARCHAR(50)
  CONSTRAINT juego_pkey PRIMARY KEY (nombre)
)
