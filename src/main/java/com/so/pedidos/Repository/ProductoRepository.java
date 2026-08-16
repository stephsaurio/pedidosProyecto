package com.so.pedidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.so.pedidos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer>{

}
