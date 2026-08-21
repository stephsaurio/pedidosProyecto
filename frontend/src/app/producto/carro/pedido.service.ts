import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private pedidoUrl = 'http://localhost:8080/pedido';
  private detalleUrl = 'http://localhost:8080/detallepedido';

  constructor(private http: HttpClient) {}

  crearPedido(pedido: any): Observable<any> {
    return this.http.post(
      `${this.pedidoUrl}/create_pedido`,
      pedido
    );
  }

  crearDetalle(detalle: any): Observable<any> {
    return this.http.post(
      `${this.detalleUrl}/create_detallepedido`,
      detalle
    );
  }
}