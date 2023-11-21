// 21GIIN - Proyectos de Programación - Clase Operacion
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase operación
public class Operacion {
    // Atributos
    private String tipo; // Carga o descarga
    private int cantidad; // Cantidad de contenedores
    private int id; // Identificador único de la operación
    private static int idActual = 0; // Identificador actual para rastrear el último id asignado
    private Ruta ruta; // Ruta a la que pertenece la operación. Clase Ruta
    private Empaquetado empaquetado; // Empaquetado al que pertenece la operación. Clase Empaquetado
    private Facturacion facturacion; // Facturación a la que pertenece la operación. Clase Facturación

    // Constructor de la clase Operación
    public Operacion(String tipo, int cantidad, Ruta ruta, Empaquetado empaquetado, Facturacion facturacion) {
        this.tipo = tipo; // Asignamos el tipo 
        this.cantidad = cantidad; // Asignamos la cantidad
        this.id = idActual; // Asignamos el id actual y aumentamos el id actual en 1
        idActual++;
        this.ruta = ruta; // Asignamos la ruta
        this.empaquetado = empaquetado; // Asignamos el empaquetado
        this.facturacion = facturacion; // Asignamos la facturación
    }

    // Getters 
    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getId() {
        return id;
    }

    // Setters 
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Métodos para operaciones relacionadas con la clase Operación
    public void crearOperacion() {
        // Método para crear una operación
    }
    public void eliminarOperacion() {
        // Método para eliminar una operación
    }
    public void modificarOperacion() {
        // Método para modificar una operación
    }

    // Método toString
    @Override // Sobreescribimos el método toString para mostrar los datos de la operación
    public String toString() {
        return "Operacion{" +
                "tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", id=" + id +
                '}';
    }
}

