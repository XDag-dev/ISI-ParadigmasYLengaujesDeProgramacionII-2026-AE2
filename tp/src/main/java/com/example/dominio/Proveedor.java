package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

public class Proveedor extends Persona {

    private String razonSocial;
    private String cuit;
    private List<Producto> productos;

    public Proveedor(String nombre, String domicilio, String dni, String telefono,
                     CondicionIVA condicionIVA, String razonSocial, String cuit) {
        super(nombre, domicilio, dni, telefono, condicionIVA);
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}