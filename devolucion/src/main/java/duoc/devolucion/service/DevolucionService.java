package duoc.devolucion.service;
import duoc.devolucion.dto.DevolucionRequest;
import duoc.devolucion.model.Devolucion;
import duoc.devolucion.repository.DevolucionRepository;
import duoc.devolucion.webclient.PedidosClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private PedidosClient pedidosClient;

    public List<Devolucion> getDevolucion() {return devolucionRepository.findAll();}

    public Devolucion buscarPorId(Integer id){return devolucionRepository.findById(id).orElse(null);}

    public Devolucion guardarDevolucion(Devolucion devolucion) {return devolucionRepository.save(devolucion);}

    public Devolucion crearDesdeRequest(DevolucionRequest request, String token){
        Map<String, Object> pedidos = pedidosClient.obtenerPedidoId(request.getPedidoId(), token);
        if(pedidos == null || pedidos.isEmpty()){
            throw new RuntimeException("Pedido no encontrado");
        }
        Devolucion devolucion1 = new Devolucion();
        devolucion1.setPedidoId(request.getPedidoId()   );
        devolucion1.setRequerimiento(request.getRequerimiento());
        devolucion1.setMotivo(request.getMotivo());
        devolucion1.setNumeroSerie(pedidos.get("numeroSerie").toString());
        devolucion1.setNombreProducto(pedidos.get("nombreProducto").toString());
        devolucion1.setPrecioInstrumento((Integer) pedidos.get("precioInstrumento"));
        devolucion1.setNombreCliente(pedidos.get("nombreCliente").toString());
        return guardarDevolucion(devolucion1);
    }

    public boolean eliminar(Integer id) {
        Devolucion devolucion = buscarPorId(id);
        if(devolucion == null){
            return false;
        }
        devolucionRepository.deleteById(id);
        return true;
    }
    }