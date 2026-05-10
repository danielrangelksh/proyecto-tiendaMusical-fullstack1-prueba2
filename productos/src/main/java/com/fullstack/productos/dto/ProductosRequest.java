package com.fullstack.productos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosRequest {

    @Size(min = 5, max = 20, message = "El número de serie debe tener entre 5 a 20 digitos.")
    private String numeroSerie;

    @NotBlank(message = "El nombre del producto es obligatorio.")
    @Size(max = 100, message = "El nombre del producto no debe superar los 100 caracteres.")
    private String nombreProducto;

    @NotBlank(message = "El tipo de instrumento es obligatorio.")
    @Size(max = 100, message = "El tipo de instrumento no debe superar los 100 caracteres.")
    private String tipoInstrumento;

    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 1000, message = "La descripción del producto no debe superar los 1000 caracteres.")
    private String descripcion;

    private Integer precioInstrumento;

    private Integer precioArriendo;

    @NotBlank(message = "El tipo de negocio es obligatorio.")
    @Pattern(regexp = "(?i)VENTA|ARRIENDO", message = "Solo se permite 'VENTA' o 'ARRIENDO'.")
    private String ventaArriendo;

    @NotBlank(message = "Ingresar el estado es obligatorio.")
    @Pattern(regexp = "(?i)DISPONIBLE|VENDIDO|ARRENDADO|TALLER", message = "Estado no valido, USE: DISPONIBLE, VENDIDO, ARRENDADO o TALLER.")
    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;
}
