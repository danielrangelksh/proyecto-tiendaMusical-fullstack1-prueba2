package com.fullstack.productos.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "productos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = true, name = "numeroSerie")
    private String numeroSerie;

    @Column(nullable = false, name = "nombreProducto")
    private String nombreProducto;

    @Column(nullable = false, name = "tipoInstrumento")
    private String tipoInstrumento;

    @Column(nullable = false, name = "descripcion")
    private String descripcion;

    @Column(nullable = true, name = "precioInstrumento")
    private Integer precioInstrumento;

    @Column(nullable = true, name = "precioArriendo")
    private Integer precioArriendo;

    @Column(nullable = false, name = "ventaArriendo")
    private String ventaArriendo;

    @Column(nullable = false, name = "estado")
    private String estado;

    @Column(nullable = true, name = "fechaRegistro")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

}