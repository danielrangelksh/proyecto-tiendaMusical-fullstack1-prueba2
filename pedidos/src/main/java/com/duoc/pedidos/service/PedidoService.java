package com.duoc.pedidos.service;

import com.duoc.pedidos.dto.PedidoRequest;
import com.duoc.pedidos.model.Pedido;
import com.duoc.pedidos.repository.PedidoRepository;
import com.duoc.pedidos.webClient.ClienteClient;
import com.duoc.pedidos.webClient.ProductoClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional

public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProductoClient productoClient;

    public List<Pedido> getPedidos() {return pedidoRepository.findAll();}

    public Pedido buscarPorId(Integer id){return pedidoRepository.findById(id).orElse(null);}

    public Pedido guardarPedido(Pedido pedidos) {return pedidoRepository.save(pedidos);}

    public Pedido crearDesdeRequest(PedidoRequest request, String token){
        Map<String, Object> cliente = clienteClient.obtenerClienteId(request.getClienteId(), token);
        if(cliente == null || cliente.isEmpty()) throw new RuntimeException("Cliente no encontrado");

        Map<String, Object> producto = productoClient.obtenerProductoId(request.getProductoId(), token);
        if(producto == null || producto.isEmpty()) throw new RuntimeException("Producto no encontrado");

        Pedido pedido2 = new Pedido();

        pedido2.setClienteId(request.getClienteId());
        pedido2.setProductoId(request.getProductoId());
        pedido2.setDireccion(request.getDireccion());
        pedido2.setFechaPedido(request.getFechaPedido());
        pedido2.setPrecioEnvio(request.getPrecioEnvio());


        pedido2.setNombreProducto(producto.get("nombreProducto").toString());
        pedido2.setNumeroSerie(producto.get("numeroSerie") != null ? producto.get("numeroSerie").toString() : null);
        pedido2.setDescripcion(producto.get("descripcion").toString());
        pedido2.setTipoInstrumento(producto.get("tipoInstrumento").toString());
        pedido2.setVentaArriendo(producto.get("ventaArriendo").toString());
        pedido2.setEstado(producto.get("estado").toString());

        if(producto.get("precioInstrumento") != null) {
            pedido2.setPrecioInstrumento(Integer.parseInt(producto.get("precioInstrumento").toString()));
        }
        if(producto.get("precioArriendo") != null) {
            pedido2.setPrecioArriendo(Integer.parseInt(producto.get("precioArriendo").toString()));
        }

        if (producto.get("fechaRegistro") != null) {
            try {
                String fechaStr = producto.get("fechaRegistro").toString();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaConvertida = formatter.parse(fechaStr);
                pedido2.setFechaRegistro(fechaConvertida);
            } catch (Exception e) {
                System.out.println("Error al convertir fecha: " + e.getMessage());

            }
        }

        String nombre = cliente.get("nombre").toString();
        String apellido = cliente.get("apellido").toString();
        pedido2.setNombreCliente(nombre + " " + apellido);

        return pedidoRepository.save(pedido2);
    }

    public boolean eliminar(Integer id) {
        Pedido pedido = buscarPorId(id);
        if(pedido == null){
            return false;
        }
        pedidoRepository.deleteById(id);
        return true;
    }


}
