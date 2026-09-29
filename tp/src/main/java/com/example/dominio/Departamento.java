package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

public class Departamento {

    private String nombre;
    private double presupuesto;
    private Empleado responsable;
    private List<Empleado> empleados;

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

    public String getNombre() {
        return nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}