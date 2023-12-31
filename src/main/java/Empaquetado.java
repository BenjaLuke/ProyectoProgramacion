// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


// Definimos la clase Empaquetado
public class Empaquetado implements GestorDatos{
    // Atributos de la clase Empaquetado
    private int id_empaquetado; // ID del empaquetado
    private int numero_contenedores; // Número de contenedores
    private double peso; // Peso del empaquetado
    private double tamano; // Tamaño del empaquetado
    private double densidad; // Densidad del empaquetado

    // Constructor
    public Empaquetado(int id_empaquetado, int numero_contenedores, double peso, double tamano) {
        this.id_empaquetado = id_empaquetado; // Asignamos el id del empaquetado
        this.numero_contenedores = numero_contenedores; // Asignamos el número de contenedores
        this.peso = peso; // Asignamos el peso del empaquetado
        this.tamano = tamano; // Asignamos el tamaño del empaquetado
        this.densidad = calcularDensidad(); // Calculamos la densidad del empaquetado
        
    }

    // Método para calcular la densidad del empaquetado
    public double calcularDensidad() {
        if (tamano != 0) { // Comprobamos que el tamaño no sea 0
            return peso / tamano; // Calculamos la densidad
        } else {
            //System.out.println("Error: el tamaño no puede ser 0. No se puede calcular la densidad.");
            return 0; // Devolvemos 0
        }
        
    }


    // Implementación de los métodos de la interfaz GestorDatos
    @Override
    public void crear() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para crear un empaquetado
        String sql = "INSERT INTO empaquetado (id_empaquetado, numero_contenedores, peso, tamano, densidad) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para crear un empaquetado
            pstmt.setInt(1, this.id_empaquetado); // Asignamos el id del empaquetado
            pstmt.setInt(2, this.numero_contenedores); // Asignamos el número de contenedores
            pstmt.setDouble(3, this.peso); // Asignamos el peso del empaquetado
            pstmt.setDouble(4, this.tamano); // Asignamos el tamaño del empaquetado
            double densidad = calcularDensidad(); // Obtenemos la densidad del empaquetado
            pstmt.setDouble(5, densidad); // Asignamos la densidad del empaquetado
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Empaquetado creado con éxito: " + this.id_empaquetado);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al crear el empaquetado: " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public void modificar() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para modificar un empaquetado
        String sql = "UPDATE empaquetado SET numero_contenedores = ?, peso = ?, tamano = ?, densidad = ? WHERE id_empaquetado = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para modificar un empaquetado
            pstmt.setInt(1, this.numero_contenedores); // Asignamos el número de contenedores
            pstmt.setDouble(2, this.peso); // Asignamos el peso del empaquetado
            pstmt.setDouble(3, this.tamano); // Asignamos el tamaño del empaquetado
            double densidad = calcularDensidad(); // Obtenemos la densidad del empaquetado
            pstmt.setDouble(4, densidad); // Asignamos la densidad del empaquetado
            pstmt.setInt(5, this.id_empaquetado); // Asignamos el id del empaquetado
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Empaquetado modificado con éxito: " + this.id_empaquetado);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al modificar el empaquetado: " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
        
    }

    @Override
    public void eliminar() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para eliminar un empaquetado
        String sql = "DELETE FROM empaquetado WHERE id_empaquetado = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para eliminar un empaquetado
            pstmt.setInt(1, this.id_empaquetado); // Asignamos el id del empaquetado
            pstmt.executeUpdate(); // Ejecutamos la consulta

            System.out.println("Empaquetado eliminado con éxito: " + this.id_empaquetado);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el empaquetado: " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    @Override
    public String leer() {
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db"); // Creamos un objeto SQLiteManager para conectarnos a la base de datos

        // Definimos la consulta SQL para leer un empaquetado
        String sql = "SELECT * FROM empaquetado";
        StringBuilder empaquetados = new StringBuilder();

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) { // Creamos el PreparedStatement para leer un empaquetado
            ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta

            // Recorremos el ResultSet
            while (rs.next()) {
                // Guardamos los datos del empaquetado en el StringBuilder
                empaquetados.append("ID: ").append(rs.getInt("id_empaquetado")).append("\n");
                empaquetados.append("Número de contenedores: ").append(rs.getInt("numero_contenedores")).append("\n");
                empaquetados.append("Peso: ").append(rs.getDouble("peso")).append("\n");
                empaquetados.append("Tamaño: ").append(rs.getDouble("tamano")).append("\n");   
                double densidad = rs.getDouble("densidad"); // Obtenemos la densidad del empaquetado"));
                empaquetados.append("Densidad: ").append(densidad).append("\n\n");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el empaquetado: " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
        return empaquetados.toString(); // Devolvemos los datos del empaquetado

    }

}


