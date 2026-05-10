package com.fullstack.reclamos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamosRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 100, message = "El asunto no puede pasar los 100 carácteres.")
    private String asunto;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "El asunto no puede pasar los 1000 carácteres.")
    private String descripcion;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer clienteId;

}
