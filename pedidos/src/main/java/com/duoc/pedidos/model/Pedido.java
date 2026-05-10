package com.duoc.pedidos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "pedidos")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer productoId;

    @Column(nullable = false)
    private Integer clienteId;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(unique = true, length = 20, nullable = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = false)
    private String tipoInstrumento;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = true)
    private Integer precioInstrumento;

    @Column(nullable = true)
    private Integer precioArriendo;

    @Column(nullable = false)
    private String ventaArriendo;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Integer precioEnvio;

    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaPedido;










}
