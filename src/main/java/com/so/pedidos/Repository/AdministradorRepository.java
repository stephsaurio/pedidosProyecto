package com.so.pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.so.pedidos.model.Administrador;
@Repository("administradorRepository")
public interface AdministradorRepository extends JpaRepository<Administrador, Integer>  {

    public List<Administrador> findByUsuarioAndPassword(String usuario, String password);
    
}
