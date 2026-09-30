package com.example.dominio;

import java.time.LocalDate;

public class Empleado extends Persona {

    private double salario;
    private String puesto; // administrativo / técnico / gerente
    private LocalDate fechaIngreso;
    private Departamento departamento;

    public Empleado(String nombre, String domicilio, String dni, String telefono,
                    CondicionIVA condicionIVA, double salario, String puesto,
                    LocalDate fechaIngreso, Departamento departamento) {
        super(nombre, domicilio, dni, telefono, condicionIVA);
        this.salario = salario;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

     // Implementación de el método abstracto de la clase Persona.java
    @Override 
    public String getDetalleRol() {
       return "Rol: CLIENTE - Categoría: " + puesto + " - Límite Crédito: $" + salario;
    }
}