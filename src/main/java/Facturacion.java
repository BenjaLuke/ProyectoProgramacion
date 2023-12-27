// 21GIIN - Proyectos de Programación - Clase Facturacion
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;

// Definimos la clase Facturacion
public class Facturacion implements GestorDatos{
    // Definimos los atributos de la clase Facturacion
    private int id_factura; // ID de la factura
    private Date fecha; // Fecha de la factura
    private Time hora; // Hora de la factura
    private String tipo; // Tipo de factura
    private String matricula; // Matrícula del vehículo
    private String nombre; // Nombre del cliente
    private String dni; // DNI del cliente
    private String concepto; // Concepto de la factura
    private double importe; // Importe de la factura
    private String estado; // Estado de la factura

    // Definimos el constructor de la clase Facturacion
    public Facturacion (int id_factura, Date fecha, Time hora, String tipo, String matricula, String nombre, String dni, String concepto, double importe, String estado) {
        this.id_factura = id_factura;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.matricula = matricula;
        this.nombre = nombre;
        this.dni = dni;
        this.concepto = concepto;
        this.importe = importe;
        this.estado = estado;
    }

    
    // Implementación de los métodos de la interfaz GestorDatos
    @Override
    public void crear() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para crear una factura
        String sql = "INSERT INTO facturacion (id_factura, fecha, hora, tipo, matricula, nombre, dni, concepto, importe, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para crear una factura
            pstmt.setInt(1, this.id_factura); // Asignamos el id de la factura
            pstmt.setDate(2, this.fecha); // Asignamos la fecha de la factura
            pstmt.setTime(3, this.hora); // Asignamos la hora de la factura
            pstmt.setString(4, this.tipo); // Asignamos el tipo de la factura
            pstmt.setString(5, this.matricula); // Asignamos la matrícula del vehículo
            pstmt.setString(6, this.nombre); // Asignamos el nombre del cliente
            pstmt.setString(7, this.dni); // Asignamos el DNI del cliente
            pstmt.setString(8, this.concepto); // Asignamos el concepto de la factura
            pstmt.setDouble(9, this.importe); // Asignamos el importe de la factura
            pstmt.setString(10, this.estado); // Asignamos el estado de la factura
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Factura creada con éxito: " + this.id_factura); // Mostramos un mensaje de éxito
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos un mensaje de error
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public void modificar() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para modificar una factura
        String sql = "UPDATE facturacion SET fecha = ?, hora = ?, tipo = ?, matricula = ?, nombre = ?, dni = ?, concepto = ?, importe = ?, estado = ? WHERE id_factura = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para modificar una factura
            pstmt.setDate(1, this.fecha); // Asignamos la fecha de la factura
            pstmt.setTime(2, this.hora); // Asignamos la hora de la factura
            pstmt.setString(3, this.tipo); // Asignamos el tipo de la factura
            pstmt.setString(4, this.matricula); // Asignamos la matrícula del vehículo
            pstmt.setString(5, this.nombre); // Asignamos el nombre del cliente
            pstmt.setString(6, this.dni); // Asignamos el DNI del cliente
            pstmt.setString(7, this.concepto); // Asignamos el concepto de la factura
            pstmt.setDouble(8, this.importe); // Asignamos el importe de la factura
            pstmt.setString(9, this.estado); // Asignamos el estado de la factura
            pstmt.setInt(10, this.id_factura); // Asignamos el id de la factura
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Factura modificada con éxito: " + this.id_factura); // Mostramos un mensaje de éxito
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos un mensaje de error
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public void eliminar() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para eliminar una factura
        String sql = "DELETE FROM facturacion WHERE id_factura = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para eliminar una factura
            pstmt.setInt(1, this.id_factura); // Asignamos el id de la factura
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Factura eliminada con éxito: " + this.id_factura); // Mostramos un mensaje de éxito
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos un mensaje de error
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public String leer() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para leer una factura
        String sql = "SELECT * FROM facturacion";
        StringBuilder facturacion = new StringBuilder(); // Creamos un StringBuilder para guardar los datos de la factura

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para leer una factura
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta

            // Recorremos el ResultSet
            while (rs.next()) {
                facturacion.append("ID: ").append(rs.getInt("id_factura")).append("\n"); // Añadimos el id de la factura al StringBuilder
                facturacion.append("Fecha: ").append(rs.getString("fecha")).append("\n"); // Añadimos la fecha de la factura al StringBuilder
                facturacion.append("Hora: ").append(rs.getString("hora")).append("\n"); // Añadimos la hora de la factura al StringBuilder
                facturacion.append("Tipo: ").append(rs.getString("tipo")).append("\n"); // Añadimos el tipo de la factura al StringBuilder
                facturacion.append("Matrícula: ").append(rs.getString("matricula")).append("\n"); // Añadimos la matrícula del vehículo al StringBuilder
                facturacion.append("Nombre: ").append(rs.getString("nombre")).append("\n"); // Añadimos el nombre del cliente al StringBuilder
                facturacion.append("DNI: ").append(rs.getString("dni")).append("\n"); // Añadimos el DNI del cliente al StringBuilder
                facturacion.append("Concepto: ").append(rs.getString("concepto")).append("\n"); // Añadimos el concepto de la factura al StringBuilder
                facturacion.append("Importe: ").append(rs.getDouble("importe")).append("\n"); // Añadimos el importe de la factura al StringBuilder
                facturacion.append("Estado: ").append(rs.getString("estado")).append("\n"); // Añadimos el estado de la factura al StringBuilder
                facturacion.append("\n"); // Añadimos un salto de línea al StringBuilder
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos un mensaje de error
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }

        return facturacion.toString(); // Devolvemos los datos de la factura
    }

}
    
    
