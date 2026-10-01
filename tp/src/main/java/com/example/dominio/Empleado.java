package com.example.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

public class Empleado extends Persona {

    private double salario;
    private String puesto; // administrativo / técnico / gerente
    private LocalDate fechaIngreso;
   
    //------------------------------------------------------------------------------------------
    @JsonIgnore // Evita referencia circular con Departamento
    private Departamento departamento;

    // Constructor vacío requerido por Jackson
    protected Empleado() {
        super();
    }
    //------------------------------------------------------------------------------------------

    public Empleado(String nombre, String domicilio, String dni, String telefono,
                    CondicionIVA condicionIVA, double salario, String puesto,
                    LocalDate fechaIngreso, Departamento departamento) {
        super(nombre, domicilio, dni, telefono, condicionIVA);
        this.salario = salario;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.departamento = departamento;
    }

    // Getrers y Setters

    // --- ----
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // --- ----

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto){
        this.puesto = puesto;
    }

    // --- ----

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // --- ----
    
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    // --- ----

    // Implementación de el método abstracto de la clase Persona.java
    @Override 
    public String getDetalleRol() {
       return "Rol: CLIENTE - Categoría: " + puesto + " - Límite Crédito: $" + salario;
    }
}