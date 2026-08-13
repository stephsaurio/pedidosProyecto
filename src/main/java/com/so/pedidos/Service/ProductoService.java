package com.so.pedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.ProductoRepository;
import com.so.pedidos.model.Producto;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository)
    {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAllProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    public Optional<Producto> findById(int par_id) {
        return productoRepository.findById(par_id);
    }

    public Producto save(Producto par_producto) {
        return productoRepository.save(par_producto);
    }

    public void delete(Producto par_producto) {
        productoRepository.delete(par_producto);
    }

}
