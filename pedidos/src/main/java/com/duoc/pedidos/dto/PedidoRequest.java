package com.duoc.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PedidoRequest {

    @NotNull(message = "La id de producto es obligatoria")
    @Column(nullable = false)
    private Integer productoId;

    @NotNull(message = "La id de cliente es obligatoria")
    @Column(nullable = false)
    private Integer clienteId;

    @NotBlank(message = "La direccion es obligatoria")
    @Column(nullable = false)
    private String direccion;

    @NotNull(message = "El precio no puede estar vacio")
    @Column(nullable = false)
    private Integer precioEnvio;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaPedido;
}
