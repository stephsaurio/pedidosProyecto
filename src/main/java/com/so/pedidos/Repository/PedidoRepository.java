package com.so.pedidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.so.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

}
