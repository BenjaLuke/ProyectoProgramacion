// 21GIIN - Proyectos de Programación - Clase Autenticador
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

// Clase que se encarga de autenticar a los usuarios
public class Autenticador {

    private static final String DB_FILE_PATH = "src/db/proyecto_logistica.db"; // Ruta a la base de datos
    private static final String SELECT_USER_QUERY = "SELECT * FROM usuarios WHERE email = ? AND Clave = ?"; // Query para buscar usuarios
    private static final String SELECT_USER_ROLES_QUERY = "SELECT r.nombre FROM roles r " + // Query para buscar los roles de un usuario
            "INNER JOIN usuarios_roles ur ON r.id = ur.id_rol " +
            "INNER JOIN usuarios u ON u.id = ur.id_usuario " +
            "WHERE u.email = ?";

    protected SQLiteManager manager; // Objeto SQLiteManager para conectarnos a la base de datos
    

    // Constructor
    public Autenticador() {
        this.manager = new SQLiteManager(DB_FILE_PATH); // Creamos un objeto SQLiteManager para conectarnos a la base de datos
        this.manager.createTableUsuarios(); // Creamos la tabla usuarios si no existe

    }


    public static void main(String[] args) { // Método main para probar la clase
        Autenticador autenticador = new Autenticador(); // Creamos un objeto Autenticador
        autenticador.autenticarUsuario(); // Llamamos al método autenticarUsuario()
        }

    // // Método para autenticar usuarios
    public void autenticarUsuario() {
        try (Scanner scanner = new Scanner(System.in)) { // Creamos un objeto Scanner para leer datos del usuario
            System.out.println("=== Autenticación ===");
            System.out.print("Introduzca su email: ");
            String email = scanner.nextLine(); // Leemos el email
            System.out.print("Introduzca su contraseña: ");
            String clave = scanner.nextLine(); // Leemos la clave

            // Lógica de autenticación
            try (Connection connection = manager.getConnection(); // Creamos la conexión con la base de datos
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) { // Creamos el PreparedStatement para buscar el usuario

                statement.setString(1, email); // Asignamos el email
                statement.setString(2, clave);  // Asignamos la clave

                ResultSet resultSet = statement.executeQuery(); // Ejecutamos la consulta
                if (resultSet.next()) { // Si el usuario existe
                    String emailUsario = resultSet.getString("email"); // Obtenemos el email del usuario

                    PreparedStatement rolesStatement = connection.prepareStatement(SELECT_USER_ROLES_QUERY); // Creamos el PreparedStatement para buscar los roles del usuario
                    rolesStatement.setString(1, emailUsario); // Asignamos el email del usuario
                    ResultSet rolesResultSet = rolesStatement.executeQuery(); // Ejecutamos la consulta

                    List<String> rolesUsuario = new ArrayList<>(); // Creamos una lista para guardar los roles del usuario
                    while (rolesResultSet.next()) { // Guardamos los roles en la lista
                        String nombreRol = rolesResultSet.getString("nombre"); // Obtenemos el nombre del rol
                        rolesUsuario.add(nombreRol); // Agregamos el rol a la lista
                    }
                    
                    if (rolesUsuario.contains("Admin")) { // Si el usuario tiene el rol admin
                        System.out.println("Bienvenido administrador"); // Mostramos un mensaje de bienvenida
                        // Lanzamos el menú de administrador
                        programaUsuario.iniciarMenu(new String[] {});
                    } else if (rolesUsuario.contains("Contable")) {
                        System.out.println("Bienvenido contable");
                        // Lanzamos el menú de contable
                        menuFacturacion.iniciarMenu(); // Lanzamos el menú de facturación
                    } else if (rolesUsuario.contains("Cliente")) {
                        System.out.println("Redirigiendo a la pantalla de cliente...");
                    } else {
                        System.out.println("El usuario no tiene roles asignados");
                    }
                    // Cerramos la conexión con la base de datos
                    connection.close();
                } else {
                    System.out.println("Usuario o contraseña incorrectos");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // public void autenticarUsuario() {
    //     try (Scanner scanner = new Scanner(System.in)) {
    //         System.out.println("=== Autenticación ===");
    //         System.out.print("Introduzca su email: ");
    //         String email = scanner.nextLine();
    //         System.out.print("Introduzca su contraseña: ");
    //         String clave = scanner.nextLine();

    //         Connection connection = null;

    //         try {
    //             connection = manager.getConnection();

    //             try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) {
    //                 statement.setString(1, email);
    //                 statement.setString(2, clave);
    //                 ResultSet resultSet = statement.executeQuery();

    //                 if (resultSet.next()) {
    //                     String emailUsuario = resultSet.getString("email");

    //                     PreparedStatement rolesStatement = connection.prepareStatement(SELECT_USER_ROLES_QUERY);
    //                     rolesStatement.setString(1, emailUsuario);
    //                     ResultSet rolesResultSet = rolesStatement.executeQuery();

    //                     List<String> rolesUsuario = new ArrayList<>();
    //                     while (rolesResultSet.next()) {
    //                         String nombreRol = rolesResultSet.getString("nombre");
    //                         rolesUsuario.add(nombreRol);
    //                     }

    //                     if (rolesUsuario.contains("Admin")) {
    //                         System.out.println("Bienvenido administrador");
    //                         programaUsuario.iniciarMenu(new String[] {});
    //                     } else if (rolesUsuario.contains("Contable")) {
    //                         System.out.println("Bienvenido contable");
    //                         menuFacturacion.iniciarMenu();
    //                     } else if (rolesUsuario.contains("Cliente")) {
    //                         System.out.println("Redirigiendo a la pantalla de cliente...");
    //                     } else {
    //                         System.out.println("El usuario no tiene roles asignados");
    //                     }
    //                 } else {
    //                     System.out.println("Usuario o contraseña incorrectos");
    //                 }
    //             }
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         } finally {
    //             try {
    //                 if (connection != null) {
    //                     connection.close(); // Cerramos la conexión al finalizar todas las operaciones
    //                 }
    //             } catch (SQLException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    //}

}