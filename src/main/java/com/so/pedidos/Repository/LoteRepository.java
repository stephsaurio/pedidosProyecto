package com.so.pedidos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.so.pedidos.model.Lote;

public interface LoteRepository extends JpaRepository<Lote,Integer> {

}
