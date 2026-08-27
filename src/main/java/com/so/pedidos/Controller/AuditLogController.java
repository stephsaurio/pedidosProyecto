package com.so.pedidos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.so.pedidos.Service.AuditLogService;
import com.so.pedidos.model.AuditLog;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auditlogs")

public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public List<AuditLog> listarTodos() {
        return auditLogService.listarTodos();
    }

    @GetMapping("/tabla/{tablaAfectada}")
    public List<AuditLog> listarPorTabla(@PathVariable String tablaAfectada) {
        return auditLogService.listarPorTabla(tablaAfectada);
    }

    @GetMapping("/registro")
    public List<AuditLog> listarPorRegistro(
            @RequestParam String tabla,
            @RequestParam Integer idRegistro) {
        return auditLogService.listarPorRegistro(tabla, idRegistro);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<AuditLog> listarPorCliente(@PathVariable Integer idCliente) {
        return auditLogService.listarPorCliente(idCliente);
    }
    
}
