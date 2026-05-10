package com.duoc.pedidos.controller;

import com.duoc.pedidos.dto.PedidoRequest;
import com.duoc.pedidos.model.Pedido;
import com.duoc.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedido(){
        List<Pedido> pedidos = pedidoService.getPedidos();
        if(pedidos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidosPorId(@PathVariable Integer id){
        Pedido pedido = pedidoService.buscarPorId(id);
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<?> crearPedidos(@Valid @RequestBody PedidoRequest request, @RequestHeader("Authorization") String token){
        Pedido pedidoGuardado = pedidoService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Integer id){
        boolean eliminado = pedidoService.eliminar(id);
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
