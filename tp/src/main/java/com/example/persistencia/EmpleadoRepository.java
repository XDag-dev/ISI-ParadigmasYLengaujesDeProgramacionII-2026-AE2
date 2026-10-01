package com.example.persistencia;

import com.example.dominio.Empleado;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {

    private final ObjectMapper mapper;
    private final File archivo;

    public EmpleadoRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.registerModule(new JavaTimeModule()); // Requerido por LocalDate fechaIngreso
        this.archivo = new File("datos/empleados.json");
    }

    public void guardarTodos(List<Empleado> empleados) throws IOException {
        if (archivo.getParentFile() != null && !archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }
        mapper.writerFor(new TypeReference<List<Empleado>>() {})
              .withDefaultPrettyPrinter()
              .writeValue(archivo, empleados);
    }

    public List<Empleado> obtenerTodos() throws IOException {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        return mapper.readValue(archivo, new TypeReference<List<Empleado>>() {});
    }
}