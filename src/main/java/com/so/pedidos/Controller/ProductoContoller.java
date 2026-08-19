package com.so.pedidos.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.so.pedidos.Service.ProductoService;
import com.so.pedidos.model.Lote;
import com.so.pedidos.model.Producto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/producto")
public class ProductoContoller {

    private final ProductoService productoService;

    public ProductoContoller(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listproductos")
    public List<Producto> getAllProducto() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{par_id}")
    public Producto getProductoById(@PathVariable("par_id") int productoId) {
        Optional<Producto> ProductoOptional = productoService.findById(productoId);
        return ProductoOptional.orElse(null);
    }

    @PostMapping("/create_producto")
    public Producto createProducto(@RequestBody Producto par_Producto) {

        if (par_Producto != null && productoService.findById(par_Producto.getIdproducto()).isPresent()) {
            return null;
        } else {
            return productoService.save(par_Producto);
        }
    }

    @PutMapping("/update/{par_id}")
    public Producto updateProducto(@PathVariable("par_id") int productoId, @RequestBody Producto updatedproducto) {

        Optional<Producto> productoOptional = productoService.findById(productoId);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setNombreProducto(updatedproducto.getNombreProducto());
            producto.setDescripcion(updatedproducto.getDescripcion());
            producto.setPrecio(updatedproducto.getPrecio());
            producto.setLote(updatedproducto.getLote());
            return productoService.save(producto);
        } else {
            return null;
        }
    }

    @PatchMapping("/update_partial_producto/{par_id}")
    public Producto partialUpdateProducto(@PathVariable int par_id, @RequestBody Map<String, Object> updates) {
        Optional<Producto> ProductoOptional = productoService.findById(par_id);

        if (ProductoOptional.isPresent()) {
            Producto producto = ProductoOptional.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombreProducto":
                        producto.setNombreProducto((String) value);
                        break;
                    case "Descripcion":
                        producto.setDescripcion((String) value);
                        break;
                    case "Precio":
                        producto.setPrecio(Double.parseDouble((String) value));
                        break;
                    case "lote":
                        producto.setLote((Lote) value);
                        break;
                }
            });

            return productoService.save(producto);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete_producto/{id}")
    public void deleteProducto(@PathVariable("id") int productoId) {
        Optional<Producto> productoOptional = productoService.findById(productoId);
        if(productoOptional.isPresent()){
            productoService.delete(productoOptional.get());
        }
    }

}