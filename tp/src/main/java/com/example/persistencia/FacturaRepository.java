package com.example.persistencia;

import com.example.dominio.Factura;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository {

    private final ObjectMapper mapper;
    private final File archivo;

    public FacturaRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.archivo = new File("datos/facturas.json");
    }

    public void guardarTodas(List<Factura> facturas) throws IOException {
        if (archivo.getParentFile() != null && !archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }
        mapper.writerFor(new TypeReference<List<Factura>>() {})
              .withDefaultPrettyPrinter()
              .writeValue(archivo, facturas);
    }

    public List<Factura> obtenerTodas() throws IOException {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(archivo, new TypeReference<List<Factura>>() {});
    }
}