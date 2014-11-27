CREATE TABLE COCHE (
	matricula VARCHAR(12) not null,
	modelo VARCHAR(128) not null,
	ano INT,
	confort INT,
	PRIMARY KEY (matricula),
);

CREATE TABLE USUARIO (
	usuario VARCHAR(64) not null,
	clave VARCHAR(64) not null,
	fechanacimiento DATETIME,
	profesion VARCHAR(128),
	nombre VARCHAR(128),
	apellidos VARCHAR(128),
	email VARCHAR(45),
	coche VARCHAR(12),
	PRIMARY KEY (usuario),
	FOREIGN KEY (coche) REFERENCES COCHE(matricula)
	ON DELETE CASCADE
);

CREATE TABLE PARADA (
	id INT not null,
	ciudad VARCHAR (45),
	calle VARCHAR (128),
	CP INT,
	fecha DATETIME,
	PRIMARY KEY (id)
);

CREATE TABLE VIAJE (
	id INT not null,
	plazas INT,
	precio REAL,
	coche VARCHAR (12) not null,
	paradaorigen INT,
	paradadestino INT,
	PRIMARY KEY (id),
	FOREIGN KEY (coche) REFERENCES COCHE (matricula),
	FOREIGN KEY (paradaorigen) REFERENCES PARADA(id),
	FOREIGN KEY (paradadestino)REFERENCES PARADA(id)
);

CREATE TABLE RESERVA (
	id INT not null,
	comentario VARCHAR (256),
	usuario VARCHAR(64) not null,
	viaje INT not null,
	estado INT,	
	PRIMARY KEY (id),
	FOREIGN KEY (usuario) REFERENCES USUARIO (usuario),
	FOREIGN KEY (viaje) REFERENCES VIAJE (id)
);

CREATE TABLE VALORACION (
	id INT not null,
	comentario VARCHAR (256),
	puntuacion INT,
	reserva INT not null,
	usuarioemisor VARCHAR(64) not null,
	usuarioreceptor VARCHAR(64) not null,
	PRIMARY KEY (id),
	FOREIGN KEY (reserva) REFERENCES RESERVA (id),
	FOREIGN KEY (usuarioemisor) REFERENCES USUARIO (usuario),
	FOREIGN KEY (usuarioreceptor) REFERENCES USUARIO (usuario)
);