package com.so.pedidos.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.ClienteRepository;
import com.so.pedidos.model.Cliente;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarCliente(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
