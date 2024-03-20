DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE Usuario (
  id SERIAL,  
  contraseña VARCHAR(50) NOT NULL,  
  username VARCHAR(50) NOT NULL UNIQUE, 
  nacimiento DATE NOT NULL,  
  biografia VARCHAR(50) DEFAULT '', 
  email VARCHAR(50) NOT NULL UNIQUE,
  CONSTRAINT user_pkey PRIMARY KEY (id)  
);
