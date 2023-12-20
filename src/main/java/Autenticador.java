// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import src/main/java()

// Clase que se encarga de autenticar a los usuarios
public class Autenticador {

    private static final String DB_FILE_PATH = "src/db/proyecto_logistica.db"; // Ruta a la base de datos
    private static final String SELECT_USER_QUERY = "SELECT * FROM usuarios WHERE email = ? AND Clave = ?"; // Query para buscar usuarios
    private static final String SELECT_USER_ROLES_QUERY = "SELECT r.nombre FROM roles r " + // Query para buscar los roles de un usuario
            "INNER JOIN usuarios_roles ur ON r.id = ur.id_rol " +
            "INNER JOIN usuarios u ON u.id = ur.id_usuario " +
            "WHERE u.email = ?";


    public static void main(String[] args) { // Método main para probar la clase
        SQLiteManager manager = new SQLiteManager(DB_FILE_PATH); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
        manager.createTableUsuarios(); // Creamos la tabla usuarios si no existe

        Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer la entrada del usuario

        System.out.println("Introduzca su email: "); // Pedimos el mail de usuario
        String email = scanner.nextLine(); // Leemos el mail de usuario

        System.out.println("Introduzca su contraseña: "); // Pedimos la contraseña de usuario
        String password = scanner.nextLine(); // Leemos la contraseña de usuario

        // Lógica de autenticación
        try(Connection connection=manager.getConnection(); // Creamos un objeto Connection para conectarnos a la base de datos
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) { // Creamos un objeto PreparedStatement para ejecutar la query de búsqueda
            
            statement.setString(1, email); // Asignamos el email a la primera interrogación
            statement.setString(2, password); // Asignamos la contraseña a la segunda interrogación

            ResultSet resultSet = statement.executeQuery(); // Ejecutamos la query y guardamos el resultado en un objeto ResultSet
            if (resultSet.next()) { // Si el resultado contiene algún registro
                String emailUsuario = resultSet.getString("email"); // Obtenemos el email del usuario

                PreparedStatement rolesStatement = connection.prepareStatement(SELECT_USER_ROLES_QUERY); // Creamos un objeto PreparedStatement para ejecutar la query de búsqueda de roles
                rolesStatement.setString(1, emailUsuario); // Asignamos el email del usuario a la primera interrogación
                ResultSet rolesResultSet = rolesStatement.executeQuery(); // Ejecutamos la query y guardamos el resultado en un objeto ResultSet

                List<String> rolesUsuario = new ArrayList<>(); // Creamos una lista para guardar los roles del usuario
                while (rolesResultSet.next()) { // Mientras el resultado contenga registros
                    String nombreRol = rolesResultSet.getString("nombre"); // Obtenemos el nombre del rol
                    rolesUsuario.add(nombreRol); // Añadimos el rol a la lista
                }

                // Redirigir al usuario a la pantalla correspondiente según sus roles
                if (rolesUsuario.contains("admin")) { // Redirigir al menu de programaUsuario
                    System.out.println("Bienvenido, administrador. Redirigiendo al menú de programaUsuario...");
                    //Lanzamos el programaUsuario
                    programaUsuario.iniciarMenu(args); 
                } else if (rolesUsuario.contains("Operador")) {
                    System.out.println("Redirigiendo a la pantalla de operador...");
                } else if (rolesUsuario.contains("Cliente")) {
                    System.out.println("Redirigiendo a la pantalla de cliente...");
                } else {
                    System.out.println("El usuario no tiene roles asignados");
                }
            } else { // Si el resultado no contiene ningún registro
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
            scanner.close(); // Cerramos el objeto Scanner
        }

    }

}
