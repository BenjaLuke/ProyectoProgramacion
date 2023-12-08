// 21GIIN - Proyectos de Programación - Clase Ruta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Ruta 
public class Ruta implements GestorDatos, Trayecto{
    // Atributos clase Ruta
    private int idRuta;
    private String tipoRuta;
    private String puertoOrigen;
    private String puertoDestino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private int costeRuta;

    public Ruta(int idRuta, String tipoRuta, String puertoOrigen, String puertoDestino, Date fechaSalida,
            Date fechaLlegada, int costeRuta) {
        this.idRuta = idRuta;
        this.tipoRuta = tipoRuta;
        this.puertoOrigen = puertoOrigen;
        this.puertoDestino = puertoDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.costeRuta = costeRuta;
    }

    public void agregarTrayecto() {
        // Lógica para agregar un trayecto
    }
}