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

    // Implementación de los métodos de la interfaz GestorDatos
    @Override
    public void setDatos(String datos) {
        // Lógica para configurar los datos de la ruta a partir de una cadena
    }

    @Override
    public String getDatos() {
        return "ID Ruta: " + idRuta + ", Tipo Ruta: " + tipoRuta + ", Puerto Origen: " + puertoOrigen
                + ", Puerto Destino: " + puertoDestino;
    }

    // Implementación de los métodos de la interfaz Trayecto
    @Override
    public void setTrayecto(String trayecto) {
        // Lógica para configurar los datos del trayecto a partir de una cadena
    }
    public String getTrayecto() {
        return "Fecha Salida: " + fechaSalida + ", Fecha Llegada: " + fechaLlegada + ", Coste Ruta: " + costeRuta;
    }
}