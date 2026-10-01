package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

public class Departamento {

    private String nombre;
    private double presupuesto;
    private Empleado responsable;
    private List<Empleado> empleados;

    //------------------------------------------------------------------------------------------
    // Constructor vacío requerido por Jackson
    public Departamento() {
        this.empleados = new ArrayList<>();
    }
    //------------------------------------------------------------------------------------------

    public Departamento(String nombre, double presupuesto) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.empleados = new ArrayList<>();
    }

    public void asignarResponsable(Empleado empleado) {
        this.responsable = empleado;
    }

    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }


    // Getters y Setters

    // --- ---
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- ---

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) { 
        this.presupuesto = presupuesto; 
    }

    // --- ---

    public Empleado getResponsable() {
        return responsable;
    }
    

    public void setResponsable(Empleado responsable) {
    this.responsable = responsable;
    }

    // --- ---

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) { 
        this.empleados = empleados; 
    }

    // --- ---
}