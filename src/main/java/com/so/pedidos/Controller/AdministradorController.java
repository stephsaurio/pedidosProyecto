package com.so.pedidos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.so.pedidos.Service.AdministradorService;
import com.so.pedidos.model.Administrador;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/administradores")
public class AdministradorController {


    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public List<Administrador> listarAdministradores() {
        return administradorService.listarAdministradores();
    }

    @PostMapping
    public Administrador guardarAdministrador(
            @RequestBody Administrador administrador) {

        return administradorService.guardarAdministrador(administrador);
    }

     @DeleteMapping("/{idAdministrador}")
    public void eliminarAdministrador(
            @PathVariable Integer idAdministrador) {

        administradorService.eliminarAdministrador(idAdministrador);
    }

    @PostMapping("/login")
    public List<Administrador> login(
            @RequestBody Administrador administrador) {

        return administradorService.login(
                administrador.getUsuario(),
                administrador.getPassword()
        );
    }
    
}
