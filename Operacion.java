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
        // Lógica para crear un usuario
    }

    @Override
    public void modificar() {
        // Lógica para modificar un usuario
    }

    @Override
    public void eliminar() {
        // Lógica para eliminar un usuario
    }

    @Override
    public String leer() {
        // Lógica para leer (obtener datos) de un usuario
        return null; // Retorna los datos del usuario
    }
}

