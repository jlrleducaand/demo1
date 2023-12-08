DROP DATABASE IF EXISTS juego;
CREATE DATABASE juego CHARACTER SET utf8mb4;
USE juego;

CREATE TABLE partido (
                         id INT  AUTO_INCREMENT,
                         fecha DATE NOT NULL,
                         equipo1 VARCHAR(50) NOT NULL,
                         puntos_equipo1 INT,
                         equipo2 VARCHAR(50) NOT NULL,
                         puntos_equipo2 INT,
                         tipo_partido VARCHAR(50) NOT NULL CHECK (tipo_partido = 'amistoso' OR tipo_partido='oficial'),
                         PRIMARY KEY (id)
);

insert into partido (fecha, equipo1, puntos_equipo1, equipo2, puntos_equipo2, tipo_partido) value
    ('2023-12-4', "Alpujarra", 73, "Malaga", 67, 'oficial'),
    ('2023-12-4', "Fuengirola", 73, "Churriana", 67, 'oficial'),
    ('2023-12-4', "Cartama", 73, "El Palo", 67, 'oficial'),
    ('2023-12-4', "Mortadelo", 73, "La Palma", 67, 'oficial');


SELECT * FROM partido;
