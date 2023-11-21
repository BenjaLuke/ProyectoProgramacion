// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Empaquetado
public class Empaquetado {
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

    // Getters y Setters para los atributos de la clase Empaquetado
    
    public int getNumeroContenedores() {
        return numeroContenedores;
    }

    public void setNumeroContenedores(int numeroContenedores) {
        this.numeroContenedores = numeroContenedores;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamano() {
        return tamano;
    }

    public void setTamano(double tamano) {
        this.tamano = tamano;
    }

    // Método para calcular la densidad del empaquetado
    public double calcularDensidad() {
        return peso / tamano;
    }
}


