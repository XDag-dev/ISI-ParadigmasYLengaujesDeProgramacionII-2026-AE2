package com.example.dominio;


// Se extiende de ItemFacturable especificando la naturaleza de bien tangible.
public class Producto extends ItemFacturable {

    // Constructor vacío requerido por Jackson
    protected Producto() {
        super();
    }

    public Producto(String codigo, String nombre, double precio, String tipo, Proveedor proveedor) {
        super(codigo, nombre, precio, tipo, proveedor);
    }

    @Override
    public String getDescripcion() {
        return "Producto: " + getNombre() + " (" + getTipo() + ")";
    }
}