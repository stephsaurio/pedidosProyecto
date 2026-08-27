package com.so.pedidos.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.AuditLogRepository;
import com.so.pedidos.model.AuditLog;
import com.so.pedidos.model.Cliente;

@Service

public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public AuditLog registrar(Cliente cliente, String tablaAfectada, Integer idRegistro,
            String ipAddress, String valoresAnteriores, String valoresNuevos) {

        AuditLog log = new AuditLog(cliente, tablaAfectada, idRegistro,
                ipAddress, valoresAnteriores, valoresNuevos);

        log.setFechaHora(LocalDateTime.now());

        return auditLogRepository.save(log);
    }

    public List<AuditLog> listarTodos() {
        return auditLogRepository.findAll();
    }

    public List<AuditLog> listarPorTabla(String tablaAfectada) {
        return auditLogRepository.findByTablaAfectada(tablaAfectada);
    }

    public List<AuditLog> listarPorRegistro(String tablaAfectada, Integer idRegistro) {
        return auditLogRepository.findByTablaAfectadaAndIdRegistro(tablaAfectada, idRegistro);
    }

    public List<AuditLog> listarPorCliente(Integer idCliente) {
        return auditLogRepository.findByCliente_IdCliente(idCliente);
    }
    
}
