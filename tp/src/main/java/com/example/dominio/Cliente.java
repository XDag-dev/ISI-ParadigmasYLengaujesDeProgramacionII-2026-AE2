package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private double limiteCredito;
    private String categoria; // regular / premium / corporativo
    private List<Factura> historial;

    public Cliente(String nombre, String domicilio, String dni, String telefono,
                   CondicionIVA condicionIVA, double limiteCredito, String categoria) {
        super(nombre, domicilio, dni, telefono, condicionIVA);
        this.limiteCredito = limiteCredito;
        this.categoria = categoria;
        this.historial = new ArrayList<>();
    }

    public void agregarFactura(Factura factura) {
        this.historial.add(factura);
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<Factura> getHistorial() {
        return historial;
    }

    public String getDni() {
        return dni;
    }

    // Implementación de el método abstracto de la clase Persona.java
    @Override 
    public String getDetalleRol() {
        return "Rol: CLIENTE - Límite Crédito: $" + limiteCredito;
    }
}