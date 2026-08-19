package com.so.pedidos.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.AdministradorRepository;
import com.so.pedidos.model.Administrador;

@Service
public class AdministradorService {
    
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    public Administrador guardarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void eliminarAdministrador(Integer idAdministrador) {
        administradorRepository.deleteById(idAdministrador);
    }

    public List<Administrador> login(String usuario, String password) {
        return administradorRepository.findByUsuarioAndPassword(usuario, password);
    }
    
}
