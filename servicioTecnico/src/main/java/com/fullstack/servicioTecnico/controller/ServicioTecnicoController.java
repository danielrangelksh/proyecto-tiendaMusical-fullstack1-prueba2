package com.fullstack.servicioTecnico.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fullstack.servicioTecnico.model.ServicioTecnico;
import com.fullstack.servicioTecnico.dto.ServicioTecnicoRequest;
import com.fullstack.servicioTecnico.service.ServicioTecnicoService;

import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;

@RestController
@RequestMapping("/api/servicioTecnico")
public class ServicioTecnicoController {

    @Autowired
    private ServicioTecnicoService servicioTecnicoService;

    @GetMapping
    public ResponseEntity<List<ServicioTecnico>> getServicios() {
        List<ServicioTecnico> servicios = servicioTecnicoService.listarServicioTecnico();
        if(servicios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServicioPorId(@PathVariable Integer id){
        ServicioTecnico servicioTecnico = servicioTecnicoService.buscarPorId(id);
        if(servicioTecnico == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicioTecnico);
    }

    @PostMapping
    public ResponseEntity<?> registrarServicio(@Valid @RequestBody ServicioTecnicoRequest request,
                                               @RequestHeader("Authorization") String token) {
        ServicioTecnico servicioTecnico = servicioTecnicoService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioTecnico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarServicioPorId(@PathVariable Integer id) {
        boolean eliminado = servicioTecnicoService.eliminar(id);
        if(!eliminado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/publico")
    public ResponseEntity<String> publico(){
        return ResponseEntity.ok("Endpoint público - Tienda musical");
    }
}
