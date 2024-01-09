// 21GIIN - Proyectos de Programación - Clase Ruta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


// Definimos la clase Ruta 
public class Ruta implements GestorDatos{

    // Atributos clase Ruta
    private int idRuta; 
    private int tipoRuta;
    private String puertoOrigen;
    private String puertoDestino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private int costeRuta;

    // Atributos clase Ruta
    // protected int idRuta; 
    // protected String tipoRuta;
    // protected String puertoOrigen;
    // protected String puertoDestino;
    // protected Date fechaSalida;
    // protected Date fechaLlegada;
    // protected int costeRuta;



// Constructor
    public Ruta (int idRuta, int tipoRuta, String puertoOrigen, String puertoDestino, Date fechaSalida,
            Date fechaLlegada, int costeRuta) {
        this.idRuta = idRuta;
        this.tipoRuta = tipoRuta;
        this.puertoOrigen = puertoOrigen;
        this.puertoDestino = puertoDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.costeRuta = costeRuta;
    }

    //public abstract void crear(); // Método abstracto para crear una ruta

    @Override
    public void crear() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para crear una ruta
        String sql = "INSERT INTO Ruta (idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para crear una ruta
            pstmt.setInt(1, this.idRuta); // Asignamos el id de la ruta
            pstmt.setInt(2, this.tipoRuta); // Asignamos el tipo de ruta
            pstmt.setString(3, this.puertoOrigen); // Asignamos el puerto de origen
            pstmt.setString(4, this.puertoDestino); // Asignamos el puerto de destino
            //Formateamos las fechas al formato de cadena que SQLite admite (yyyy-MM-dd)
            // SimpleDateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd");
            // String fechaSalidaBD = formatoBD.format(this.fechaSalida); // Formateamos la fecha de salida
            // String fechaLlegadaBD = formatoBD.format(this.fechaLlegada); // Formateamos la fecha de llegada
            // pstmt.setString(5, fechaSalidaBD); // Asignamos la fecha de salida
            // pstmt.setString(6, fechaLlegadaBD); // Asignamos la fecha de llegada
            pstmt.setDate(5, this.fechaSalida); // Asignamos la fecha de salida
            pstmt.setDate(6, this.fechaLlegada); // Asignamos la fecha de llegada
            pstmt.setInt(7, this.costeRuta); // Asignamos el coste de la ruta
            
            // Ejecutamos la consulta
            int affectedRows = pstmt.executeUpdate(); 
            if (affectedRows == 0) { // Si no se ha creado la ruta lanzamos una excepción 
                throw new SQLException("No se ha podido crear la ruta");
            } 
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) { // Obtenemos el id de la ruta creada
                if (generatedKeys.next()) { // Si se ha creado la ruta mostramos el id de la ruta
                    this.idRuta = generatedKeys.getInt(1);
                     
                }
                else { // Si no se ha creado la ruta lanzamos una excepción 
                    throw new SQLException("No se ha podido obtener el ID de la ruta"); 
                }
            }

            System.out.println("Ruta creada con éxito: " + this.idRuta);
        } catch (SQLException e) {
            System.out.println("Error al crear la ruta " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }

        

    }


    @Override
    public void modificar() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para modificar una ruta
        String sql = "UPDATE Ruta SET tipoRuta = ?, puertoOrigen = ?, puertoDestino = ?, fechaSalida = ?, fechaLlegada = ?, costeRuta = ? WHERE idRuta = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para modificar una ruta
            pstmt.setInt(1, this.tipoRuta); // Asignamos el tipo de ruta
            pstmt.setString(2, this.puertoOrigen); // Asignamos el puerto de origen
            pstmt.setString(3, this.puertoDestino); // Asignamos el puerto de destino
            //Formateamos las fechas al formato de cadena que SQLite admite (yyyy-MM-dd)
            // SimpleDateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd");
            // String fechaSalidaBD = formatoBD.format(this.fechaSalida); // Formateamos la fecha de salida
            // String fechaLlegadaBD = formatoBD.format(this.fechaLlegada); // Formateamos la fecha de llegada
            // pstmt.setString(4, fechaSalidaBD); // Asignamos la fecha de salida
            // pstmt.setString(5, fechaLlegadaBD); // Asignamos la fecha de llegada
            pstmt.setDate(4, this.fechaSalida); // Asignamos la fecha de salida
            pstmt.setDate(5, this.fechaLlegada); // Asignamos la fecha de llegada
            pstmt.setInt(6, this.costeRuta); // Asignamos el coste de la ruta
            pstmt.setInt(7, this.idRuta); // Asignamos el id de la ruta

            // Ejecutamos la consulta
            int affectedRows = pstmt.executeUpdate(); 
            if (affectedRows == 0) { // Si no se ha modificado la ruta lanzamos una excepción 
                throw new SQLException("No se ha podido modificar la ruta");
            } 

            System.out.println("Ruta modificada con éxito: " + this.idRuta);
        } catch (SQLException e) {
            System.out.println("Error al modificar la ruta " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public void eliminar() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para eliminar una ruta
        String sql = "DELETE FROM Ruta WHERE idRuta = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para eliminar una ruta
            pstmt.setInt(1, this.idRuta); // Asignamos el id de la ruta

            // Ejecutamos la consulta
            int affectedRows = pstmt.executeUpdate(); 
            if (affectedRows == 0) { // Si no se ha eliminado la ruta lanzamos una excepción 
                throw new SQLException("No se ha podido eliminar la ruta");
            } 

            System.out.println("Ruta eliminada con éxito: " + this.idRuta);
        } catch (SQLException e) {
            System.out.println("Error al eliminar la ruta " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public String leer() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
        String sql = "SELECT * FROM Ruta "+// Definimos la consulta SQL para leer una ruta
                                "LEFT JOIN RutaAerea ON Ruta.idRuta = RutaAerea.idRuta " +
                                "LEFT JOIN RutaTerrestre ON Ruta.idRuta = RutaTerrestre.idRuta " +
                                "LEFT JOIN RutaMaritima ON Ruta.idRuta = RutaMaritima.idRuta";

        StringBuilder rutas = new StringBuilder(); // Creamos un StringBuilder para almacenar las rutas

        // Damos formato a las fechas
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para leer una ruta
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta
            
            while (rs.next()) { // Mientras haya rutas
                rutas.append("ID Ruta: ").append(rs.getInt("idRuta")).append("\n"); // Añadimos el id de la ruta al StringBuilder
                rutas.append("Tipo Ruta: ").append(rs.getString("tipoRuta")).append("\n"); // Añadimos el tipo de ruta al StringBuilder
                rutas.append("Puerto Origen: ").append(rs.getString("puertoOrigen")).append("\n"); // Añadimos el puerto de origen al StringBuilder
                rutas.append("Puerto Destino: ").append(rs.getString("puertoDestino")).append("\n"); // Añadimos el puerto de destino al StringBuilder
                // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // String fechaSalida = dateFormat.format(rs.getDate("fechaSalida")); // Formateamos la fecha de salida
                // String fechaLlegada = dateFormat.format(rs.getDate("fechaLlegada")); // Formateamos la fecha de llegada

                // Date fechaSalida = rs.getDate("fechaSalida"); // Obtenemos la fecha de salida
                // String formattedFechaSalida = dateFormat.format(fechaSalida); // Formateamos la fecha de salida
                // rutas.append("Fecha Salida: ").append(formattedFechaSalida).append("\n"); // Añadimos la fecha de salida al StringBuilder

                // rutas.append("Fecha Salida: ").append(fechaSalida).append("\n");
                // rutas.append("Fecha Llegada: ").append(fechaLlegada).append("\n");

                // Date fechaLlegada = rs.getDate("fechaLlegada"); // Obtenemos la fecha de llegada
                // String formattedFechaLlegada = dateFormat.format(fechaLlegada); // Formateamos la fecha de llegada
                // rutas.append("Fecha Llegada: ").append(formattedFechaLlegada).append("\n"); // Añadimos la fecha de llegada al StringBuilder

                // Formatear y mostrar las fechas en el formato deseado
                // Date fechaSalida = rs.getDate("fechaSalida");
                // String formattedFechaSalida = (fechaSalida != null) ? dateFormat.format(fechaSalida) : "NULL";
                // rutas.append("Fecha Salida: ").append(formattedFechaSalida).append("\n");

                // Date fechaLlegada = rs.getDate("fechaLlegada");
                // String formattedFechaLlegada = (fechaLlegada != null) ? dateFormat.format(fechaLlegada) : "NULL";
                // rutas.append("Fecha Llegada: ").append(formattedFechaLlegada).append("\n");


                rutas.append("Fecha Salida: ").append(rs.getDate("fechaSalida")).append("\n"); // Añadimos la fecha de salida al StringBuilder
                rutas.append("Fecha Llegada: ").append(rs.getDate("fechaLlegada")).append("\n"); // Añadimos la fecha de llegada al StringBuilder
                rutas.append("Coste Ruta: ").append(rs.getInt("costeRuta")).append("\n"); // Añadimos el coste de la ruta al StringBuilder
                rutas.append("\n"); // Añadimos un salto de línea al StringBuilder
                rutas.append("Altitud Vuelo: ").append(rs.getInt("altitudVuelo")).append("\n"); // Añadimos la altitud de vuelo al StringBuilder
                rutas.append("Condiciones Atmosféricas: ").append(rs.getString("condicionesAtmosfericas")).append("\n"); // Añadimos las condiciones atmosféricas al StringBuilder
                rutas.append("Rutas Vuelo: ").append(rs.getString("rutasVuelo")).append("\n"); // Añadimos las rutas de vuelo al StringBuilder
                rutas.append("\n"); // Añadimos un salto de línea al StringBuilder
                rutas.append("Tipo Terreno: ").append(rs.getString("tipoTerreno")).append("\n"); // Añadimos el tipo de terreno al StringBuilder
                rutas.append("Condiciones Climáticas: ").append(rs.getString("condicionesClimaticas")).append("\n"); // Añadimos las condiciones climáticas al StringBuilder
                rutas.append("Número Peajes: ").append(rs.getInt("numeroPeajes")).append("\n"); // Añadimos el número de peajes al StringBuilder
                rutas.append("\n"); // Añadimos un salto de línea al StringBuilder
                rutas.append("Profundidad Agua: ").append(rs.getInt("profundidadAgua")).append("\n"); // Añadimos la profundidad del agua al StringBuilder
                rutas.append("Condiciones Mar: ").append(rs.getString("condicionesMar")).append("\n"); // Añadimos las condiciones del mar al StringBuilder
                rutas.append("Puertos Intermedios: ").append(rs.getString("puertosIntermedios")).append("\n"); // Añadimos los puertos intermedios al StringBuilder

                // Datos específicos de cada tipo de ruta
                // if (rs.getInt("altitudVuelo") != 0) {
                //     rutas.append("Altitud Vuelo: ").append(rs.getInt("altitudVuelo")).append("\n");
                //     rutas.append("Condiciones Atmosféricas: ").append(rs.getString("condicionesAtmosfericas"))
                //             .append("\n");
                //     rutas.append("Rutas Vuelo: ").append(rs.getString("rutasVuelo")).append("\n");
                // // } else if (rs.getString("tipoTerreno") != null) {
                // //     rutas.append("Tipo Terreno: ").append(rs.getString("tipoTerreno")).append("\n");
                // //     rutas.append("Condiciones Climáticas: ").append(rs.getString("condicionesClimaticas")).append("\n");
                // //     rutas.append("Número Peajes: ").append(rs.getInt("numeroPeajes")).append("\n");
                // // } else if (rs.getInt("profundidadAgua") != 0) {
                // //     rutas.append("Profundidad Agua: ").append(rs.getInt("profundidadAgua")).append("\n");
                // //     rutas.append("Condiciones Mar: ").append(rs.getString("condicionesMar")).append("\n");
                // //     rutas.append("Puertos Intermedios: ").append(rs.getString("puertosIntermedios")).append("\n");
                // // }
                rutas.append("\n");
                
            }
        } catch (SQLException e) {
            System.out.println("Error al leer la ruta " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
        return rutas.toString(); // Retornamos las rutas
    }

    public Integer getTipoRuta() { // Método para obtener el tipo de ruta
        return this.tipoRuta;
    }

    public String getPuertoOrigen() { // Método para obtener el puerto de origen
        return this.puertoOrigen;
    }

    public String getPuertoDestino() { // Método para obtener el puerto de destino
        return this.puertoDestino;
    }

    public Date getFechaSalida() {  // Método para obtener la fecha de salida
        return this.fechaSalida;
    }

    public Date getFechaLlegada() { // Método para obtener la fecha de llegada
        return this.fechaLlegada;
    }

    public int getCosteRuta() { // Método para obtener el coste de la ruta
        return this.costeRuta;
    }

    public int getIdRuta() { // Método para obtener el id de la ruta
        return this.idRuta;
    }
}