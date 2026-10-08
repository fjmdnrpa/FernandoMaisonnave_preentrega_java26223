package com.techlab.producto.model;

/*
 * CLASE ARTICULOELECTRONICO
 * --------------------------------------------------
 * Esta clase hereda de Articulo.
 *
 * Eso significa que:
 * - recibe todos los atributos comunes
 * - puede reutilizar getters y setters heredados
 * - solo agrega lo específico de este subtipo
 */
public class ProductoDisco extends Producto {

    private int duracionMinutos;

    /*
     * Constructor
     * --------------------------------------------------
     * Llama a super(...) para inicializar los atributos heredados.
     * Luego inicializa su atributo propio.
     */
    public ProductoDisco(int codigo, String nombre, double precio, Categoria categoria, int duracionMinutos) {
        super(codigo, nombre, precio, categoria);
        this.duracionMinutos = duracionMinutos;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String getTipoProducto() {
        return "Disco";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Duración: " + duracionMinutos + " minutos";
    }

    public String nroTelMesaDeAyudaParaReclamos() {
        return "0800-123-4567";
    }

    /*
     * toString() específico del hijo
     * --------------------------------------------------
     * Acá mostramos otra idea importante:
     * además del toString() de la clase padre, una clase hija también puede
     * sobrescribirlo si quiere agregar o cambiar el formato.
     *
     * En este caso reutilizamos super.toString() y le agregamos una aclaración.
     */
    @Override
    public String toString() {
        return super.toString() + " [subtipo disco]";
    }
}
