import {
  Component,
  OnInit,
  OnDestroy,
  ChangeDetectorRef
} from '@angular/core';

import { Router, NavigationStart, NavigationEnd } from '@angular/router';

import { ProductoService } from '../producto.service';

import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './catalogo.html',
  styleUrl: './catalogo.css'
})
export class Catalogo implements OnInit, OnDestroy {

  productos: any[] = [];

  constructor(
    private productoService: ProductoService,
    private cd: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {

    console.log('🟢 CATALOGO INICIADO');
    console.log('📍 RUTA ACTUAL:', this.router.url);

    this.router.events.subscribe(event => {

      if (event instanceof NavigationStart) {
        console.log('🚨 NAVEGACIÓN INICIADA:', event.url);
      }

      if (event instanceof NavigationEnd) {
        console.log('✅ NAVEGACIÓN TERMINADA:', event.urlAfterRedirects);
      }

    });

    this.cargarProductos();

  }

  cargarProductos(): void {

    console.log('🔵 PIDIENDO PRODUCTOS...');

    this.productoService.obtenerProductos().subscribe({

      next: (respuesta: any[]) => {

        console.log('📦 RESPUESTA:', respuesta);

        console.log(
          '📊 CANTIDAD RECIBIDA:',
          respuesta.length
        );

        this.productos = [...respuesta];

        console.log(
          '📊 CANTIDAD EN productos:',
          this.productos.length
        );

        this.cd.detectChanges();

      },

      error: (error: any) => {

        console.error(
          '🔴 ERROR AL CARGAR PRODUCTOS:',
          error
        );

      }

    });

  }

  ngOnDestroy(): void {

    console.log('🚨🚨 CATALOGO FUE DESTRUIDO');

  }

}