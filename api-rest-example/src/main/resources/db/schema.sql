
DROP TABLE IF EXISTS tiendas;

CREATE TABLE tiendas (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25)
);


DROP TABLE IF EXISTS productos;

CREATE TABLE productos (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25),
	shopid INTEGER 
);