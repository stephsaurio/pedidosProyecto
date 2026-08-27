package com.so.pedidos.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "AuditLog")

public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAuditLog")
    private Integer idAuditLog;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @Column(name = "Tabla_afectada", length = 50)
    private String tablaAfectada;

    @Column(name = "Id_registro")
    private Integer idRegistro;

    @Column(name = "Fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "IP_address", length = 45)
    private String ipAddress;

    @Column(name = "Valores_anteriores", columnDefinition = "json")
    private String valoresAnteriores;

    @Column(name = "Valores_nuevos", columnDefinition = "json")
    private String valoresNuevos;

    public AuditLog() {
    }

    public AuditLog(Cliente cliente, String tablaAfectada, Integer idRegistro,
            String ipAddress, String valoresAnteriores, String valoresNuevos) {
        this.cliente = cliente;
        this.tablaAfectada = tablaAfectada;
        this.idRegistro = idRegistro;
        this.ipAddress = ipAddress;
        this.valoresAnteriores = valoresAnteriores;
        this.valoresNuevos = valoresNuevos;
    }

    public Integer getIdAuditLog() {
        return idAuditLog;
    }

    public void setIdAuditLog(Integer idAuditLog) {
        this.idAuditLog = idAuditLog;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTablaAfectada() {
        return tablaAfectada;
    }

    public void setTablaAfectada(String tablaAfectada) {
        this.tablaAfectada = tablaAfectada;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getValoresAnteriores() {
        return valoresAnteriores;
    }

    public void setValoresAnteriores(String valoresAnteriores) {
        this.valoresAnteriores = valoresAnteriores;
    }

    public String getValoresNuevos() {
        return valoresNuevos;
    }

    public void setValoresNuevos(String valoresNuevos) {
        this.valoresNuevos = valoresNuevos;
    }
    
}
