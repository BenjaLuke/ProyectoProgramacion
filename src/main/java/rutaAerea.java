// 21GIIN - Proyectos de Programación - Clase Ruta Aérea
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;




// Definimos la clase ruta Aérea

public class rutaAerea extends Ruta {
    // Atributos y métodos específicos de RutaAerea
    private int altitudVuelo; // Altitud de vuelo
    private int condicionesAtmosfericas; // Condiciones atmosféricas
    private List<String> rutasVuelo; // Rutas de vuelo
    
    

    // Constructor
    public rutaAerea(int idRuta, int tipoRuta, String puertoOrigen, String puertoDestino, 
            Date fechaSalida, Date fechaLlegada, int costeRuta,
            int altitudVuelo, int condicionesAtmosfericas, List <String> rutasVuelo) {
        super(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta); // Llamamos al constructor de la clase padre
        this.altitudVuelo = altitudVuelo; // Asignamos la altitud de vuelo
        this.condicionesAtmosfericas = condicionesAtmosfericas; // Asignamos las condiciones atmosféricas
        this.rutasVuelo = rutasVuelo; // Asignamos las rutas de vuelo
        
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
        String sql = "INSERT INTO RutaAerea (idRuta, altitudVuelo, condicionesAtmosfericas, rutasVuelo) VALUES (?, ?, ?, ?)"; // Definimos la consulta SQL para crear una ruta aérea
       
        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para crear una ruta aérea
            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
           // pstmt.setInt(1, this.getIdRuta()); // Asignamos el id de la ruta
            pstmt.setInt(2, this.altitudVuelo); // Asignamos la altitud de vuelo
            pstmt.setInt(3, this.condicionesAtmosfericas); // Asignamos las condiciones atmosféricas
            pstmt.setString(4, this.rutasVuelo.toString()); // Asignamos las rutas de vuelo
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta aérea creada con éxito: " + ruta.getIdRuta());
            //System.out.println("Ruta aérea creada con éxito: " + this.getIdRuta());
        } catch (SQLException e) {
            System.out.println("Error al crear la ruta aérea" + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }


    }

    @Override
    public void modificar() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                 super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes
        
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        String sql = "UPDATE RutaAerea SET altitudVuelo = ?, condicionesAtmosfericas = ?, rutasVuelo = ? WHERE idRuta = ?"; // Definimos la consulta SQL para modificar una ruta aérea

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para modificar una ruta aérea
            pstmt.setInt(1, this.altitudVuelo); // Asignamos la altitud de vuelo
            pstmt.setInt(2, this.condicionesAtmosfericas); // Asignamos las condiciones atmosféricas
            pstmt.setString(3, this.rutasVuelo.toString()); // Asignamos las rutas de vuelo
            pstmt.setInt(4, ruta.getIdRuta()); // Asignamos el id de la ruta
            //pstmt.setInt(4, this.getIdRuta()); // Asignamos el id de la ruta
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta aérea modificada con éxito: " + ruta.getIdRuta());
            //System.out.println("Ruta aérea modificada con éxito: " + this.getIdRuta());
        } catch (SQLException e) {
            System.out.println("Error al modificar la ruta aérea " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public void eliminar() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                 super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes
        
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        String sql = "DELETE FROM RutaAerea WHERE idRuta = ?"; // Definimos la consulta SQL para eliminar una ruta aérea

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para eliminar una ruta aérea
            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
            //pstmt.setInt(1, this.getIdRuta()); // Asignamos el id de la ruta
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta aérea eliminada con éxito: " + ruta.getIdRuta());
            //System.out.println("Ruta aérea eliminada con éxito: " + this.getIdRuta());
        } catch (SQLException e) {
            System.out.println("Error al eliminar la ruta aérea " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public String leer() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
        String sql = "SELECT * FROM RutaAerea " + // Definimos la consulta SQL para leer una ruta
                "INNER JOIN Ruta ON Ruta.idRuta = RutaAerea.idRuta"; 
        StringBuilder rutasAereas = new StringBuilder(); // Creamos un StringBuilder para almacenar las rutas aéreas

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para leer una ruta aérea
            pstmt.setInt(1, super.getIdRuta()); // Asignamos el id de la ruta
            //pstmt.setInt(1, this.getIdRuta()); // Asignamos el id de la ruta
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta

            while (rs.next()) { // Recorremos el ResultSet
                rutasAereas.append("Id de la ruta: ").append(rs.getInt("idRuta")).append("\n"); // Añadimos el id de la ruta
                rutasAereas.append("Altitud de vuelo: ").append(rs.getInt("altitudVuelo")).append("\n"); // Añadimos la altitud de vuelo
                rutasAereas.append("Condiciones atmosféricas: ").append(rs.getInt("condicionesAtmosfericas")).append("\n"); // Añadimos las condiciones atmosféricas
                rutasAereas.append("Rutas de vuelo: ").append(rs.getString("rutasVuelo")).append("\n"); // Añadimos las rutas de vuelo
            }
        } catch (SQLException e) {
            System.out.println("Error al leer la ruta aérea " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }

        return rutasAereas.toString(); // Retornamos las rutas aéreas
    
    }

    
}
