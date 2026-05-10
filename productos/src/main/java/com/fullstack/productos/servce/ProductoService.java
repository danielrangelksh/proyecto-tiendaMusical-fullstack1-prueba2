package com.fullstack.productos.servce;

import com.fullstack.productos.dto.ProductosRequest;
import com.fullstack.productos.model.Producto;
import com.fullstack.productos.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos(){return productoRepository.findAll();}

    public Producto buscarPorId(Integer id){return productoRepository.findById(id).orElse(null);}

    public Producto guardar(Producto producto){return productoRepository.save(producto);}

    public Producto crearDesdeRequest(ProductosRequest request){
        Producto producto = new Producto();
        producto.setNumeroSerie(request.getNumeroSerie());
        producto.setNombreProducto(request.getNombreProducto());
        producto.setTipoInstrumento(request.getTipoInstrumento());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecioInstrumento(request.getPrecioInstrumento());
        producto.setPrecioArriendo(request.getPrecioArriendo());
        producto.setVentaArriendo(request.getVentaArriendo());
        producto.setEstado(request.getEstado());
        producto.setFechaRegistro(request.getFechaRegistro());

        return guardar(producto);
    }

    public Producto actualizar(Integer id, ProductosRequest request){
        Producto producto = buscarPorId(id);
        if (producto == null){
            return null;
        }
        producto.setNumeroSerie(request.getNumeroSerie());
        producto.setNombreProducto(request.getNombreProducto());
        producto.setTipoInstrumento(request.getTipoInstrumento());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecioInstrumento(request.getPrecioInstrumento());
        producto.setPrecioArriendo(request.getPrecioArriendo());
        producto.setVentaArriendo(request.getVentaArriendo());
        producto.setEstado(request.getEstado());
        producto.setFechaRegistro(request.getFechaRegistro());
        return guardar(producto);
    }

public boolean eliminar(Integer id){
        Producto producto = buscarPorId(id);
        if (producto == null){
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}
