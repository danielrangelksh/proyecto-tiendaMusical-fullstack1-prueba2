
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE productos;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO productos (numeroSerie, nombreProducto, tipoInstrumento, descripcion, precioInstrumento, precioArriendo, ventaArriendo, estado, fechaRegistro)
VALUES
    ('SN-STRAT-001', 'Fender Stratocaster', 'Cuerdas', 'Guitarra eléctrica clásica color Sunburst', 1200000, 45000, 'Venta', 'Disponible', '2026-01-10'),
    ('SN-YAM-P45', 'Yamaha P-45', 'Teclado', 'Piano digital de 88 teclas pesadas', 450000, 25000, 'Arriendo', 'Disponible', '2026-02-15'),
    ('SN-DW-PERC', 'DW Design Series', 'Percusión', 'Batería acústica de 5 piezas sin platillos', 1800000, 80000, 'Venta', 'Disponible', '2026-03-01'),
    ('SN-BOSS-DS1', 'Boss DS-1 Distortion', 'Accesorio', 'Pedal de distorsión para guitarra eléctrica', 65000, 5000, 'Venta', 'Agotado', '2026-04-20'),
    ('SN-MAR-JVM', 'Marshall JVM410H', 'Amplificación', 'Cabezal de amplificador a tubos de 100W', 1500000, 60000, 'Arriendo', 'Mantenimiento', '2026-05-01');