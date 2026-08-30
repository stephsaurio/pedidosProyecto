package com.so.pedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.PedidoRepository;
import com.so.pedidos.model.Pedido;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final AuditLogService auditLogService;

    public PedidoService(PedidoRepository pedidoRepository, AuditLogService auditLogService) {
        this.pedidoRepository = pedidoRepository;
        this.auditLogService = auditLogService;
    }

    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(int par_id) {
        return pedidoRepository.findById(par_id);
    }

    public Pedido save(Pedido par_pedido) {
        Pedido guardado = pedidoRepository.save(par_pedido);

        if (guardado.getCliente() != null) {
            auditLogService.registrar(
                guardado.getCliente(),
                "pedido",
                guardado.getIdpedido(),
                "N/A",
                null,
                aJson(guardado)
            );
        }

        return guardado;
    }

    public void delete(Pedido par_pedido) {
        pedidoRepository.delete(par_pedido);

        if (par_pedido.getCliente() != null) {
            auditLogService.registrar(
                par_pedido.getCliente(),
                "pedido",
                par_pedido.getIdpedido(),
                "N/A",
                aJson(par_pedido),
                null
            );
        }
    }

    private String aJson(Pedido pedido) {
        return String.format(
            "{\"idpedido\":%d,\"fecha\":\"%s\",\"estado\":\"%s\",\"total\":%.2f}",
            pedido.getIdpedido(),
            pedido.getFecha(),
            pedido.getEstado(),
            pedido.getTotal()
        );
    }


}
