package com.so.pedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nombre;
  private String password;
  private String nombreCompleto;
  private Integer telefono;
  private String Correo_electronico;
  private String NIT;

  public Cliente(String nombre, String password, String nombreCompleto, Integer telefono, String Correo_electronico,
      String NIT) {
    this.nombre = nombre;
    this.password = password;
    this.nombreCompleto = nombreCompleto;
    this.telefono = telefono;
    this.Correo_electronico = Correo_electronico;
    this.NIT = NIT;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  public Integer getTelefono() {
    return telefono;
  }

  public void setTelefono(Integer telefono) {
    this.telefono = telefono;
  }

  public String getCorreo_electronico() {
    return Correo_electronico;
  }

  public void setCorreo_electronico(String correo_electronico) {
    Correo_electronico = correo_electronico;
  }

  public String getNIT() {
    return NIT;
  }

  public void setNIT(String nIT) {
    NIT = nIT;
  }

  public Cliente() {
  }

}
