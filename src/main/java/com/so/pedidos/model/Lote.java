package com.so.pedidos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "producto"})
@Table(name = "lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLote")
    private int idlote;

    @Column(name = "Estilo")
    private String estilo;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "Talla")
    private double talla;

    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> producto;

    public Lote(String estilo, int stock, double talla){
        this.estilo = estilo;
        this.stock = stock;
        this.talla = talla;
    }
    public Lote() {
}

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    

}