package com.so.pedidos.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private int idproducto;

    @Column(name = "Nombre_producto")
    private String nombreProducto;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Precio")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "idLote", nullable = false)
    private Lote lote;


    public Producto(String nombreProducto, String descripcion, double precio, Lote lote){
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.lote = lote;
    }


    public int getIdproducto() {
        return idproducto;
    }


    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }


    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public double getPrecio() {
        return precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public Lote getLote() {
        return lote;
    }


    public void setLote(Lote lote) {
        this.lote = lote;
    }

}
