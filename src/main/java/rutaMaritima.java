// 21GIIN - Proyectos de Programación - Clase Ruta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;



// Definimos la clase ruta Marítima

public class rutaMaritima extends Ruta implements GestorDatos{
    // Atributos y métodos específicos de RutaMaritima

    private int profundidadAgua;
    private int condicionesMar;
    private String puertosIntermedios;



    // Constructor
    public rutaMaritima(int idRuta, int tipoRuta, String puertoOrigen, String puertoDestino, 
            Date fechaSalida, Date fechaLlegada, int costeRuta,
            int profundidadAgua, int condicionesMar, String puertosIntermedios) {
        super(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta); // Llamamos al constructor de la clase padre
        this.profundidadAgua = profundidadAgua; // Asignamos la profundidad del agua
        this.condicionesMar = condicionesMar; // Asignamos las condiciones del mar
        this.puertosIntermedios = puertosIntermedios; // Asignamos los puertos intermedios

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
        String sql = "INSERT INTO RutaMaritima (idRuta, profundidadAgua, condicionesMar, puertosIntermedios) VALUES (?, ?, ?, ?)"; // Definimos la consulta SQL para crear una ruta marítima

        try(PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)){

            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
            pstmt.setInt(2, this.profundidadAgua); // Asignamos la profundidad del agua
            pstmt.setInt(3, this.condicionesMar); // Asignamos las condiciones del mar
            pstmt.setString(4, this.puertosIntermedios); // Asignamos los puertos intermedios
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta marítima creada con éxito: " + ruta.getIdRuta());
        } catch (SQLException e) {
            e.printStackTrace();
        }
   
    }

    @Override
    public void modificar() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes
                                 
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
         
        String sql = "UPDATE RutaMaritima SET profundidadAgua = ?, condicionesMar = ?, puertosIntermedios = ? WHERE idRuta = ?"; // Definimos la consulta SQL para modificar una ruta marítima

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para modificar una ruta marítima
            pstmt.setInt(1, this.profundidadAgua); // Asignamos la profundidad del agua
            pstmt.setInt(2, this.condicionesMar); // Asignamos las condiciones del mar
            pstmt.setString(3, this.puertosIntermedios); // Asignamos los puertos intermedios
            pstmt.setInt(4, ruta.getIdRuta()); // Asignamos el id de la ruta
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta marítima modificada con éxito: " + ruta.getIdRuta());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes

        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        String sql = "DELETE FROM RutaMaritima WHERE idRuta = ?"; // Definimos la consulta SQL para eliminar una ruta marítima

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para eliminar una ruta marítima
            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Ruta marítima eliminada con éxito: " + ruta.getIdRuta());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String leer() {
        Ruta ruta = crearRutaComun(super.getIdRuta(), super.getTipoRuta(), super.getPuertoOrigen(), super.getPuertoDestino(), 
                                super.getFechaSalida(), super.getFechaLlegada(), super.getCosteRuta()); // Creamos una nueva instancia de la clase Ruta con los datos comunes

        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        String sql = "SELECT * FROM RutaMaritima " + // Definimos la consulta SQL para leer una ruta
                "INNER JOIN Ruta ON Ruta.idRuta = RutaMaritima.idRuta";

        StringBuilder rutasMaritimas = new StringBuilder(); // Creamos un StringBuilder para almacenar las rutas marítimas


        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para leer una ruta marítima
            pstmt.setInt(1, ruta.getIdRuta()); // Asignamos el id de la ruta
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta

            // Recorremos el ResultSet
            while (rs.next()) {
                rutasMaritimas.append("Id de la ruta: ").append(rs.getInt("idRuta")).append("\n");
                rutasMaritimas.append("Profundidad del agua: ").append(rs.getInt("profundidadAgua")).append("\n");
                rutasMaritimas.append("Condiciones del mar: ").append(rs.getInt("condicionesMar")).append("\n");
                rutasMaritimas.append("Puertos intermedios: ").append(rs.getString("puertosIntermedios")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Otros métodos específicos de la clase RutaMaritima
}
   

