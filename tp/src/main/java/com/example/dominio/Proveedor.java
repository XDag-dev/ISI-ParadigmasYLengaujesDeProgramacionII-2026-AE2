package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

public class Proveedor extends Persona {

    private String razonSocial;
    private String cuit;
    private List<Producto> productos;

    public Proveedor(String razonSocial, String cuit, String domicilio, 
                    String telefono, CondicionIVA condicionIVA) {
       super(razonSocial, cuit, domicilio, telefono, condicionIVA);
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public String getRazonSocial() { 
        return getNombre(); 
    }

    public String getCuit() {
        return getDni();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // Implementación de el método abstracto de la clase Persona.java
    @Override 
    public String getDetalleRol() {
       return "Rol: CLIENTE - Categoría: " + razonSocial + " - Límite Crédito: $" + cuit;
    }
}