import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClienteService } from '../cliente.service';

@Component({
  selector: 'app-registro',
  imports: [FormsModule],
  templateUrl: './registro.html',
  styleUrl: './registro.css'
})
export class Registro {

  cliente = {
    nombreUsuario: '',
    password: '',
    nombreCompleto: '',
    telefono: null,
    correoElectronico: '',
    nit: ''
  };

  constructor(private clienteService: ClienteService) {
  }

  registrar() {
    this.clienteService.registrar(this.cliente).subscribe({
      next: (respuesta) => {
        console.log('Cliente registrado:', respuesta);
        alert('Cuenta creada correctamente');
      },
      error: (error) => {
        console.error('Error al registrar cliente:', error);
        alert('No se pudo crear la cuenta');
      }
    });
  }
}