// 21GIIN - Proyectos de Programación - Clase Operacion
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase operación
public class Operacion implements GestorDatos{
    // Atributos
    private int idOperacion;

    // Constructor
    public Operacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    // Implementación de los métodos de la interfaz GestorDatos
        @Override
    public void crear() {
        // Lógica para crear una operación
    }

    @Override
    public void modificar() {
        // Lógica para modificar una operación
    }

    @Override
    public void eliminar() {
        // Lógica para eliminar una operación
    }

    @Override
    public String leer() {
        // Lógica para leer (obtener datos) de una operación
        return null; // Retorna los datos de una operación
    }
}

