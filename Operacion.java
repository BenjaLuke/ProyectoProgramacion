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
    public void setDatos(String datos) {
        // Lógica para configurar los datos de la operación
    }

    @Override
    public String getDatos() {
        return "ID Operación: " + idOperacion;
    }
}

