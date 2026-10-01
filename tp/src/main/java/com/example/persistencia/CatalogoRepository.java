package com.example.persistencia;

import com.example.dominio.ItemFacturable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoRepository {

    private final ObjectMapper mapper;
    private final File archivo;

    public CatalogoRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.archivo = new File("datos/catalogo.json");
    }

    public void guardarTodos(List<ItemFacturable> catalogo) throws IOException {
        if (archivo.getParentFile() != null && !archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }
        mapper.writerFor(new TypeReference<List<ItemFacturable>>() {})
              .withDefaultPrettyPrinter()
              .writeValue(archivo, catalogo);
    }

    public List<ItemFacturable> obtenerTodos() throws IOException {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(archivo, new TypeReference<List<ItemFacturable>>() {});
    }
}

// (Soporta Producto y Servicio por anotaciones @JsonTypeInfo)