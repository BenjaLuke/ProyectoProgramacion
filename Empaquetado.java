// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Empaquetado
public class Empaquetado implements GestorDatos{
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
    public void setDatos(String datos) {
        // Lógica para configurar los datos de empaquetado a partir de una cadena
    }

    @Override
    public String getDatos() {
        return "Número de contenedores: " + numeroContenedores + ", Peso: " + peso + ", Tamaño: " + tamano;
    }

    // Método para calcular la densidad del empaquetado
    public double calcularDensidad() {
        return peso / tamano;
    }
}


