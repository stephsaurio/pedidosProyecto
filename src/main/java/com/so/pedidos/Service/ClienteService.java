package com.so.pedidos.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.ClienteRepository;
import com.so.pedidos.model.Cliente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
private final BCryptPasswordEncoder passwordEncoder =
        new BCryptPasswordEncoder();
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

 public Cliente guardarCliente(Cliente cliente) {

    if (cliente.getRol() == null || cliente.getRol().isBlank()) {
        cliente.setRol("CLIENTE");
    }

    String passwordHash =
        passwordEncoder.encode(cliente.getPassword());

    cliente.setPassword(passwordHash);

    return clienteRepository.save(cliente);
}

 public Cliente iniciarSesion(String correo, String password) {

    Cliente cliente = clienteRepository.findByCorreoElectronico(correo);

    if (cliente == null ||
        !passwordEncoder.matches(password, cliente.getPassword())) {

        throw new RuntimeException("Correo o contraseña incorrectos");
    }

    return cliente;
}

    public Cliente buscarCliente(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
