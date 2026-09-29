package com.example;

import com.example.dominio.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // 1. Crear Departamento y Empleado
        Departamento depSistemas = new Departamento("Sistemas", 500000.0);
        Empleado empleado1 = new Empleado("Juan Perez", "Calle 123", "12345678", "3764111111",
                CondicionIVA.RESPONSABLE_INSCRIPTO, 350000.0, "Tecnico", LocalDate.of(2023, 3, 1), depSistemas);
        depSistemas.asignarResponsable(empleado1);
        depSistemas.agregarEmpleado(empleado1);

        // 2. Crear Proveedor
        Proveedor proveedor1 = new Proveedor("Distribuidora SRL", "Ruta 12 km 5", "20111222",
                "3764222222", CondicionIVA.RESPONSABLE_INSCRIPTO, "Distribuidora SRL", "30-12345678-9");

        // 3. Crear Productos y Servicios
        Producto producto1 = new Producto("P001", "Mouse Inalambrico", 15000.0, "Periferico", proveedor1);
        Servicio servicio1 = new Servicio("S001", "Instalacion de Software", 8000.0, "Soporte", proveedor1);
        proveedor1.agregarProducto(producto1);

        // 4. Crear Cliente con su condición fiscal ante IVA
        Cliente cliente1 = new Cliente("Maria Gomez", "Av. Siempreviva 742", "20-87654321-8",
                "3764333333", CondicionIVA.CONSUMIDOR_FINAL, 100000.0, "Premium");

        // 5. Generar Factura C (Monotributo/Consumidor Final) estilo ARCA
        Factura factura1 = new Factura(
                1,                        // Punto de venta 00001
                29,                       // N° Comprobante 00000029
                TipoComprobante.FACTURA_C,// Factura C (Cod. 11)
                LocalDate.now(),
                cliente1,
                empleado1,
                MetodoPago.TRANSFERENCIA,
                EstadoPago.CANCELADO
        );

        // 6. Agregar renglones de detalle (Líneas de factura con cantidad y bonificación)
        factura1.agregarLinea(producto1, 2, 0.0); // 2 Mouses
        factura1.agregarLinea(servicio1, 1, 1000.0); // 1 Servicio con $1000 de descuento

        // 7. Simular Autorización Electrónica de ARCA (Asignación de CAE)
        factura1.autorizarARCA(
                "74453966403949",
                LocalDate.now().plusDays(10),
                "https://www.arca.gob.ar/fe/qr/?p=eyJ2ZXIiOjEsImZlY2hhIjoiMjAyNi0wOS0yOSJ9"
        );

        // 8. Vincular factura al cliente
        cliente1.agregarFactura(factura1);

        // 9. Imprimir comprobante fiscal en pantalla
        factura1.mostrarDetalle();
    }
}