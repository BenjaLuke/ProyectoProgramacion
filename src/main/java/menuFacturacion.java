// 21GIIN - Proyectos de Programación - Clase menuFacturacion
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

import java.util.Calendar;
import java.util.Scanner;
import java.sql.Date;
import java.sql.Time;

// Clase que se encarga de gestionar el menú de facturación
public class menuFacturacion { 

    private static Date obtenerFechaActual() { // Método para obtener la fecha actual
        Calendar calendar = Calendar.getInstance(); // Creamos un objeto Calendar para obtener la fecha actual
        return new Date(calendar.getTimeInMillis()); // Devolvemos la fecha actual
    }

    private static Time obtenerHoraActual() { // Método para obtener la hora actual
        Calendar calendar = Calendar.getInstance(); // Creamos un objeto Calendar para obtener la hora actual
        return new Time(calendar.getTimeInMillis()); // Devolvemos la hora actual
    }
    //public static void main(String[] args) {
    public static void iniciarMenu() { // Método para iniciar el menú de facturación
        System.setProperty("file.encoding", "UTF-8"); // Establecemos la codificación de caracteres
        Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer datos del usuario
        int opcion; // Variable para almacenar la opción seleccionada por el usuario

        // Crear la conexión con la base de datos
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");
        
            
        try { 

            // Crear la tabla de facturación si no existe
            manager.createTableFacturacion();

            // Insertar datos si es necesario
            // manager.insertData();

            do {
                System.out.println("=== Menú ===");
                System.out.println("1. Crear factura");
                System.out.println("2. Modificar factura");
                System.out.println("3. Eliminar factura");
                System.out.println("4. Mostrar todas las facturas");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después del nextInt()

                switch (opcion) {
                    case 1:
                        crearFactura(scanner);
                        break;
                    case 2:
                        modificarFactura(scanner);
                        break;
                    case 3:
                        eliminarFactura(scanner);
                        break;
                    case 4:
                        mostrarFacturas();
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

    private static void crearFactura(Scanner scanner) { // Método para crear una factura
        System.out.println("=== Crear factura ===");

        // Obtener la fecha y la hora actuales del sistema
        java.sql.Date fechaActual = obtenerFechaActual();
        java.sql.Time horaActual = obtenerHoraActual();

        System.out.println("Fecha actual: " + fechaActual);
        System.out.println("Hora actual: " + horaActual);

        System.out.print("Introduzca el tipo de la factura: ");
        String tipo = scanner.nextLine(); // Pedimos el tipo de la factura
        System.out.print("Introduzca la matrícula del vehículo: ");
        String matricula = scanner.nextLine(); // Pedimos la matrícula del vehículo
        System.out.print("Introduzca el nombre del cliente: ");
        String nombre = scanner.nextLine(); // Pedimos el nombre del cliente
        System.out.print("Introduzca el DNI del cliente: ");
        String dni = scanner.nextLine(); // Pedimos el DNI del cliente
        System.out.print("Introduzca el concepto de la factura: ");
        String concepto = scanner.nextLine(); // Pedimos el concepto de la factura
        System.out.print("Introduzca el importe de la factura: ");
        double importe = scanner.nextDouble(); // Pedimos el importe de la factura
        scanner.nextLine(); // Consumir el salto de línea después del nextDouble()
        System.out.print("Introduzca el estado de la factura: ");
        String estado = scanner.nextLine(); // Pedimos el estado de la factura

        // Añadir ID de la factura de forma secuencial
        int id_factura = 1 + (int) (Math.random() * 100); // Generar ID aleatorio

        Facturacion factura = new Facturacion(id_factura, fechaActual, horaActual, tipo, matricula, nombre, dni, concepto, importe, estado); // Creamos un objeto Facturacion con los datos introducidos por el usuario
        factura.crear(); // Creamos la factura en la base de datos
    }

    // Método para modificar una factura
    private static void modificarFactura(Scanner scanner) {
        System.out.println("=== Modificar factura ===");
        System.out.print("Introduzca el ID de la factura a modificar: ");
        int id_factura = scanner.nextInt(); // Pedimos el ID de la factura a modificar
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()

        // Obtener la fecha y la hora actuales del sistema
        java.sql.Date fechaActual = obtenerFechaActual();
        java.sql.Time horaActual = obtenerHoraActual();

        System.out.println("Fecha actual: " + fechaActual);
        System.out.println("Hora actual: " + horaActual);

        System.out.print("Introduzca el nuevo tipo de la factura: ");
        String tipo = scanner.nextLine(); // Pedimos el tipo de la factura
        System.out.print("Introduzca la nueva matrícula del vehículo: ");
        String matricula = scanner.nextLine(); // Pedimos la matrícula del vehículo
        System.out.print("Introduzca el nuevo nombre del cliente: ");
        String nombre = scanner.nextLine(); // Pedimos el nombre del cliente
        System.out.print("Introduzca el nuevo DNI del cliente: ");
        String dni = scanner.nextLine(); // Pedimos el DNI del cliente
        System.out.print("Introduzca el nuevo concepto de la factura: ");
        String concepto = scanner.nextLine(); // Pedimos el concepto de la factura
        System.out.print("Introduzca el nuevo importe de la factura: ");
        double importe = scanner.nextDouble(); // Pedimos el importe de la factura
        scanner.nextLine(); // Consumir el salto de línea después del nextDouble()
        System.out.print("Introduzca el nuevo estado de la factura: ");
        String estado = scanner.nextLine(); // Pedimos el estado de la factura

        Facturacion factura = new Facturacion(id_factura, fechaActual, horaActual, tipo, matricula, nombre, dni, concepto, importe, estado); // Creamos un objeto Facturacion con los datos introducidos por el usuario
        factura.modificar(); // Modificamos la factura en la base de datos
    }

    // Método para eliminar una factura
    private static void eliminarFactura(Scanner scanner) {
        System.out.println("=== Eliminar factura ===");
        System.out.print("Introduzca el ID de la factura a eliminar: ");
        int id_factura = scanner.nextInt(); // Pedimos el ID de la factura a eliminar
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()

        Facturacion factura = new Facturacion(id_factura, null, null, null, null, null, null, null, 0, null); // Creamos un objeto Facturacion con los datos introducidos por el usuario
        factura.eliminar(); // Eliminamos la factura de la base de datos
    }

    // Método para mostrar todas las facturas
    private static void mostrarFacturas() {
        System.out.println("=== Mostrar todas las facturas ===");
        Facturacion factura = new Facturacion(0, null, null, null, null, null, null, null, 0, null); // Creamos un objeto Facturacion con los datos introducidos por el usuario
        String listaFacturas = factura.leer(); // Obtenemos todas las facturas de la base de datos
        System.out.println(listaFacturas); // Mostramos todas las facturas de la base de datos
        //factura.leer(); // Mostramos todas las facturas de la base de datos
    }

}
