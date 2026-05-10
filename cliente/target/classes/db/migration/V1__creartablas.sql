CREATE TABLE clientes(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    run VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    fechaNacimiento date,
    correo VARCHAR(255) NOT NULL
);