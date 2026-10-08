package com.techlab.producto.model;

/*
 * CLASE ABSTRACTA ARTICULO
 * --------------------------------------------------
 * Esta clase ahora es ABSTRACTA.
 *
 * ¿Qué significa abstracta?
 * Que no se puede crear un objeto directamente con:
 * new Producto(...)
 * sirve de plantilla para que otras clases hijas que concreten su implementación.
 *
 * ¿Por qué?
 * Porque queremos que todo artículo real sea de un tipo concreto:
 * - ProductoLibro
 * - ProductoDisco
 *
 * Esta clase contiene:
 * - lo común a todos los artículos
 * - atributos compartidos
 * - getters y setters compartidos
 * - lógica común como toString()
 */
public abstract class Producto extends Object {

    private int codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    /*
     * Constructor de la clase padre.
     *
     * Las clases hijas usarán super(...) para inicializar estos atributos comunes.
     */
    public Producto(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /*
     * Método abstracto.
     *
     * Obliga a cada clase hija o subclase a decir qué tipo de artículo es.
     */
    public abstract String getTipoProducto();

    /*
     * Método abstracto.
     *
     * Obliga a cada subclase a devolver su detalle específico.
     * Esto sirve para no meter toda la lógica en la clase padre.
     */
    public abstract String getDetalleEspecifico();

    /*
     * toString()
     * --------------------------------------------------
     * Este método es ideal para explicar:
     * - cómo mostrar un objeto de manera legible
     * - cómo aprovechar datos comunes
     * - cómo usar métodos abstractos dentro de una implementación común
     *
     * Observá que acá usamos getTipoProducto() y getDetalleEspecifico().
     * Esos métodos los resolverá cada clase hija.
     *
     * Eso es polimorfismo.
     * es la capacidad de un mismo methodo (toString()) de comportarse de manera diferente según la clase que lo implemente.
     * poli muchos
     * morfismo formas
     */
    @Override
    public String toString() {
        return "Producto {" +
                "código=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoría='" + categoria.getNombre() + '\'' +
                ", tipo='" + this.getTipoProducto() + '\'' +
                ", detalle='" + this.getDetalleEspecifico() + '\'' +
                '}';
    }
}
