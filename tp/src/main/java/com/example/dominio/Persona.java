package com.example.dominio;

// Importaciones para manejar el polimorfismo durante la serialización y deserialización de objetos JSON
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Ese fragmento corresponde a anotaciones de Jackson que le enseñan a la biblioteca cómo
// manejar el polimorfismo al momento de guardar y leer objetos en formato JSON.
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Cliente.class, name = "cliente"),
    @JsonSubTypes.Type(value = Empleado.class, name = "empleado"),
    @JsonSubTypes.Type(value = Proveedor.class, name = "proveedor")
})

/**
 * Clase abstracta base para toda persona vinculada a la empresa.
 * No se intancia directamente: siempre a través de Cliente, Empleado o Proveedor 
 */

// Clase abstracta 'abstract'
public abstract class Persona {
    
    private String nombre;
    private String domicilio;
    protected String dni;
    private String telefono;
    private CondicionIVA CondicionIVA;

    // Constructor vacío requerido por Jackson
    protected Persona() {
    }

    public Persona(String nombre, String domicilio, String dni, String telefono, CondicionIVA CondicionIVA){
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.telefono = telefono;
        this.CondicionIVA = CondicionIVA;
    }


    // Getters y Setters
     // --- ---
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // --- ---
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
     // --- ---
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
     // --- ---
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
     // --- ---
    public CondicionIVA getCondicionIVA() {
        return CondicionIVA;
    }

    // Cambiamos la condición fiscal del cliente existente.
    public void setCondicionIVA(CondicionIVA CondiciónIVA){
        this.CondicionIVA = CondiciónIVA;
    }
    // --- ---
    
    // Método abstracto: cada clase lo implementará a su manera
    public abstract String getDetalleRol();
}
