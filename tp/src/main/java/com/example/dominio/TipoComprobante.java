package com.example.dominio;


/**
 * Tipos de comprobantes fiscales con sus códigos oficiales según la tabla de AFIP/ARCA.
 * Los códigos numéricos (1, 6, 11) corresponden a los identificadores requeridos
 * para la facturación electrónica y la persistencia en base de datos (nuestro Repositorio en JSON).
 */
public enum TipoComprobante {
    FACTURA_A(1, "Factura A"),
    FACTURA_B(6, "Factura B"),
    FACTURA_C(11, "Factura C");

    private final int codigo;
    private final String descripcion;

    TipoComprobante(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

/*
 * Define el tipo formal del comprobante fiscal ARCA con su respectivo código 
 * numérico AFIP/ARCA (Factura A = 1, B = 6, C = 11).
 */