import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Lote } from '../admin.model';
import { LoteService } from '../admin.service';

@Component({
  selector: 'app-lote-admin',
  imports: [CommonModule, FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './loteAdmin.html',
  styleUrl: './loteAdmin.css',
})
export class LoteAdmin implements OnInit {
  lotes: Lote[] = [];

  loteForms: Partial<Lote> = {};
  editando = false;

  constructor(
    private loteService: LoteService,
    private cdr: ChangeDetectorRef,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.cargarLotes();
  }

  cargarLotes(): void {
    this.loteService.listar().subscribe((data) => {
      this.lotes = data;
      this.cdr.detectChanges();
    });
  }

  guardar(): void {
    if (this.editando && this.loteForms.idlote) {
      this.loteService
        .actualizar(this.loteForms.idlote, this.loteForms)
        .subscribe(() => {
          this.cargarLotes();
          this.cancelar();
        });
    } else {
      this.loteService.crear(this.loteForms).subscribe(() => {
        this.cargarLotes();
        this.cancelar();
      });
    }
  }

  editar(lote: Lote): void {
    this.loteForms = { ...lote };
    this.editando = true;
    this.cdr.detectChanges();
  }

  eliminar(id: number): void {
    if (confirm('¿Seguro que deseas eliminar este lote?')) {
      this.loteService.eliminar(id).subscribe(() => {
        this.cargarLotes();
      });
    }
  }

  cancelar(): void {
    this.loteForms = {};
    this.editando = false;
    this.cdr.detectChanges();
  }
  cerrarSesion(): void {
    localStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }
}