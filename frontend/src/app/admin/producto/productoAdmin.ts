import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Lote, Producto } from '../admin.model';
import { ProductoService, LoteService } from '../admin.service';

@Component({
  selector: 'app-producto-admin',
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './productoAdmin.html',
  styleUrl: './productoAdmin.css',
})
export class ProductoAdmin implements OnInit {
  productos: Producto[] = [];
  lotes: Lote[] = [];

  productoForms: Partial<Producto> = {};
  editando = false;

  constructor(
    private productoService: ProductoService,
    private loteService: LoteService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarProductos();
    this.loteService.listar().subscribe((data) => {
      this.lotes = data;
      this.cdr.detectChanges();
    });
  }

  cargarProductos(): void {
    this.productoService.listar().subscribe((data) => {
      this.productos = data;
      this.cdr.detectChanges();
    });
  }

  guardar(): void {
    if (this.editando && this.productoForms.idproducto) {
      this.productoService
        .actualizar(this.productoForms.idproducto, this.productoForms)
        .subscribe(() => {
          this.cargarProductos();
          this.cancelar();
        });
    } else {
      this.productoService.crear(this.productoForms).subscribe(() => {
        this.cargarProductos();
        this.cancelar();
      });
    }
  }

  editar(producto: Producto): void {
    this.productoForms = { ...producto };
    this.editando = true;
    this.cdr.detectChanges();
  }

  eliminar(id: number): void {
    if (confirm('¿Seguro que deseas eliminar este producto?')) {
      this.productoService.eliminar(id).subscribe(() => {
        this.cargarProductos();
      });
    }
  }

  cancelar(): void {
    this.productoForms = {};
    this.editando = false;
    this.cdr.detectChanges();
  }
}