package duoc.devolucion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
@Entity
@Table(name = "devoluciones")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer pedidoId;

    @Column(nullable = false)
    private String requerimiento;

    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(unique = true, length = 20, nullable = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = true)
    private Integer precioInstrumento;

}
