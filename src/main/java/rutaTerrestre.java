// 21GIIN - Proyectos de Programación - Clase Ruta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;




// Definimos la clase ruta Terrestre
public class rutaTerrestre extends Ruta implements GestorDatos{
    // Atributos clase RutaTerrestre
    private int  tipoTerreno;
    private int numeroPeajes;
    //private String estadoCarreteras;
    private int condicionesAtmosfericas; // Condiciones atmosféricas


    // Constructor
    public rutaTerrestre(int idRuta, int tipoRuta, String puertoOrigen, String puertoDestino, 
            Date fechaSalida, Date fechaLlegada, int costeRuta,
            int tipoTerreno, int numeroPeajes, int condicionesAtmosfericas) {
        super(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta); // Llamamos al constructor de la clase padre
        this.tipoTerreno = tipoTerreno; // Asignamos el tipo de terreno
        this.numeroPeajes = numeroPeajes; // Asignamos el número de peajes
        this.condicionesAtmosfericas = condicionesAtmosfericas; // Asignamos las condiciones atmosféricas
        //this.estadoCarreteras = estadoCarreteras; // Asignamos el estado de las carreteras

    }

    // método para crear ruta con datos comunes
    private static Ruta crearRutaComun(int idRuta, int tipoRuta, String puertoOrigen, String puertoDestino, Date fechaSalida, Date fechaLlegada,
            int costeRuta) {
        return new Ruta(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta); // Creamos una nueva instancia de la clase Ruta con los datos comunes
    }


    @Override
    public void crear() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes

        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
        String sql = "INSERT INTO RutaTerrestre (idRuta, tipoTerreno, condicionesClimaticas, numeroPeajes) VALUES (?, ?, ?, ?)"; // Definimos la consulta SQL para crear una ruta terrestre
        
        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para crear una ruta terrestre
            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
            pstmt.setInt(2, this.tipoTerreno); // Asignamos el tipo de terreno
            pstmt.setInt(3, this.numeroPeajes); // Asignamos el número de peajes
            pstmt.setInt(4, this.condicionesAtmosfericas); // Asignamos el estado de las carreteras
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta terrestre creada con éxito: " + ruta.getIdRuta());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes
        
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        String sql = "UPDATE RutaTerrestre SET tipoTerreno = ?, condicionesClimaticas = ?, numeroPeajes = ? WHERE idRuta = ?"; // Definimos la consulta SQL para modificar una ruta terrestre

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para modificar una ruta terrestre
            pstmt.setInt(1, this.tipoTerreno); // Asignamos el tipo de terreno
            pstmt.setInt(2, this.numeroPeajes); // Asignamos el número de peajes
            pstmt.setInt(3, this.condicionesAtmosfericas); // Asignamos el estado de las carreteras
            pstmt.setInt(4, ruta.getIdRuta()); // Asignamos el id de la ruta
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta terrestre modificada con éxito: " + ruta.getIdRuta());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void eliminar() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes

        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        String sql = "DELETE FROM RutaTerrestre WHERE idRuta = ?"; // Definimos la consulta SQL para eliminar una ruta terrestre

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para eliminar una ruta terrestre
            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta terrestre eliminada con éxito: " + ruta.getIdRuta());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public String leer() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
        String sql = "SELECT * FROM RutaTerrestre " + // Definimos la consulta SQL para leer una ruta
                "INNER JOIN Ruta ON Ruta.idRuta = RutaTerrestre.idRuta";

        StringBuilder rutasTerrestre = new StringBuilder(); // Creamos un StringBuilder para almacenar las rutas terrestres

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para leer una ruta
            pstmt.setInt(1, super.getIdRuta()); // Asignamos el id de la ruta
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta

            while (rs.next()) { // Recorremos el ResultSet
                rutasTerrestre.append("idRuta: ").append(rs.getInt("idRuta")).append("\n"); // Añadimos el id de la ruta al StringBuilder
                rutasTerrestre.append("tipoTerreno: ").append(rs.getInt("tipoTerreno")).append("\n"); // Añadimos el tipo de terreno al StringBuilder
                rutasTerrestre.append("condicionesClimaticas: ").append(rs.getInt("condicionesClimaticas")).append("\n"); // Añadimos las condiciones climáticas al StringBuilder
                rutasTerrestre.append("numeroPeajes: ").append(rs.getInt("numeroPeajes")).append("\n"); // Añadimos el número de peajes al StringBuilder
            }
        } catch (SQLException e) {
            System.out.println("Error al leer la ruta terrestre " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
        return rutasTerrestre.toString(); // Retornamos las rutas terrestres

    }

    
}

