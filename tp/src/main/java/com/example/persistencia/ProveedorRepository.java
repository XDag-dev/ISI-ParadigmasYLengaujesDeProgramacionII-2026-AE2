package com.example.persistencia;

import com.example.dominio.Proveedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRepository {

    private final ObjectMapper mapper;
    private final File archivo;

    public ProveedorRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.archivo = new File("datos/proveedores.json");
    }

    public void guardarTodos(List<Proveedor> proveedores) throws IOException {
        if (archivo.getParentFile() != null && !archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }
        mapper.writerFor(new TypeReference<List<Proveedor>>() {})
              .withDefaultPrettyPrinter()
              .writeValue(archivo, proveedores);
    }

    public List<Proveedor> obtenerTodos() throws IOException {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(archivo, new TypeReference<List<Proveedor>>() {});
    }
}