// 21GIIN - Proyectos de Programación - Clase Ruta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Ruta 
public class Ruta {
    // Atributos clase Ruta
    private String tipoRuta; // Tipo de ruta: Marítima, Terrestre, Aérea
    private String puertoOrigen; // Puerto de origen
    private String puertoDestino; // Puerto de destino
    private int distancia; // Distancia en km
    private int duracion; // Duración en horas
    private int coste; // Coste en €
    private int id; // Identificador de la ruta

    // Constructor
    public Ruta(String tipoRuta, String puertoOrigen,String puertoDestino, int distancia, int duracion, int coste, int id) {
        this.tipoRuta = tipoRuta;
        this.puertoOrigen = puertoOrigen;
        this.puertoDestino = puertoDestino;
        this.distancia = distancia;
        this.duracion = duracion;
        this.coste = coste;
        this.id = id;
    }

    // Getters y Setters
    public String getTipoRuta() {
        return tipoRuta;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCoste() {
        return coste;
    }

    public int getId() {
        return id;
    }
    
    public void setTipoRuta(String tipoRuta) {
        this.tipoRuta = tipoRuta;
    }
    // Método para calcular el tiempo estimado de llegada en base a una velocidad promedio
    public double calcularTiempoEstimadoLlegada(double velocidadPromedio) {
        return distancia / velocidadPromedio;
    }

    // Método para calcular el costo total estimado en base a un costo por kilómetro
    public int calcularCostoTotalEstimado(int costoPorKilometro) {
        return distancia * costoPorKilometro;
    }
}