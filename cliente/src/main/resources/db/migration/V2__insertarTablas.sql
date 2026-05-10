
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE clientes;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO clientes (run, nombre, apellido, fechaNacimiento, correo)
VALUES
    ('11.111.111-1', 'Juan', 'Pérez', '1990-05-15', 'juan.perez@example.com'),
    ('22.222.222-2', 'María', 'González', '1985-08-20', 'm.gonzalez@provider.net'),
    ('33.333.333-3', 'Lucas', 'Silva', '2002-12-10', 'lucas_s@webmail.cl'),
    ('44.444.444-4', 'Ana', 'Morales', '1995-03-25', 'ana.morales@service.com'),
    ('55.555.555-5', 'Roberto', 'Díaz', '1988-11-02', 'roberto.diaz@company.org');