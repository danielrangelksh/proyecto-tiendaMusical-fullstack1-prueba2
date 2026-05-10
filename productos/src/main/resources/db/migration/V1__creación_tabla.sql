CREATE TABLE productos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numeroSerie VARCHAR(255),
    nombreProducto VARCHAR(255) NOT NULL,
    tipoInstrumento VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precioInstrumento INTEGER,
    precioArriendo INTEGER,
    ventaArriendo VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    fechaRegistro date
);