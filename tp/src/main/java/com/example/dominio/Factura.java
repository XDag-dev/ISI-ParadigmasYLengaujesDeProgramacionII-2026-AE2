package com.example.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Esta clase implementa más de una interfáz a la vez (contratos transversales)
public class Factura implements Comparable<Factura>, Imprimible, Exportable {

    private int puntoVenta;
    private int numeroComprobante;
    private TipoComprobante tipoComprobante;
    private LocalDate fechaEmision;
    
    private Cliente cliente;
    private Empleado empleado;
    private List<LineaFactura> lineas;

    // Atributos integrados de Pago
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;

    // Atributos de Validación ARCA / AFIP
    private String cae;
    private LocalDate fechaVencimientoCAE;
    private String codigoQR;

    public Factura(int puntoVenta, int numeroComprobante, TipoComprobante tipoComprobante, LocalDate fechaEmision, Cliente cliente, Empleado empleado, MetodoPago metodoPago) {
        this.puntoVenta = puntoVenta;
        this.numeroComprobante = numeroComprobante;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.empleado = empleado;
        this.metodoPago = metodoPago;
        this.lineas = new ArrayList<>();
    }

    // Método para agregar la línea recibiendo un objeto LineaFactura
    public void agregarLinea(LineaFactura linea) {
        this.lineas.add(linea);
    }

    // Setter para el estado del pago
    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public void agregarLinea(ItemFacturable item, int cantidad, double bonificacion) {
        this.lineas.add(new LineaFactura(item, cantidad, bonificacion));
    }

    public double calcularTotal() {
        double total = 0.0;
        for (LineaFactura linea : lineas) {
            total += linea.calcularSubtotal();
        }
        return total;
    }

    // Método para validar la factura y lanzar la excepción si está vacía
    public void procesarFactura() throws com.example.excepciones.FacturaSinItemsExcepcion {
        if (this.lineas.isEmpty()) {
            throw new com.example.excepciones.FacturaSinItemsException("Error crítico: No se puede procesar la Factura N° " + numeroComprobante + " porque no contiene ítems.");
        }
        System.out.println("Factura N° " + numeroComprobante + " validada y lista para emitir.");
    }

    // Métodos para asignar validación simulada de ARCA
    public void autorizarARCA(String cae, LocalDate fechaVencimientoCAE, String codigoQR) {
        this.cae = cae;
        this.fechaVencimientoCAE = fechaVencimientoCAE;
        this.codigoQR = codigoQR;
    }

    public void mostrarDetalle() {
        System.out.println("==========================================================");
        System.out.println("                   " + tipoComprobante.getDescripcion().toUpperCase() + " (COD. " + String.format("%03d", tipoComprobante.getCodigo()) + ")");
        System.out.println("Punto de Venta: " + String.format("%05d", puntoVenta) + " | Comp. Nro: " + String.format("%08d", numeroComprobante));
        System.out.println("Fecha de Emisión: " + fechaEmision);
        System.out.println("----------------------------------------------------------");
        System.out.println("CLIENTE / RECEPTOR:");
        System.out.println("  Nombre/Razón Social: " + cliente.getNombre());
        System.out.println("  DNI/CUIT: " + cliente.getDni() + " | Condición IVA: " + cliente.getCondicionIVA());
        System.out.println("  Domicilio: " + cliente.getDomicilio());
        System.out.println("EMPLEADO QUE GESTIONÓ: " + empleado.getNombre() + " (" + empleado.getPuesto() + ")");
        System.out.println("----------------------------------------------------------");
        System.out.println("DETALLE DE PRODUCTOS / SERVICIOS:");
        System.out.printf("%-10s %-25s %-6s %-10s %-10s%n", "Código", "Descripción", "Cant.", "P.Unit", "Subtotal");
        for (LineaFactura l : lineas) {
            System.out.printf("%-10s %-25s %-6d $%-9.2f $%-9.2f%n",
                    l.getItem().getCodigo(),
                    l.getItem().getNombre(),
                    l.getCantidad(),
                    l.getPrecioUnitario(),
                    l.calcularSubtotal());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("INFORMACIÓN DE PAGO:");
        System.out.println("  Método: " + metodoPago + " | Estado: " + estadoPago);
        System.out.printf("IMPORTE TOTAL: $%.2f%n", calcularTotal());
        System.out.println("----------------------------------------------------------");
        System.out.println("COMPROBANTE AUTORIZADO POR ARCA");
        System.out.println("CAE N°: " + (cae != null ? cae : "PENDIENTE"));
        System.out.println("Fecha Vto. CAE: " + (fechaVencimientoCAE != null ? fechaVencimientoCAE : "N/A"));
        System.out.println("URL Código QR: " + (codigoQR != null ? codigoQR : "N/A"));
        System.out.println("==========================================================");
    }
    
    // Getters
    public  int getPuntoVenta() {
        return puntoVenta;
    }

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public TipoComprobante getTipoComprobante() { 
        return tipoComprobante; 
    }

    public LocalDate getFechaEmision() { 
        return fechaEmision; 
    }

    public Cliente getCliente() { 
        return cliente; 
    }

    public Empleado getEmpleado() { 
        return empleado; 
    }

    public List<LineaFactura> getLineas() { 
        return lineas; 
    }

    public MetodoPago getMetodoPago() { 
        return metodoPago; 
    }

    public EstadoPago getEstadoPago() { 
        return estadoPago; 
    }

    public String getCae() { 
        return cae; 
    }

    public LocalDate getFechaVencimientoCAE() { 
        return fechaVencimientoCAE; 
    }

    public String getCodigoQR() { 
        return codigoQR; 
    }

    // --- IMPLEMENTACIÓN DE INTERFACES AE2 ---

    // 1. Contrato de Comparable (Orden Natural)
    @Override
    public int compareTo(Factura otraFactura) {
        // Ordenamos las facturas por su número de comprobante (de menor a mayor)
        return Integer.compare(this.numeroComprobante, otraFactura.numeroComprobante);
    }

    // 2. Contrato Imprimible
    @Override
    public void imprimirTicket() {
        // Como ya tienes un método "mostrarDetalle" espectacular, simplemente lo llamamos aquí
        // para cumplir el contrato de la interfaz sin duplicar código.
        this.mostrarDetalle(); 
    }

    // 3. Contrato Exportable (Para persistencia en TXT/JSON)
    @Override
    public String generarFilaTexto() {
        // Genera una línea separada por punto y coma lista para guardar en archivo
        return puntoVenta + ";" + numeroComprobante + ";" + fechaEmision + ";" + 
               cliente.getNombre() + ";" + calcularTotal();
    }
}

/*
 * Refactorización total para alinearse a las especificaciones de comprobantes 
 * fiscales de ARCA:   
 *
    * Mantiene numeración formal de ARCA: puntoVenta y numeroComprobante (ej. 00001-00000029).   
    * Incorpora datos fiscales de validación de ARCA: cae, fechaVencimientoCAE y codigoQR.   
    * Reemplaza la clase Pago integrando directamente metodoPago y estadoPago.   
    * Utiliza una lista dinámica List<LineaFactura>.   
 */