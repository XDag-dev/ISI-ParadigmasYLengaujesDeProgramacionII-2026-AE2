package com.example.consola;

import com.example.dominio.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private final Scanner scanner = new Scanner(System.in);

    // Listas dinámicas globales en memoria
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
            System.out.println("4. Facturación y Ventas (Emisión ARCA / Consultas)");
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
                    case 0 -> System.out.println("Guardando datos y saliendo del sistema...");
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        } while (opcion != 0);
    }

    private void inicializarDatosPrueba() {
        Proveedor prov1 = new Proveedor("Tech Supply S.A.", "30-11223344-5", "Av. Industrial 500", "011-44332211", CondicionIVA.RESPONSABLE_INSCRIPTO);
        proveedores.add(prov1);

        Producto prod1 = new Producto("P001", "Mouse Inalámbrico", 15000.0, "Hardware", prov1);
        Servicio serv1 = new Servicio("S001", "Instalación de Software", 8000.0, "Soporte Técnico", prov1);
        catalogo.add(prod1);
        catalogo.add(serv1);

        Cliente cli1 = new Cliente("Maria Gomez", "20-87654321-8", "Av. Siempreviva 742", "011-55554444", CondicionIVA.CONSUMIDOR_FINAL, 50000.0, "REGULAR");
        clientes.add(cli1);

        Departamento depSistemas = new Departamento("Sistemas y Soporte", 500000.0);
        departamentos.add(depSistemas);

        Empleado emp1 = new Empleado("Juan Perez", "20-12345678-9", "Calle Falsa 123", "011-11223344", CondicionIVA.RESPONSABLE_INSCRIPTO, 350000.0, "Técnico", LocalDate.of(2023, 3, 15), depSistemas);
        empleados.add(emp1);
        depSistemas.agregarEmpleado(emp1);
        depSistemas.setResponsable(emp1);

        Factura fac1 = new Factura(1, 29, TipoComprobante.FACTURA_C, LocalDate.now(), cli1, emp1, MetodoPago.TRANSFERENCIA);
        fac1.agregarLinea(new LineaFactura(prod1, 2, 0.0));
        fac1.agregarLinea(new LineaFactura(serv1, 1, 1000.0));
        fac1.setEstadoPago(EstadoPago.CANCELADO);
        fac1.autorizarARCA("74453966403949", LocalDate.now().plusDays(10), "https://www.arca.gob.ar/fe/qr/?p=eyJ2ZXJzaW9uIjoxfQ==");

        facturas.add(fac1);
        cli1.agregarFactura(fac1);
    }

    // =========================================================================
    // SUBMENÚ: CATÁLOGO COMERCIAL
    // =========================================================================
    private void menuCatalogo() {
        System.out.println("\n--- SUBMENÚ: CATÁLOGO COMERCIAL ---");
        System.out.println("1. Listar Productos y Servicios");
        System.out.println("2. Cargar Nuevo Producto");
        System.out.println("3. Cargar Nuevo Servicio");
        System.out.print("Opción: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1 -> listarCatalogo();
                case 2 -> cargarProducto();
                case 3 -> cargarServicio();
                default -> System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error en ingreso de datos.");
        }
    }

    private void listarCatalogo() {
        System.out.println("\n=== CATÁLOGO DE ÍTEMS ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay ítems registrados.");
            return;
        }
        for (ItemFacturable item : catalogo) {
            System.out.println("[" + item.getCodigo() + "] " + item.getNombre() + " - $" + item.getPrecio() + " (" + item.getDescripcion() + ")");
        }
    }

    private void cargarProducto() {
        if (proveedores.isEmpty()) {
            System.out.println(" Error: Primero debe haber al menos un Proveedor registrado.");
            return;
        }
        System.out.println("\n-- Carga de Nuevo Producto --");
        System.out.print("Código (ej. P002): ");
        String cod = scanner.nextLine();
        System.out.print("Nombre: ");
        String nom = scanner.nextLine();
        System.out.print("Precio: ");
        double prec = Double.parseDouble(scanner.nextLine());
        System.out.print("Categoría/Tipo (Hardware/Sop. Técnico): ");
        String tipo = scanner.nextLine();

        Proveedor prov = proveedores.get(0); // Selecciona el primer proveedor por defecto
        Producto p = new Producto(cod, nom, prec, tipo, prov);
        catalogo.add(p);
        System.out.println("¡Producto guardado exitosamente!");
    }

    private void cargarServicio() {
        if (proveedores.isEmpty()) {
            System.out.println(" Error: Primero debe haber al menos un Proveedor registrado.");
            return;
        }
        System.out.println("\n-- Carga de Nuevo Servicio --");
        System.out.print("Código (ej. S002): ");
        String cod = scanner.nextLine();
        System.out.print("Nombre: ");
        String nom = scanner.nextLine();
        System.out.print("Precio: ");
        double prec = Double.parseDouble(scanner.nextLine());
        System.out.print("Especialidad/Tipo: ");
        String tipo = scanner.nextLine();

        Proveedor prov = proveedores.get(0);
        Servicio s = new Servicio(cod, nom, prec, tipo, prov);
        catalogo.add(s);
        System.out.println("¡Servicio guardado exitosamente!");
    }

    // =========================================================================
    // SUBMENÚ: GESTIÓN DE PERSONAS
    // =========================================================================
    private void menuPersonas() {
        System.out.println("\n--- SUBMENÚ: GESTIÓN DE PERSONAS ---");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Listar Empleados");
        System.out.println("3. Listar Proveedores");
        System.out.println("4. Cargar Cliente");
        System.out.println("5. Cargar Empleado");
        System.out.println("6. Cargar Proveedor");
        System.out.print("Opción: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1 -> listarClientes();
                case 2 -> listarEmpleados();
                case 3 -> listarProveedores();
                case 4 -> cargarCliente();
                case 5 -> cargarEmpleado();
                case 6 -> cargarProveedor();
                default -> System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error en ingreso de datos.");
        }
    }

    private void listarClientes() {
        System.out.println("\n=== CLIENTES REGISTRADOS ===");
        for (Cliente c : clientes) {
            System.out.println("- " + c.getNombre() + " | DNI/CUIT: " + c.getDni() + " | IVA: " + c.getCondicionIVA());
        }
    }

    private void listarEmpleados() {
        System.out.println("\n=== EMPLEADOS REGISTRADOS ===");
        for (Empleado e : empleados) {
            System.out.println("- " + e.getNombre() + " | Cargo: " + e.getPuesto() + " | Depto: " + (e.getDepartamento() != null ? e.getDepartamento().getNombre() : "Sin Asignar"));
        }
    }

    private void listarProveedores() {
        System.out.println("\n=== PROVEEDORES REGISTRADOS ===");
        for (Proveedor p : proveedores) {
            System.out.println("- " + p.getRazonSocial() + " | CUIT: " + p.getCuit());
        }
    }

    private void cargarCliente() {
        System.out.println("\n-- Carga de Nuevo Cliente --");
        System.out.print("Nombre completo: ");
        String nom = scanner.nextLine();
        System.out.print("DNI o CUIT: ");
        String dni = scanner.nextLine();
        System.out.print("Domicilio: ");
        String dom = scanner.nextLine();
        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        CondicionIVA iva = seleccionarCondicionIVA();
        System.out.print("Límite de Crédito: ");
        double lim = Double.parseDouble(scanner.nextLine());

        Cliente c = new Cliente(nom, dni, dom, tel, iva, lim, "NUEVO");
        clientes.add(c);
        System.out.println(" ¡Cliente registrado exitosamente!");
    }

    private void cargarEmpleado() {
        if (departamentos.isEmpty()) {
            System.out.println(" Error: Cree primero un departamento para asignar al empleado.");
            return;
        }
        System.out.println("\n-- Carga de Nuevo Empleado --");
        System.out.print("Nombre completo: ");
        String nom = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Domicilio: ");
        String dom = scanner.nextLine();
        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        CondicionIVA iva = seleccionarCondicionIVA();
        System.out.print("Salario: ");
        double sal = Double.parseDouble(scanner.nextLine());
        System.out.print("Puesto: ");
        String puesto = scanner.nextLine();

        Departamento dep = departamentos.get(0); // Le asignamos el primer departamento por defecto
        Empleado e = new Empleado(nom, dni, dom, tel, iva, sal, puesto, LocalDate.now(), dep);
        empleados.add(e);
        dep.agregarEmpleado(e);
        System.out.println("¡Empleado registrado exitosamente!");
    }

    private void cargarProveedor() {
        System.out.println("\n-- Carga de Nuevo Proveedor --");
        System.out.print("Razón Social: ");
        String rsoc = scanner.nextLine();
        System.out.print("CUIT: ");
        String cuit = scanner.nextLine();
        System.out.print("Domicilio: ");
        String dom = scanner.nextLine();
        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        CondicionIVA iva = seleccionarCondicionIVA();

        Proveedor p = new Proveedor(rsoc, cuit, dom, tel, iva);
        proveedores.add(p);
        System.out.println("¡Proveedor registrado exitosamente!");
    }

    // =========================================================================
    // SUBMENÚ: DEPARTAMENTOS
    // =========================================================================
    private void menuDepartamentos() {
        System.out.println("\n--- SUBMENÚ: DEPARTAMENTOS ---");
        System.out.println("1. Listar Departamentos");
        System.out.println("2. Crear Departamento");
        System.out.print("Opción: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1 -> {
                    System.out.println("\n=== DEPARTAMENTOS DE LA EMPRESA ===");
                    for (Departamento d : departamentos) {
                        System.out.println("Área: " + d.getNombre() + " | Presupuesto: $" + d.getPresupuesto() + " | Miembros: " + d.getEmpleados().size());
                    }
                }
                case 2 -> {
                    System.out.println("\n-- Crear Departamento --");
                    System.out.print("Nombre del Área: ");
                    String nom = scanner.nextLine();
                    System.out.print("Presupuesto: ");
                    double pres = Double.parseDouble(scanner.nextLine());
                    departamentos.add(new Departamento(nom, pres));
                    System.out.println("¡Departamento creado!");
                }
                default -> System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error en ingreso de datos.");
        }
    }

    // =========================================================================
    // SUBMENÚ: FACTURACIÓN Y VENTAS
    // =========================================================================
    private void menuFacturacion() {
        System.out.println("\n--- SUBMENÚ: FACTURACIÓN Y VENTAS ---");
        System.out.println("1. Emitir Nueva Factura (Venta Interactiva)");
        System.out.println("2. Listar Historial de Comprobantes");
        System.out.println("3. Ver Comprobante Impreso (ARCA)");
        System.out.print("Opción: ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1 -> emitirNuevaFactura();
                case 2 -> {
                    System.out.println("\n=== HISTORIAL DE FACTURAS ===");
                    for (Factura f : facturas) {
                        System.out.println("N°: " + f.getPuntoVenta() + "-" + f.getNumeroComprobante() + " | Cliente: " + f.getCliente().getNombre() + " | Total: $" + f.calcularTotal() + " | CAE: " + f.getCae());
                    }
                }
                case 3 -> {
                    if (facturas.isEmpty()) {
                    System.out.println("No hay facturas emitidas en el sistema.");
                } else {
                    System.out.println("\n=== SELECCIONAR COMPROBANTE A IMPRIMIR ===");
                    for (int i = 0; i < facturas.size(); i++) {
                        Factura f = facturas.get(i);
                        System.out.println((i + 1) + ". N° " + f.getPuntoVenta() + "-" + f.getNumeroComprobante() 
                            + " | Cliente: " + f.getCliente().getNombre() 
                            + " | Total: $" + f.calcularTotal());
                    }
                    System.out.print("Ingrese el número de la factura a ver: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;

                    if (idx >= 0 && idx < facturas.size()) {
                        facturas.get(idx).mostrarDetalle();
                    } else {
                        System.out.println("Opción de factura no válida.");
                    }
    
    }

            }
                default -> System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.out.println("Error procesando la venta: " + e.getMessage());
        }
    }

    private void emitirNuevaFactura() {
    if (clientes.isEmpty() || empleados.isEmpty() || catalogo.isEmpty()) {
        System.out.println(" Error: Debe registrar previamente al menos 1 Cliente, 1 Empleado y 1 Ítem en el Catálogo.");
        return;
    }

    System.out.println("\n=== NUEVA VENTA / EMISIÓN DE FACTURA ===");

    // 1. SELECCIÓN DE CLIENTE
    System.out.println("\n-- Seleccione el Cliente --");
    for (int i = 0; i < clientes.size(); i++) {
        Cliente c = clientes.get(i);
        System.out.println((i + 1) + ". " + c.getNombre() + " (DNI/CUIT: " + c.getDni() + ")");
    }
    System.out.print("Ingrese el número del cliente: ");
    int idxCliente = Integer.parseInt(scanner.nextLine()) - 1;

    if (idxCliente < 0 || idxCliente >= clientes.size()) {
        System.out.println("Cliente no válido. Operación cancelada.");
        return;
    }
    Cliente clienteSeleccionado = clientes.get(idxCliente);

    // 2. SELECCIÓN DE EMPLEADO
    System.out.println("\n-- Seleccione el Empleado (Vendedor) --");
    for (int i = 0; i < empleados.size(); i++) {
        Empleado e = empleados.get(i);
        System.out.println((i + 1) + ". " + e.getNombre() + " (" + e.getPuesto() + ")");
    }
    System.out.print("Ingrese el número del empleado: ");
    int idxEmpleado = Integer.parseInt(scanner.nextLine()) - 1;

    if (idxEmpleado < 0 || idxEmpleado >= empleados.size()) {
        System.out.println("Empleado no válido. Operación cancelada.");
        return;
    }
    Empleado empleadoSeleccionado = empleados.get(idxEmpleado);

    // Definición del tipo de comprobante según condición IVA
    TipoComprobante tipoComp = (clienteSeleccionado.getCondicionIVA() == CondicionIVA.RESPONSABLE_INSCRIPTO) 
            ? TipoComprobante.FACTURA_A 
            : TipoComprobante.FACTURA_C;

    int proxNro = facturas.size() + 101;
    Factura nuevaFactura = new Factura(1, proxNro, tipoComp, LocalDate.now(), clienteSeleccionado, empleadoSeleccionado, MetodoPago.EFECTIVO);

    // 3. CARGA DINÁMICA DE ÍTEMS
    boolean agregarMas = true;
    while (agregarMas) {
        System.out.println("\n-- Catálogo Disponible --");
        for (int i = 0; i < catalogo.size(); i++) {
            ItemFacturable item = catalogo.get(i);
            System.out.println((i + 1) + ". [" + item.getCodigo() + "] " + item.getNombre() + " - $" + item.getPrecio() + " (" + item.getDescripcion() + ")");
        }
        System.out.print("Seleccione el producto/servicio a vender: ");
        int idxItem = Integer.parseInt(scanner.nextLine()) - 1;

        if (idxItem >= 0 && idxItem < catalogo.size()) {
            ItemFacturable itemSeleccionado = catalogo.get(idxItem);
            System.out.print("Ingrese la cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            nuevaFactura.agregarLinea(new LineaFactura(itemSeleccionado, cantidad, 0.0));
            System.out.println("Ítem agregado a la factura.");
        } else {
            System.out.println("Opción de ítem no válida.");
        }

        System.out.print("¿Desea agregar otro producto/servicio a esta factura? (s/n): ");
        String resp = scanner.nextLine().trim();
        if (!resp.equalsIgnoreCase("s")) {
            agregarMas = false;
        }
    }

    // 4. AUTORIZACIÓN Y GUARDADO
    nuevaFactura.setEstadoPago(EstadoPago.CANCELADO);
    String caeSimulado = String.valueOf((long) (Math.random() * 100000000000000L));
    nuevaFactura.autorizarARCA(caeSimulado, LocalDate.now().plusDays(10), "https://www.arca.gob.ar/fe/qr/?p=eyJ2ZXJzaW9uIjoxfQ==");

    facturas.add(nuevaFactura);
    clienteSeleccionado.agregarFactura(nuevaFactura);

    System.out.println("\n ¡VENTA REGISTRADA CON ÉXITO Y AUTORIZADA POR ARCA!");
    nuevaFactura.mostrarDetalle();
}

    // =========================================================================
    // MÉTODOS AUXILIARES DE SELECCIÓN
    // =========================================================================
    private CondicionIVA seleccionarCondicionIVA() {
        System.out.println("Seleccione Condición IVA:");
        System.out.println("1. CONSUMIDOR_FINAL");
        System.out.println("2. RESPONSABLE_INSCRIPTO");
        System.out.println("3. MONOTRIBUTO");
        System.out.print("Opción (por defecto 1): ");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            return switch (op) {
                case 2 -> CondicionIVA.RESPONSABLE_INSCRIPTO;
                case 3 -> CondicionIVA.MONOTRIBUTO;
                default -> CondicionIVA.CONSUMIDOR_FINAL;
            };
        } catch (Exception e) {
            return CondicionIVA.CONSUMIDOR_FINAL;
        }
    }
}