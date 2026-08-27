package com.so.pedidos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.so.pedidos.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

    List<AuditLog> findByTablaAfectada(String tablaAfectada);

    List<AuditLog> findByTablaAfectadaAndIdRegistro(String tablaAfectada, Integer idRegistro);

    List<AuditLog> findByCliente_IdCliente(Integer idCliente);

    
}
