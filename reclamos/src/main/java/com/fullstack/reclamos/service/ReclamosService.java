package com.fullstack.reclamos.service;

import com.fullstack.reclamos.dto.ReclamosRequest;
import com.fullstack.reclamos.model.Reclamos;
import com.fullstack.reclamos.repository.ReclamosRepository;
import com.fullstack.reclamos.webClient.ClienteClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReclamosService {

    @Autowired
    private ReclamosRepository reclamosRepository;

    @Autowired
    private ClienteClient clienteClient;

    public List<Reclamos> listar() {return reclamosRepository.findAll();}

    public Reclamos buscarPorId(Integer id) {return reclamosRepository.findById(id).orElse(null);}

    public Reclamos guardar(Reclamos reclamos) {return reclamosRepository.save(reclamos);}

    public Reclamos crearDesdeRequest(ReclamosRequest request, String token){
        Map<String, Object> cliente = clienteClient.obtenerClienteId(request.getClienteId(), token);
        if(cliente == null || cliente.isEmpty()){
            throw new RuntimeException("Cliente no encontrado, no se puede agregar reclamo");
        }
        Reclamos reclamos = new Reclamos();
        reclamos.setAsunto(request.getAsunto());
        reclamos.setDescripcion(request.getDescripcion());
        reclamos.setFechaRegistro(request.getFechaRegistro());
        reclamos.setClienteId(request.getClienteId());
        String nombre = (String) cliente.get("nombre");
        String apellido = (String) cliente.get("apellido");
        reclamos.setNombreCliente(nombre + " " + apellido);
        return guardar(reclamos);
    }

    public boolean eliminar(Integer id) {
        Reclamos reclamos = buscarPorId(id);
        if(reclamos == null){
            return false;
        }
        reclamosRepository.deleteById(id);
        return true;
    }

}