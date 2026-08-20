package com.so.pedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.DetallePedidoRepository;
import com.so.pedidos.model.Detalle_pedido;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallepedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallepedidoRepository) {
        this.detallepedidoRepository = detallepedidoRepository;
    }

    public List<Detalle_pedido> getAlldetallePedidos() {
        return (List<Detalle_pedido>) detallepedidoRepository.findAll();
    }

    public Optional<Detalle_pedido> findById(int par_id) {
        return detallepedidoRepository.findById(par_id);
    }

    public Detalle_pedido save(Detalle_pedido par_detallepedido) {
        return detallepedidoRepository.save(par_detallepedido);
    }

    public void delete(Detalle_pedido par_detallepedido) {
        detallepedidoRepository.delete(par_detallepedido);
    }

}
