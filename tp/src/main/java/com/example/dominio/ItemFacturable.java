package com.example.dominio;

//------------------------------------------------------------------------------------------
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
    @JsonSubTypes.Type(value = Producto.class, name = "producto"),
    @JsonSubTypes.Type(value = Servicio.class, name = "servicio")
})
//------------------------------------------------------------------------------------------

// Encapsula las propiedades de cualquier ítem (producto o servicio) que pueda ser facturado.
public abstract class ItemFacturable {

    private String codigo;
    private String nombre;
    private double precio;
    private String tipo;
    private Proveedor proveedor;

    //------------------------------------------------------------------------------------------
    // Constructor vacío requerido por Jackson
    protected ItemFacturable() {
    }
    //------------------------------------------------------------------------------------------

    

    public ItemFacturable(String codigo, String nombre, double precio, String tipo, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.proveedor = proveedor;
    }

    // Getters y Setters

    // --- ---

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // --- ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- ---

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // --- ---

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // --- ---

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    // --- ---

    // Método abstracto.
    public abstract String getDescripcion();{
    }
}


