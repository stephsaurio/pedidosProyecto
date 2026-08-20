package com.so.pedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.PedidoRepository;
import com.so.pedidos.model.Pedido;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(int par_id) {
        return pedidoRepository.findById(par_id);
    }

    public Pedido save(Pedido par_pedido) {
        return pedidoRepository.save(par_pedido);
    }

    public void delete(Pedido par_pedido) {
        pedidoRepository.delete(par_pedido);
    }

}
