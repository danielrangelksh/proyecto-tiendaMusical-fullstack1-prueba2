CREATE TABLE devoluciones (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              pedidoId BIGINT NOT NULL,
                              motivo VARCHAR(255) NOT NULL,
                              requerimiento VARCHAR(50) NOT NULL,
                              nombreCliente VARCHAR(255) NOT NULL,
                              numeroSerie VARCHAR(255),
                              nombreProducto VARCHAR(255) NOT NULL,
                              precioInstrumento INTEGER
);