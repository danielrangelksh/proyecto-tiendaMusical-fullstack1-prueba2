package com.fullstack.servicioTecnico.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "serviciotecnico")
@NoArgsConstructor
@AllArgsConstructor
public class ServicioTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String numeroSerie;

    @Column(nullable = false)
    private Integer pedidoId;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    @Column(nullable = false)
    private String falla;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String estado;
}
