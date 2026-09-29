package com.example.dominio;


public enum CondicionIVA {
    RESPONSABLE_INSCRIPTO,
    MONOTRIBUTO,
    CONSUMIDOR_FINAL,
    EXENTO,
}

/*
* Se toma como ejemplo la forma en la que trabaja ARCA, en Argentina.

* Basicamente, esta clase modela las situaciones fiscales ante ARCA en Argentina.
* Permite validar el tipo de factura a emitir entre emisor y cliente.
*/