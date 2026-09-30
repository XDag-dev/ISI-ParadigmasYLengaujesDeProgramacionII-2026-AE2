package com.example.excepciones;

public class FacturaSinItemsException extends Exception {
    public FacturaSinItemsException(String mensaje) {
        super(mensaje); // Llama al constructor de Exception
    }
}