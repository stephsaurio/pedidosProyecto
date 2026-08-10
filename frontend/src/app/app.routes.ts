import { Routes } from '@angular/router';
import { Registro } from './clientes/registro/registro';
import { Login } from './clientes/login/login';

export const routes: Routes = [
    { path: 'registro', component: Registro },
    { path: 'login', component: Login },
    { path: '', redirectTo: 'login', pathMatch: 'full' }
];