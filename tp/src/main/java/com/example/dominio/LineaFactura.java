package com.example.dominio;


public class LineaFactura {

    private ItemFacturable item;
    private int cantidad;
    private double precioUnitario;
    private double bonificacion; // Porcentaje o monto de descuento

    //------------------------------------------------------------------------------------------
    // Constructor vacío requerido por Jackson
    public LineaFactura() {
    }
    //------------------------------------------------------------------------------------------

    public LineaFactura(ItemFacturable item, int cantidad, double bonificacion) {
        this.item = item;
        this.cantidad = cantidad;
        this.precioUnitario = item.getPrecio();
        this.bonificacion = bonificacion;
    }

    public double calcularSubtotal() {
        double totalSinDescuento = cantidad * precioUnitario;
        return totalSinDescuento - bonificacion;
    }

    public ItemFacturable getItem() {
        return item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getBonificacion() {
        return bonificacion;
    }
}

/*
 * Es una clase nueva, soluciona la falla de modelado donde una factura agregaba objetos
 * sueltos. Esta clase viene a representar un renglón del detalle con su cantidad, preciuUitario,
 * bonificacion (descuento) y el cálculo de su subtotal.
 */