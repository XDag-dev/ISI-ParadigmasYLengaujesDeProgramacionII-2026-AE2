package com.example.dominio;
import java.util.Comparator;

public class ComparadorFacturaPorMonto implements Comparator<Factura> {
    @Override
    public int compare(Factura f1, Factura f2) {
        // Ordena de mayor a menor monto (descendente)
        return Double.compare(f2.calcularTotal(), f1.calcularTotal()); 
    }
}