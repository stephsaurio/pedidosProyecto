export interface Lote {
  idlote: number;
  estilo: string;
  stock: number;
  talla: number;
}

export interface Producto {
  idproducto: number;
  nombreProducto: string;
  descripcion: string;
  precio: number;
  lote: Lote;
}