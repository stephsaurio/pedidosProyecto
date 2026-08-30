package com.so.pedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.DetallePedidoRepository;
import com.so.pedidos.model.Detalle_pedido;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallepedidoRepository;
    private final AuditLogService auditLogService;

    public DetallePedidoService(DetallePedidoRepository detallepedidoRepository, AuditLogService auditLogService) {
        this.detallepedidoRepository = detallepedidoRepository;
        this.auditLogService = auditLogService;
    }

    public List<Detalle_pedido> getAlldetallePedidos() {
        return (List<Detalle_pedido>) detallepedidoRepository.findAll();
    }

    public Optional<Detalle_pedido> findById(int par_id) {
        return detallepedidoRepository.findById(par_id);
    }

    public Detalle_pedido save(Detalle_pedido par_detallepedido) {
        Detalle_pedido guardado = detallepedidoRepository.save(par_detallepedido);

        if (guardado.getPedido() != null && guardado.getPedido().getCliente() != null) {
            auditLogService.registrar(
                guardado.getPedido().getCliente(),
                "detalle_pedido",
                guardado.getIddetalle(),
                "N/A",
                null,
                aJson(guardado)
            );
        }

        return guardado;
    }

    public void delete(Detalle_pedido par_detallepedido) {
        detallepedidoRepository.delete(par_detallepedido);

        if (par_detallepedido.getPedido() != null && par_detallepedido.getPedido().getCliente() != null) {
            auditLogService.registrar(
                par_detallepedido.getPedido().getCliente(),
                "detalle_pedido",
                par_detallepedido.getIddetalle(),
                "N/A",
                aJson(par_detallepedido),
                null
            );
        }
    }

    private String aJson(Detalle_pedido detalle) {
        return String.format(
            "{\"iddetalle\":%d,\"precioUnitario\":%.2f,\"cantidad\":%d,\"subTotal\":%.2f,\"idPedido\":%d,\"idProducto\":%d}",
            detalle.getIddetalle(),
            detalle.getPrecioUnitario(),
            detalle.getCantidad(),
            detalle.getSubTotal(),
            detalle.getPedido() != null ? detalle.getPedido().getIdpedido() : 0,
            detalle.getProducto() != null ? detalle.getProducto().getIdproducto() : 0
        );
    }

}
