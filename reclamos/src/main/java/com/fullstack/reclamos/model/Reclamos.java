package com.fullstack.reclamos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "reclamos")
@NoArgsConstructor
@AllArgsConstructor

public class Reclamos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer clienteId;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false)
    private String descripcion;
}
