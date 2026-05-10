package duoc.devolucion.controller;

import duoc.devolucion.dto.DevolucionRequest;
import duoc.devolucion.model.Devolucion;
import duoc.devolucion.service.DevolucionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")

public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public ResponseEntity<List<Devolucion>> getDevolucion(){
        List<Devolucion> devolucions = devolucionService.getDevolucion();
        if(devolucions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(devolucions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDevolucionPorId(@PathVariable Integer id){
        Devolucion devolucion = devolucionService.buscarPorId(id);
        if(devolucion == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(devolucion);
    }

    @PostMapping
    public ResponseEntity<?> crearDevolucion(@Valid @RequestBody DevolucionRequest request, @RequestHeader("Authorization") String token){
        Devolucion devolucionGuardada = devolucionService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(devolucionGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDevolucion(@PathVariable Integer id){
        boolean eliminado = devolucionService.eliminar(id);
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
