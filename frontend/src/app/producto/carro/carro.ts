import {
  Component,
  OnInit,
  Inject,
  PLATFORM_ID
} from '@angular/core';

import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';
import { PedidoService } from './pedido.service';
@Component({
  selector: 'app-carro',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carro.html',
  styleUrl: './carro.css'
})
export class Carro implements OnInit {

  carrito: any[] = [];

  constructor(
    private router: Router,
    private pedidoService: PedidoService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    this.cargarCarrito();
  }

  cargarCarrito(): void {

    if (!isPlatformBrowser(this.platformId)) {
      return;
    }

    const datos = localStorage.getItem('carrito');

    if (datos) {
      this.carrito = JSON.parse(datos);

      this.carrito.forEach(producto => {
        if (!producto.cantidad) {
          producto.cantidad = 1;
        }
      });
    }
  }

  guardarCarrito(): void {

    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(
        'carrito',
        JSON.stringify(this.carrito)
      );
    }
  }

  aumentarCantidad(index: number): void {
    this.carrito[index].cantidad++;
    this.guardarCarrito();
  }

  disminuirCantidad(index: number): void {

    if (this.carrito[index].cantidad > 1) {
      this.carrito[index].cantidad--;
      this.guardarCarrito();
    }
  }

  eliminar(index: number): void {
    this.carrito.splice(index, 1);
    this.guardarCarrito();
  }

  vaciarCarrito(): void {

    if (confirm('¿Deseas vaciar el carrito?')) {

      this.carrito = [];

      if (isPlatformBrowser(this.platformId)) {
        localStorage.removeItem('carrito');
      }
    }
  }

  calcularSubtotal(producto: any): number {
    return producto.precio * producto.cantidad;
  }

  calcularTotal(): number {

    return this.carrito.reduce(
      (total, producto) =>
        total + (producto.precio * producto.cantidad),
      0
    );
  }

  volverCatalogo(): void {
    this.router.navigate(['/catalogo']);
  }

realizarPedido(): void {

  if (this.carrito.length === 0) {
    alert('El carrito está vacío');
    return;
  }

  if (!isPlatformBrowser(this.platformId)) {
    return;
  }

  const datosUsuario = localStorage.getItem('usuario');

  if (!datosUsuario) {
    alert('Debes iniciar sesión');
    this.router.navigate(['/login']);
    return;
  }

  const usuario = JSON.parse(datosUsuario);

  const pedido = {
    fecha: new Date().toISOString().split('T')[0],
    estado: 'PENDIENTE',
    total: this.calcularTotal(),
    cliente: {
      idCliente: usuario.idCliente
    }
  };

  this.pedidoService.crearPedido(pedido).subscribe({

    next: (pedidoCreado: any) => {

      this.carrito.forEach(producto => {

        const detalle = {
          precioUnitario: producto.precio,
          cantidad: producto.cantidad,
          subTotal: producto.precio * producto.cantidad,

          pedido: {
            idpedido: pedidoCreado.idpedido
          },

          producto: {
            idproducto: producto.idproducto
          }
        };

        this.pedidoService.crearDetalle(detalle).subscribe();
      });

      localStorage.removeItem('carrito');
      this.carrito = [];

      alert('Pedido realizado correctamente');

      this.router.navigate(['/catalogo']);
    },

    error: (error) => {
      console.error('Error al realizar pedido:', error);
      alert('No se pudo realizar el pedido');
    }

  });
}
}