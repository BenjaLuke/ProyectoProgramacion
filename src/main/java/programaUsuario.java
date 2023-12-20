import java.util.Scanner;

public class programaUsuario {

    //public static void main(String[] args) {
    public static void iniciarMenu(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Crear la conexión con la base de datos
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");
        
            
        try { 

            // Crear la tabla de usuarios si no existe
            manager.createTableUsuarios();

            // Insertar datos si es necesario
            // manager.insertData();

            do {
                System.out.println("=== Menú ===");
                System.out.println("1. Crear usuario");
                System.out.println("2. Modificar usuario");
                System.out.println("3. Eliminar usuario");
                System.out.println("4. Mostrar todos los usuarios");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después del nextInt()

                switch (opcion) {
                    case 1:
                        crearUsuario(scanner);
                        break;
                    case 2:
                        modificarUsuario(scanner);
                        break;
                    case 3:
                        eliminarUsuario(scanner);
                        break;
                    case 4:
                        mostrarUsuarios();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 5);

        }finally{
            manager.closeConnection(); // Cerrar la conexión con la base de datos
            }
            scanner.close();
    }

    private static void crearUsuario(Scanner scanner) {
        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la clave:");
        String clave = scanner.nextLine();

        System.out.println("Ingrese el tipo de usuario:");
        String tipoUsuario = scanner.nextLine();

        System.out.println("Ingrese el teléfono:");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese el email:");
        String email = scanner.nextLine();

        // Generar un ID único secuencial
        int id = 1 + (int) (Math.random() * 100); // Generar ID aleatorio
         
        Usuario nuevoUsuario = new Usuario(id, nombre, clave, tipoUsuario, telefono, email);
        nuevoUsuario.crear();
        
    }

    private static void modificarUsuario(Scanner scanner) {
        System.out.println("Ingrese el ID del usuario a modificar:");
        int idModificar = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()

        System.out.println("Ingrese el nuevo nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la nueva clave:");
        String clave = scanner.nextLine();

        System.out.println("Ingrese el nuevo tipo de usuario:");
        String tipoUsuario = scanner.nextLine();

        System.out.println("Ingrese el nuevo teléfono:");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese el nuevo email:");
        String email = scanner.nextLine();

        Usuario usuarioModificado = new Usuario(idModificar, nombre, clave, tipoUsuario, telefono, email);
        usuarioModificado.modificar();
    }

    private static void eliminarUsuario(Scanner scanner) {
        System.out.println("Ingrese el ID del usuario a eliminar:");
        int idEliminar = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()

        Usuario usuarioEliminar = new Usuario(idEliminar, "", "", "", "", "");
        usuarioEliminar.eliminar();
    }

    private static void mostrarUsuarios() {
        System.out.println("=== Usuarios ===");
        Usuario usuario = new Usuario(0, "", "", "", "", "");
        String listaUsuarios = usuario.leer();
        System.out.println(listaUsuarios);
    }
}
