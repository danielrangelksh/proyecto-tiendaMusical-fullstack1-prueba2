package com.fullstack.servicioTecnico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
public class ServicioTecnicoRequest {

    @NotNull(message = "El ID de el pedido es obligatorio")
    private Integer pedidoId;

    @NotBlank(message = "El asunto de la falla es obligatorio")
    @Size(max = 100, message = "La falla no debe superar los 100 carácteres.")
    private String falla;

    @NotBlank(message = "La descripción técnica es obligatoria")
    @Size(max = 1000, message = "La descripción no debe superar los 1000 carácteres.")
    private String descripcion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "(?i)RECIBIDO|ENTREGADO", message = "Estado no valido, USE: RECIBIDO o ENTREGADO.")
    private String estado;
}
