package com.techlab.producto.model;

/*
 * CLASE CATEGORIA
 * --------------------------------------------------
 * En esta clase no hay cambios conceptuales grandes respecto de la clase anterior.
 * Sigue siendo un objeto que luego se asigna a los artículos.
 */
public class Categoria {

    private int codigo;
    private String nombre;
    private String descripcion;

    public Categoria(int codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoría {" +
                "código=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripción='" + descripcion + '\'' +
                '}';
    }
}
