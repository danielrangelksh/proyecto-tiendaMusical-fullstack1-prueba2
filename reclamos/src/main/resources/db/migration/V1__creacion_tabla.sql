CREATE TABLE reclamos(
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         clienteId BIGINT NOT NULL,
                         fechaRegistro date,
                         asunto VARCHAR(255) NOT NULL,
                         descripcion VARCHAR(1000) NOT NULL,
                         nombreCliente VARCHAR(255) NOT NULL

);