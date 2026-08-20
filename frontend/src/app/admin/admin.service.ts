import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto, Lote } from './admin.model';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private url = 'http://localhost:8080/producto';
  constructor(private http: HttpClient) {}

  listar(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.url}/listproductos`);
  }

  crear(producto: Partial<Producto>): Observable<Producto> {
    return this.http.post<Producto>(`${this.url}/create_producto`, producto);
  }

  actualizar(id: number, producto: Partial<Producto>): Observable<Producto> {
    return this.http.put<Producto>(`${this.url}/update/${id}`, producto);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete_producto/${id}`);
  }
}

@Injectable({
  providedIn: 'root',
})
export class LoteService {
  private url = 'http://localhost:8080/lote';
  constructor(private http: HttpClient) {}

  listar(): Observable<Lote[]> {
    return this.http.get<Lote[]>(`${this.url}/listlote`);
  }

  crear(lote: Partial<Lote>): Observable<Lote> {
    return this.http.post<Lote>(`${this.url}/create_lote`, lote);
  }

  actualizar(id: number, lote: Partial<Lote>): Observable<Lote> {
    return this.http.put<Lote>(`${this.url}/update/${id}`, lote);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete_lote/${id}`);
  }
}
