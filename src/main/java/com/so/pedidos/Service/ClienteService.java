package com.so.pedidos.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.ClienteRepository;
import com.so.pedidos.model.Cliente;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final AuditLogService auditLogService;

    public ClienteService(ClienteRepository clienteRepository, AuditLogService auditLogService) {
        this.clienteRepository = clienteRepository;
        this.auditLogService = auditLogService;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        Cliente guardado = clienteRepository.save(cliente);

        auditLogService.registrar(
            guardado,
            "cliente",
            guardado.getIdCliente(),
            "N/A",
            null,
            aJson(guardado)
        );

        return guardado;

    }

  public Cliente iniciarSesion(String correo, String password) {

    Cliente cliente = clienteRepository
            .findByCorreoElectronicoAndPassword(correo, password);

    if (cliente == null) {
        throw new RuntimeException("Correo o contraseña incorrectos");
    }

    return cliente;
}

    public Cliente buscarCliente(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void eliminarCliente(Integer id) {
        Cliente existente = clienteRepository.findById(id).orElse(null);

        clienteRepository.deleteById(id);

        if (existente != null) {
            auditLogService.registrar(
                existente,
                "cliente",
                id,
                "N/A",
                aJson(existente),
                null
            );
        }
    }

    private String aJson(Cliente cliente) {
        return String.format(
            "{\"idCliente\":%d,\"nombreUsuario\":\"%s\",\"nombreCompleto\":\"%s\",\"correoElectronico\":\"%s\",\"rol\":\"%s\"}",
            cliente.getIdCliente(),
            cliente.getNombreUsuario(),
            cliente.getNombreCompleto(),
            cliente.getCorreoElectronico()
            
        );
    }
}
