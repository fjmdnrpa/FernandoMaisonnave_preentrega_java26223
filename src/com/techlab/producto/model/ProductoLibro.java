package com.techlab.producto.model;

/*
 * CLASE ARTICULOALIMENTICIO
 * --------------------------------------------------
 * Esta clase también hereda de Articulo.
 *
 * Agrega como dato propio:
 * - días para vencimiento
 */
public class ProductoLibro extends Producto {

    private int paginas;

    public ProductoLibro(int codigo, String nombre, double precio, Categoria categoria, int paginas) {
        //llamo al constructor de la clase padre para inicializar los atributos heredados
        super(codigo, nombre, precio, categoria);
        this.paginas = paginas;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }


     // estos dos methdos son abstractos en la clase padre, por eso los implementamos acá. cada clase hija tiene su propia implementación.
     // y esta obligada a implementarlo. 
    @Override
    public String getTipoProducto() {
        return "Libro";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Páginas: " + paginas;
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo libro]";
    }
}
