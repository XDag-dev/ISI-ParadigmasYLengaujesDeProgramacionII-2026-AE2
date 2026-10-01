package com.example.dominio;

// Importación necesaria para Jackson
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private double limiteCredito;
    private String categoria; // regular / premium / corporativo
    private List<Factura> historial;

    //------------------------------------------------------------------------------------------
    @JsonIgnore // Evita bucle de serialización circular con Factura
    private List<Factura> historial;

    // Constructor vacío requerido por Jackson
    protected Cliente() {
        super();
        this.historial = new ArrayList<>();
    }
    //------------------------------------------------------------------------------------------

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

    // Getters y Setters

    // --- ---
    
    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito){
        this.limiteCredito = limiteCredito;
    }

    // --- ---
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    // --- ---
    
    public List<Factura> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Factura> historial) {
        this.historial = historial;
    }
    
    // --- ---
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    // --- ---
    
    // Implementación de el método abstracto de la clase Persona.java
    @Override 
    public String getDetalleRol() {
       return "Rol: CLIENTE - Categoría: " + categoria + " - Límite Crédito: $" + limiteCredito;
    }
}