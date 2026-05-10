package com.fullstack.reclamos.controller;

import com.fullstack.reclamos.dto.ReclamosRequest;
import com.fullstack.reclamos.model.Reclamos;
import com.fullstack.reclamos.service.ReclamosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamosController {

    @Autowired
    private ReclamosService reclamosService;

    @GetMapping
    public ResponseEntity<List<Reclamos>> listarReclamos(){
        List<Reclamos> reclamos = reclamosService.listar();
        if(reclamos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reclamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReclamosPorId(@PathVariable Integer id){
        Reclamos reclamos = reclamosService.buscarPorId(id);
        if(reclamos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reclamos);
    }

    @PostMapping
    public ResponseEntity<?> crearReclamos(@Valid @RequestBody ReclamosRequest request, @RequestHeader("Authorization") String token){
        Reclamos reclamosGuardado = reclamosService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamosGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReclamos(@PathVariable Integer id){
        boolean eliminado = reclamosService.eliminar(id);
        if(!eliminado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publico")
    public ResponseEntity<String> publico(){
        return ResponseEntity.ok("Endpoint público - Tienda musical");
    }
}