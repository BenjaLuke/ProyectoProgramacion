// 21GIIN - Proyectos de Programación - Clase Usuario
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.swing.text.html.HTMLDocument.Iterator;

// Definimos la clase Usuario
public class Usuario implements GestorDatos{
    
    // Definimos la lista de usuarios
    private static List<Usuario> listaUsuarios = new ArrayList<>();

    // Definimos el contador de ids
    //private static int contadorIds = 0; // Variable estática para generar IDs únicos

    // Atributos
    private int id;
    private String nombre;
    private String clave;
    private String tipoUsuario;
    private String telefono;
    private String email;

    // Constructor
    public Usuario(int id, String nombre, String clave, String tipoUsuario, String telefono, String email) {
        this.id = id; // Asignamos el id y luego incrementamos el contador
        this.nombre = nombre;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public void crear() {
    
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");

        String sql = "INSERT INTO usuarios (nombre, clave, tipoUsuario, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.clave);
            pstmt.setString(3, this.tipoUsuario);
            pstmt.setString(4, this.telefono);
            pstmt.setString(5, this.email);
            pstmt.executeUpdate();

            System.out.println("Usuario creado con éxito: " + this.nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            manager.closeConnection();
        }
    }

    @Override
    public void modificar() {
    
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");

        String sql = "UPDATE usuarios SET nombre = ?, clave = ?, tipoUsuario = ?, telefono = ?, email = ? WHERE id = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.clave);
            pstmt.setString(3, this.tipoUsuario);
            pstmt.setString(4, this.telefono);
            pstmt.setString(5, this.email);
            pstmt.setInt(6, this.id);
            pstmt.executeUpdate();

            System.out.println("Usuario modificado con éxito: " + this.nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            manager.closeConnection();
        }
    }

    @Override
    public void eliminar() {

        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();

            System.out.println("Usuario eliminado exitosamente: " + this.nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            manager.closeConnection();
        }
    }

    @Override
    public String leer() {
        
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");

        String sql = "SELECT * FROM usuarios";
        StringBuilder usuarios = new StringBuilder();

        try (PreparedStatement pstmt = manager.getConnection().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                usuarios.append("ID: ").append(rs.getInt("id")).append(", Nombre: ").append(rs.getString("nombre"))
                        .append(", Clave: ").append(rs.getString("clave")).append(", Tipo de usuario: ")
                        .append(rs.getString("tipoUsuario")).append(", Teléfono: ").append(rs.getString("telefono"))
                        .append(", Email: ").append(rs.getString("email")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            manager.closeConnection();
        }

        return usuarios.toString();
    }

}
    



