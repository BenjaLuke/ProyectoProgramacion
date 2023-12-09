// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Empaquetado
public class Empaquetado extends Operacion implements GestorDatos{
    // Atributos de la clase Empaquetado
    private int numeroContenedores;
    private double peso;
    private double tamano;
    // Otros atributos relacionados con el empaquetado

    // Constructor
    public Empaquetado(int numeroContenedores, double peso, double tamano) {
        this.numeroContenedores = numeroContenedores;
        this.peso = peso;
        this.tamano = tamano;
        
    }

    // Implementación de los métodos de la interfaz GestorDatos
    @Override
    public void crear() {
        // Lógica para crear un empaquetado
    }

    @Override
    public void modificar() {
        // Lógica para modificar un empaquetado
    }

    @Override
    public void eliminar() {
        // Lógica para eliminar un empaquetado
    }

    @Override
    public String leer() {
        // Lógica para leer (obtener datos) de un empaquetado
        return null; // Retorna los datos del empaquetado
    }

    // Método para calcular la densidad del empaquetado
    public double calcularDensidad() {
        return peso / tamano;
    }
}


