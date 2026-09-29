package com.example.dominio;

/**
 * Clase abstracta base para toda persona vinculada a la empresa.
 * No se intancia directamente: siempre a través de Cliente, Empleado o Proveedor 
 */

public abstract class Persona {
    
    private String nombre;
    private String domicilio;
    protected String dni;
    private String telefono;
    private CondicionIVA CondicionIVA;

    public Persona(String nombre, String domicilio, String dni, String telefono, CondicionIVA CondicionIVA){
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.telefono = telefono;
        this.CondicionIVA = CondicionIVA;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public CondicionIVA getCondicionIVA() {
        return CondicionIVA;
    }

    // Cambiamos la condición fiscal del cliente existente.
    public void setCondicionIVA(CondicionIVA CondiciónIVA){
        this.CondicionIVA = CondiciónIVA;
    }
}
