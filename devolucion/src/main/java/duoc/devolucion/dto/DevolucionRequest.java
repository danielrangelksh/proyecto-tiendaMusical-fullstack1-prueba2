package duoc.devolucion.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DevolucionRequest {
    @NotNull(message = "El ID del pedido es obligatorio")
    private Integer pedidoId;

    @NotBlank(message = "El requerimiento (REEMBOLSO o CAMBIO) no puede estar vacio")
    @Pattern(regexp = "(?i)REEMBOLSO|CAMBIO", message = "Solo se permite 'REEMBOLSO' o 'CAMBIO'.")
    private String requerimiento;

    @NotBlank(message = "Debe indicar un motivo de devolución")
    private String motivo;

}
