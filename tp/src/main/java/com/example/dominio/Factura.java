package com.example.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {

    private int puntoVenta;
    private int numeroComprobante;
    private TipoComprobante tipoComprobante;
    private LocalDate fechaEmision;

    private Cliente cliente;
    private Empleado empleado;
    private List<LineaFactura> lineas;

    // Se integran los atributos de Pago.java (clase eliminada)
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;

    // Se tienen los atributos de Validación AFIP/ARCA (Se tomó como ejemplo)
    private String cae;
    private LocalDate fechaVencimienCAE;
    private String codigoQR;

    public Factura(int puntoVenta, int numeroComprobante, TipoComprobante tipoComprobante,
                   LocalDate fechaEmision, Cliente cliente, Empleado empleado,
                   MetodoPago metodoPago, EstadoPago estadoPago) {
        this.puntoVenta = puntoVenta;
        this.numeroComprobante = numeroComprobante;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.empleado = empleado;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.lineas = new ArrayList<>();
    }

    
}