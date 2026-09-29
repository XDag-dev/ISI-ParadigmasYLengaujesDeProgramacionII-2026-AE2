package com.example.consola;

import com.example.dominio.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private final Scanner scanner = new Scanner(System.in);

    // Listas dinámicas en memoria (listas para persistir en JSON más adelante)
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Empleado> empleados = new ArrayList<>();
    private final List<Proveedor> proveedores = new ArrayList<>();
    private final List<Departamento> departamentos = new ArrayList<>();
    private final List<ItemFacturable> catalogo = new ArrayList<>();
    private final List<Factura> facturas = new ArrayList<>();

    public void iniciar() {
        // Carga inicial de datos por defecto (1 de cada entidad principal)
        inicializarDatosPrueba();

        int opcion = -1;
        do {
            System.out.println("\n==================================================");
            System.out.println("           SISTEMA DE GESTIÓN COMERCIAL           ");
            System.out.println("==================================================");
            System.out.println("1. Gestión de Catálogo (Productos / Servicios)");
            System.out.println("2. Gestión de Personas (Clientes / Empleados / Proveedores)");
            System.out.println("3. Gestión Organizativa (Departamentos)");
            System.out.println("4. Facturación y Ventas (Emisión / Consultas)");
            System.out.println("0. Salir del Sistema");
            System.out.println("==================================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> menuCatalogo();
                    case 2 -> menuPersonas();
                    case 3 -> menuDepartamentos();
                    case 4 -> menuFacturacion();
                    case 0 -> System.out.println("Guardando y saliendo del sistema...");
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        } while (opcion != 0);
    }

    /**
     * Carga 1 elemento por defecto de cada entidad clave del dominio.
     */
    private void inicializarDatosPrueba() {
        // 1. Proveedor por defecto
        Proveedor prov1 = new Proveedor("Tech Supply S.A.", "30-11223344-5", "Av. Industrial 500", "011-44332211", CondicionIVA.RESPONSABLE_INSCRIPTO);
        proveedores.add(prov1);

        // 2. Producto y Servicio por defecto
        Producto prod1 = new Producto("P001", "Mouse Inalámbrico", 15000.0, "Hardware", prov1);
        Servicio serv1 = new Servicio("S001", "Instalación de Software", 8000.0, "Soporte Técnico", prov1);
        catalogo.add(prod1);
        catalogo.add(serv1);

        // 3. Cliente por defecto
        Cliente cli1 = new Cliente("Maria Gomez", "20-87654321-8", "Av. Siempreviva 742", "011-55554444", CondicionIVA.CONSUMIDOR_FINAL, 50000.0, "REGULAR");
        clientes.add(cli1);

        // 4. Departamento por defecto
        Departamento depSistemas = new Departamento("Sistemas y Soporte", 500000.0);
        departamentos.add(depSistemas);

        // 5. Empleado por defecto (asignado al departamento)
        Empleado emp1 = new Empleado("Juan Perez", "20-12345678-9", "Calle Falsa 123", "011-11223344", CondicionIVA.RESPONSABLE_INSCRIPTO, 350000.0, "Técnico", LocalDate.of(2023, 3, 15), depSistemas);
        empleados.add(emp1);
        depSistemas.agregarEmpleado(emp1);
        depSistemas.setResponsable(emp1);

        // 6. Factura C por defecto (con items, autorización ARCA y líneas asociadas)
        Factura fac1 = new Factura(1, 29, TipoComprobante.FACTURA_C, LocalDate.now(), cli1, emp1, MetodoPago.TRANSFERENCIA);
        fac1.agregarLinea(new LineaFactura(prod1, 2, 0.0)); // 2 Mouses
        fac1.agregarLinea(new LineaFactura(serv1, 1, 1000.0)); // 1 Servicio con bonificación
        fac1.setEstadoPago(EstadoPago.CANCELADO);
        fac1.autorizarARCA("74453966403949", LocalDate.now().plusDays(10), "https://www.arca.gob.ar/fe/qr/?p=eyJ2ZXJzaW9uIjoxfQ==");

        facturas.add(fac1);
        cli1.agregarFactura(fac1);
    }

    // =========================================================================
    // SUBMENÚS Y OPERACIONES CRUD
    // =========================================================================

    private void menuCatalogo() {
        System.out.println("\n--- SUBMENÚ: CATÁLOGO COMERCIAL ---");
        System.out.println("1. Listar Productos y Servicios");
        System.out.println("2. Cargar Nuevo Producto");
        System.out.println("3. Cargar Nuevo Servicio");
        System.out.println("4. Modificar / Eliminar Ítem");
        System.out.print("Opción: ");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> {
                System.out.println("\n=== CATÁLOGO DE ÍTEMS ===");
                for (ItemFacturable item : catalogo) {
                    System.out.println("[" + item.getCodigo() + "] " + item.getNombre() + " - $" + item.getPrecio() + " (" + item.getDescripcion() + ")");
                }
            }
            case 2, 3, 4 -> System.out.println("Función de carga/edición lista para solicitar datos por teclado.");
            default -> System.out.println("Opción inválida.");
        }
    }

    private void menuPersonas() {
        System.out.println("\n--- SUBMENÚ: GESTIÓN DE PERSONAS ---");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Listar Empleados");
        System.out.println("3. Listar Proveedores");
        System.out.println("4. Cargar Cliente");
        System.out.println("5. Cargar Empleado");
        System.out.println("6. Cargar Proveedor");
        System.out.print("Opción: ");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> {
                System.out.println("\n=== CLIENTES REGISTRADOS ===");
                for (Cliente c : clientes) {
                    System.out.println("- " + c.getNombre() + " | DNI/CUIT: " + c.getDni() + " | IVA: " + c.getCondicionIVA());
                }
            }
            case 2 -> {
                System.out.println("\n=== EMPLEADOS REGISTRADOS ===");
                for (Empleado e : empleados) {
                    System.out.println("- " + e.getNombre() + " | Cargo: " + e.getPuesto() + " | Depto: " + (e.getDepartamento() != null ? e.getDepartamento().getNombre() : "Sin Asignar"));
                }
            }
            case 3 -> {
                System.out.println("\n=== PROVEEDORES REGISTRADOS ===");
                for (Proveedor p : proveedores) {
                    System.out.println("- " + p.getRazonSocial() + " | CUIT: " + p.getCuit());
                }
            }
            case 4, 5, 6 -> System.out.println("Función de alta de persona lista para solicitar Scanner.");
            default -> System.out.println("Opción inválida.");
        }
    }

    private void menuDepartamentos() {
        System.out.println("\n--- SUBMENÚ: DEPARTAMENTOS ---");
        System.out.println("1. Listar Departamentos");
        System.out.println("2. Crear Departamento");
        System.out.println("3. Asignar Empleado a Departamento");
        System.out.print("Opción: ");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> {
                System.out.println("\n=== DEPARTAMENTOS DE LA EMPRESA ===");
                for (Departamento d : departamentos) {
                    System.out.println("Área: " + d.getNombre() + " | Presupuesto: $" + d.getPresupuesto() + " | Miembros: " + d.getEmpleados().size());
                }
            }
            case 2, 3 -> System.out.println("Función lista para conectar con la entrada del usuario.");
            default -> System.out.println("Opción inválida.");
        }
    }

    private void menuFacturacion() {
        System.out.println("\n--- SUBMENÚ: FACTURACIÓN Y VENTAS ---");
        System.out.println("1. Emitir Nueva Factura (Nueva Venta)");
        System.out.println("2. Listar Historial de Comprobantes");
        System.out.println("3. Ver Comprobante Impreso (Vista Previa / ARCA)");
        System.out.print("Opción: ");
        int op = Integer.parseInt(scanner.nextLine());
        switch (op) {
            case 1 -> System.out.println("Flujo de venta listo para capturar cliente, ítems y solicitar CAE.");
            case 2 -> {
                System.out.println("\n=== HISTORIAL DE FACTURAS ===");
                for (Factura f : facturas) {
                    System.out.println("N°: " + f.getPuntoVenta() + "-" + f.getNumeroComprobante() + " | Cliente: " + f.getCliente().getNombre() + " | Total: $" + f.calcularTotal() + " | CAE: " + f.getCae());
                }
            }
            case 3 -> {
                System.out.println("\n--- IMPRESIÓN DE LA FACTURA CARGADA POR DEFECTO ---");
                if (!facturas.isEmpty()) {
                    facturas.get(0).mostrarDetalle();
                }
            }
            default -> System.out.println("Opción inválida.");
        }
    }
}