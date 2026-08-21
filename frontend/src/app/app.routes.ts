import { Routes } from '@angular/router';

import { Registro } from './clientes/registro/registro';
import { Login } from './clientes/login/login';
import { Catalogo } from './producto/catalogo/catalogo';
import { Carro } from './producto/carro/carro';
import { ProductoAdmin } from './admin/producto/productoAdmin';
import { LoteAdmin } from './admin/lote/loteAdmin';
import { MisPedidos } from './producto/pedidos/mis-pedidos';
import { PedidoAdmin } from './admin/pedido/pedidoAdmin';

export const routes: Routes = [
  { path: 'registro', component: Registro },
  { path: 'login', component: Login },

  { path: 'catalogo', component: Catalogo },
  { path: 'carrito', component: Carro },
{ path: 'admin/pedidos', component: PedidoAdmin },
  { path: 'admin/productos', component: ProductoAdmin },
  { path: 'admin/lotes', component: LoteAdmin },
  { path: 'mis-pedidos', component: MisPedidos },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
];
