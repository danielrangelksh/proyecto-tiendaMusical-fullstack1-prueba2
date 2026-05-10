package com.fullstack.cliente.service;

import com.fullstack.cliente.dto.ClienteRequest;
import com.fullstack.cliente.model.Cliente;
import com.fullstack.cliente.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos(){return clienteRepository.findAll();}

    public Cliente buscarPorId(Integer id){return clienteRepository.findById(id).orElse(null);}

    public Cliente guardar(Cliente cliente){return clienteRepository.save(cliente);}

    public Cliente crearDesdeRequest(ClienteRequest request){
        Cliente cliente = new Cliente();
        cliente.setRun(request.getRun());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setFechaNacimiento(request.getFechaNacimiento());
        cliente.setCorreo(request.getCorreo());

        return guardar(cliente);
    }

    public Cliente actualizar(Integer id, ClienteRequest request){
        Cliente cliente = buscarPorId(id);
        if (cliente == null){
            return null;
        }
        cliente.setRun(request.getRun());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setFechaNacimiento(request.getFechaNacimiento());
        cliente.setCorreo(request.getCorreo());

        return guardar(cliente);
    }

    public boolean eliminar(Integer id){
        Cliente cliente = buscarPorId(id);
        if(cliente == null){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

}
