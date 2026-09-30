package com.example.excepciones;

public class FacturaSinItemsExcepcion extends Exception {
    public FacturaSinItemsExcepcion(String mensaje) {
        super(mensaje); // Llama al constructor de Exception
    }
}