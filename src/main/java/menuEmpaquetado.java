// 21GIIN - Proyectos de Programación - Clase menuEmpaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.util.Scanner;

public class menuEmpaquetado {

    public static void iniciarMenu(String[] args) {
    // public static void main (String[] args) {
        System.setProperty("file.encoding", "UTF-8"); // Establecemos la codificación de caracteres
        Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer datos del usuario
        int opcion; // Variable para almacenar la opción seleccionada por el usuario

        // Crear la conexión con la base de datos
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");
        
            
        try { 

            // Crear la tabla de empaquetado si no existe
            manager.createTableEmpaquetado();

            // Insertar datos si es necesario
            // manager.insertData();

            do {
                System.out.println("=== Menú ===");
                System.out.println("1. Crear empaquetado");
                System.out.println("2. Modificar empaquetado");
                System.out.println("3. Eliminar empaquetado");
                System.out.println("4. Mostrar todos los empaquetados");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después del nextInt()

                switch (opcion) {
                    case 1:
                        crearEmpaquetado(scanner);
                        break;
                    case 2:
                        modificarEmpaquetado(scanner);
                        break;
                    case 3:
                        eliminarEmpaquetado(scanner);
                        break;
                    case 4:
                        mostrarEmpaquetados();
                        break;
                    case 5:
                        System.out.println("Saliendo del menú de empaquetado...");
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                        break;
                }
            } while (opcion != 5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
        }
    }

    private static void crearEmpaquetado(Scanner scanner) { // Método para crear un empaquetado
        System.out.println("=== Crear empaquetado ===");
        
        System.out.println("Introduzca el número de contenedores: ");
        int numero_contenedores = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Ingrese el peso: ");
        double peso = Double.parseDouble(scanner.nextLine());
               
        System.out.println("Ingrese el tamaño: ");
        double tamano = Double.parseDouble(scanner.nextLine());

        // Añadir ID del empquetado de manera automática
        int id_empaquetado = 1 + (int)(Math.random() * 1000);
        
        Empaquetado nuevoEmpaquetado = new Empaquetado(id_empaquetado, numero_contenedores, peso, tamano); // Creamos un objeto Empaquetado con los datos del usuario
        nuevoEmpaquetado.crear(); // Creamos el empaquetado en la base de datos
    }

    // Método para modificar un empaquetado
    private static void modificarEmpaquetado(Scanner scanner) {
        System.out.println("=== Modificar empaquetado ===");
        
        System.out.println("Introduzca el ID del empaquetado a modificar: ");
        int id_empaquetado = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Introduzca el nuevo número de contenedores: ");
        int numero_contenedores = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Introduzca el nuevo peso: ");
        double peso = Double.parseDouble(scanner.nextLine());
               
        System.out.println("Introduzca el nuevo tamaño: ");
        double tamano = Double.parseDouble(scanner.nextLine());

        Empaquetado nuevoEmpaquetado = new Empaquetado(id_empaquetado, numero_contenedores, peso, tamano); // Creamos un objeto Empaquetado con los datos del usuario
        nuevoEmpaquetado.modificar(); // Modificamos el empaquetado en la base de datos
    }

    // Método para eliminar un empaquetado
    private static void eliminarEmpaquetado(Scanner scanner) {
        System.out.println("=== Eliminar empaquetado ===");
        
        System.out.println("Introduzca el ID del empaquetado a eliminar: ");
        int id_empaquetado = Integer.parseInt(scanner.nextLine());

        Empaquetado nuevoEmpaquetado = new Empaquetado(id_empaquetado, 0, 0, 0); // Creamos un objeto Empaquetado con los datos del usuario
        nuevoEmpaquetado.eliminar(); // Eliminamos el empaquetado de la base de datos
    }

    // Método para mostrar todos los empaquetados
    private static void mostrarEmpaquetados() {
        System.out.println("=== Mostrar empaquetados ===");
        Empaquetado nuevoEmpaquetado = new Empaquetado(0, 0, 0, 0); // Creamos un objeto Empaquetado con los datos del usuario  
        String listaEmpaquetados = nuevoEmpaquetado.leer(); // Mostramos todos los empaquetados de la base de datos
        System.out.println(listaEmpaquetados); // Mostramos todos los empaquetados de la base de datos
    }

}
