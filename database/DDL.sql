DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE SEQUENCE grupo_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

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


CREATE TABLE "public"."inscribir" (
    "usuario_id" integer NOT NULL,
    "torneo_id" character varying(100) NOT NULL,
    CONSTRAINT "inscribir_pkey" PRIMARY KEY ("usuario_id", "torneo_id")
) WITH (oids = false);


CREATE TABLE "public"."juego" (
    "nombre" character varying(100) NOT NULL,
    "tipo" character varying(50),
    CONSTRAINT "juego_pkey" PRIMARY KEY ("nombre")
) WITH (oids = false);


CREATE SEQUENCE match_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."match" (
    "id" integer DEFAULT nextval('match_id_seq') NOT NULL,
    "child_count" integer,
    "matchstatus" character varying(255),
    "number" integer,
    "group_id" integer,
    "round_id" integer,
    "stage_id" integer,
    CONSTRAINT "match_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE matchgame_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."matchgame" (
    "id" integer DEFAULT nextval('matchgame_id_seq') NOT NULL,
    "matchstatus" character varying(255),
    "number" integer,
    "match_id" integer,
    "stage_id" integer,
    CONSTRAINT "matchgame_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE participant_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."participant" (
    "id" integer DEFAULT nextval('participant_id_seq') NOT NULL,
    "tournament_id" character varying(255),
    CONSTRAINT "participant_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE participant_match_game_result_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."participant_match_game_result" (
    "id" integer DEFAULT nextval('participant_match_game_result_id_seq') NOT NULL,
    "forfeit" boolean,
    "position" integer,
    "matchresult" character varying(255),
    "score" integer,
    "opponent1matchgameid" integer,
    "opponent2matchgameid" integer,
    "participant_id" integer,
    CONSTRAINT "participant_match_game_result_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_dwlvo2cci93dagrgxq9sstr97" UNIQUE ("opponent2matchgameid"),
    CONSTRAINT "uk_jno288xqb2d264w1fg28c8d7k" UNIQUE ("opponent1matchgameid")
) WITH (oids = false);


CREATE SEQUENCE participant_match_result_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."participant_match_result" (
    "id" integer DEFAULT nextval('participant_match_result_id_seq') NOT NULL,
    "forfeit" boolean,
    "position" integer,
    "matchresult" character varying(255),
    "score" integer,
    "opponent1matchid" integer,
    "opponent2matchid" integer,
    "participant_id" integer,
    CONSTRAINT "participant_match_result_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_6unt18e9bp35jliy8d9a5lbtm" UNIQUE ("opponent1matchid"),
    CONSTRAINT "uk_haeidpiq8lesakibfloyyrsrq" UNIQUE ("opponent2matchid")
) WITH (oids = false);


CREATE SEQUENCE roles_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."roles" (
    "id" integer DEFAULT nextval('roles_id_seq') NOT NULL,
    "name" character varying(20),
    CONSTRAINT "roles_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE round_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."round" (
    "id" integer DEFAULT nextval('round_id_seq') NOT NULL,
    "number" integer,
    "group_id" integer,
    "stage_id" integer,
    CONSTRAINT "round_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE stage_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."stage" (
    "id" integer DEFAULT nextval('stage_id_seq') NOT NULL,
    "name" character varying(255),
    "number_id" integer,
    "tournament_id" character varying(255),
    "type" character varying(255),
    CONSTRAINT "stage_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE SEQUENCE stage_settings_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."stage_settings" (
    "id" integer DEFAULT nextval('stage_settings_id_seq') NOT NULL,
    "balance_byes" boolean,
    "group_count" integer,
    "matches_child_count" integer,
    "round_robin_mode" character varying(255),
    "size" integer,
    "stage_id" integer,
    CONSTRAINT "stage_settings_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_27mq2t2uuud3acho545i4xuxt" UNIQUE ("stage_id")
) WITH (oids = false);


CREATE TABLE "public"."torneo" (
    "nombre" character varying(100) NOT NULL,
    "jugadores" integer NOT NULL,
    "informacion" character varying(250) DEFAULT '',
    "estado" character varying(10),
    "tipo_torneo" character varying(50) DEFAULT 'Eliminación Directa',
    "tipo_juego" character varying(100) DEFAULT '',
    "fecha" date NOT NULL,
    "premio" integer DEFAULT '0',
    "inscritos" integer DEFAULT '0',
    "hora" time without time zone NOT NULL,
    "jugadores_por_equipo" integer DEFAULT '1' NOT NULL,
    "imagen" character varying(255),
    CONSTRAINT "torneo_pkey" PRIMARY KEY ("nombre")
) WITH (oids = false);


CREATE SEQUENCE usuario_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."usuario" (
    "id" integer DEFAULT nextval('usuario_id_seq') NOT NULL,
    "contraseña" character varying(64) NOT NULL,
    "username" character varying(50) NOT NULL,
    "nacimiento" date NOT NULL,
    "biografia" character varying(50) DEFAULT '',
    "email" character varying(50) NOT NULL,
    CONSTRAINT "usuario_email_key" UNIQUE ("email"),
    CONSTRAINT "usuario_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "usuario_username_key" UNIQUE ("username")
) WITH (oids = false);


ALTER TABLE ONLY "public"."grupo" ADD CONSTRAINT "fk189vbeqjrknrb9y2ylqlrj9m7" FOREIGN KEY (stage_id) REFERENCES stage(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."inscribir" ADD CONSTRAINT "inscribir_torneo_id_fkey" FOREIGN KEY (torneo_id) REFERENCES torneo(nombre) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."inscribir" ADD CONSTRAINT "inscribir_usuario_id_fkey" FOREIGN KEY (usuario_id) REFERENCES usuario(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."match" ADD CONSTRAINT "fk51ksextsfgx1eitl1wgfcoql8" FOREIGN KEY (stage_id) REFERENCES stage(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."match" ADD CONSTRAINT "fk6agetfj7o8q7s0075kkwcsvot" FOREIGN KEY (group_id) REFERENCES grupo(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."match" ADD CONSTRAINT "fkol8rkyfucvsv37sd7kebpm1nw" FOREIGN KEY (round_id) REFERENCES round(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."matchgame" ADD CONSTRAINT "fkhw0l9505abqlrl011ojw4jehb" FOREIGN KEY (stage_id) REFERENCES stage(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."matchgame" ADD CONSTRAINT "fko5tcyifbsvvk2la3lveiopw83" FOREIGN KEY (match_id) REFERENCES match(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."participant_match_game_result" ADD CONSTRAINT "fk9x8og5cx1d69ewek5phh1es2a" FOREIGN KEY (participant_id) REFERENCES participant(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."participant_match_game_result" ADD CONSTRAINT "fkqm1k009u5mr5vxs0ip0f2say6" FOREIGN KEY (opponent2matchgameid) REFERENCES matchgame(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."participant_match_game_result" ADD CONSTRAINT "fksmr9vohf1r1nqvqq8fab966ja" FOREIGN KEY (opponent1matchgameid) REFERENCES matchgame(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."participant_match_result" ADD CONSTRAINT "fk9vjg3d9woy4gyha4yh8reak7m" FOREIGN KEY (opponent2matchid) REFERENCES match(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."participant_match_result" ADD CONSTRAINT "fkeilslmw598ubrmwdmttfx6md5" FOREIGN KEY (opponent1matchid) REFERENCES match(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."participant_match_result" ADD CONSTRAINT "fkmxrj0m453pw2fvhpyvxly43b6" FOREIGN KEY (participant_id) REFERENCES participant(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."round" ADD CONSTRAINT "fk6esyebxjy4qos3xvcksym4oep" FOREIGN KEY (stage_id) REFERENCES stage(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."round" ADD CONSTRAINT "fkepfsiule452h7q2d1gq1eg2fv" FOREIGN KEY (group_id) REFERENCES grupo(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."stage_settings" ADD CONSTRAINT "fkgr3n9rafogqlcrcumie6uomt4" FOREIGN KEY (stage_id) REFERENCES stage(id) NOT DEFERRABLE;