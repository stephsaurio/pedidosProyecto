import { Routes } from '@angular/router';
import { Registro } from './clientes/registro/registro';
import { Login } from './clientes/login/login';
import { Catalogo } from './producto/catalogo/catalogo';
import { ProductoAdmin } from './admin/producto/productoAdmin';
import { LoteAdmin } from './admin/lote/loteAdmin';

export const routes: Routes = [
  { path: 'registro', component: Registro },
  { path: 'login', component: Login },
  { path: 'catalogo', component: Catalogo },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'admin/productos', component: ProductoAdmin },
  { path: 'admin/lotes', component: LoteAdmin }
];