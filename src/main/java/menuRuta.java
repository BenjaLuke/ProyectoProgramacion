// 21GIIN - Proyectos de Programación - Clase menuRuta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;



public class menuRuta {    

    private static Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer datos del usuario
    private static int idRutaVirtual; // Variable para almacenar el id de la ruta
    public static void main(String[] args) {

        System.setProperty("file.encoding", "UTF-8"); // Establecemos la codificación de caracteres
        
        int opcion; // Variable para almacenar la opción seleccionada por el usuario

        // Crear la conexión con la base de datos
        SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");

        try {

            // Crear la tabla de rutas si no existe
            manager.createTableRuta();

            // Insertar datos si es necesario
            // manager.insertData();

            do {
                System.out.println("=== Menú Rutas===");
                System.out.println("1. Crear nueva ruta");
                System.out.println("2. Modificar ruta");
                System.out.println("3. Eliminar ruta");
                System.out.println("4. Mostrar todas las rutas");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después del nextInt()

                switch (opcion) {
                    case 1:
                        crearNuevaRuta();
                        break;
                    case 2:
                        //modificarRuta();
                        break;
                    case 3:
                        //eliminarRuta();
                        break;
                    case 4:
                        mostrarRutas();
                        break;
                    case 5:
                        System.out.println("Saliendo del menú de rutas...");
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                        break;
                }
            } while (opcion != 5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            manager.closeConnection(); // Cerramos la conexión con la base de datos
            scanner.close(); // Cerramos el objeto Scanner
        }
        
    }

    // Método para crear nueva ruta
    private static void crearNuevaRuta() { 
        System.out.println("=== Crear nueva ruta ===");

        int tipoRuta; // Variable para almacenar el tipo de ruta

        do {
            System.out.println("Seleccione el tipo de ruta: "); // Pedimos el tipo de ruta
            System.out.println("1. Ruta Aérea"); // Opción 1: Ruta Aérea
            System.out.println("2. Ruta Terrestre"); // Opción 2: Ruta Terrestre
            System.out.println("3. Ruta Marítima"); // Opción 3: Ruta Marítima
            System.out.print("Seleccione una opción: "); // Pedimos que seleccione una opción
            tipoRuta = scanner.nextInt(); // Leemos la opción seleccionada
            scanner.nextLine(); // Consumimos el salto de línea

            if (tipoRuta < 1 || tipoRuta > 3) { // Si el tipo de ruta es incorrecto,mostramos un mensaje de error
            System.out.println("Opción incorrecta");
            }else{
                // Datos comunes a todas las rutas
                System.out.print("Puerto de origen: "); // Pedimos el puerto de origen
                String puertoOrigen = scanner.nextLine(); // Leemos el puerto de origen

                System.out.print("Puerto de destino: "); // Pedimos el puerto de destino
                String puertoDestino = scanner.nextLine(); // Leemos el puerto de destino

                System.out.print("Fecha de salida (yyyy/MM/dd): "); // Pedimos la fecha de salida
                String fechaSalidaStr = scanner.nextLine(); // Leemos la fecha de salida
                Date fechaSalida = convertirAFecha(fechaSalidaStr); // Convertimos la fecha de salida a formato Date

                System.out.print("Fecha de llegada (yyyy/MM/dd): "); // Pedimos la fecha de llegada
                String fechaLlegadaStr = scanner.nextLine(); // Leemos la fecha de llegada
                Date fechaLlegada = convertirAFecha(fechaLlegadaStr); // Convertimos la fecha de llegada a formato Date

                System.out.print("Coste de la ruta: "); // Pedimos el coste de la ruta
                int costeRuta = scanner.nextInt(); // Leemos el coste de la ruta
                scanner.nextLine(); // Consumimos el salto de línea

                switch (tipoRuta) {
                    case 1: // Ruta Aérea
                    crearRutaAerea(puertoOrigen, puertoDestino, fechaSalida, fechaLlegada,costeRuta);
                    break;
                    case 2: // Ruta Terrestre
                    //crearRutaTerrestre(puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
                    break;
                    case 3: // Ruta Marítima
                    //crearRutaMaritima(puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
                    break;
                    default:
                    System.out.println("Opción incorrecta");
                    break;
                }

                // Creamos el ID de la ruta automáticamente
                int idRuta = 1 + (int) (Math.random() * 1000);

                idRutaVirtual = idRuta; // Asignamos el id de la ruta a la variable idRutaVirtual
                
                // Creamos una nueva instancia de la clase Ruta
                Ruta nuevaRuta = new Ruta(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
                nuevaRuta.crear(); // Llamamos al método crear de la clase Ruta


            }

        } while (tipoRuta < 1 || tipoRuta > 3); // Mientras el tipo de ruta sea incorrecto, repetimos el bucle
        

    }

    

    // Método para crear una ruta aérea
    private static void crearRutaAerea (String puertoOrigen, String puertoDestino, Date fechaSalida, Date fechaLlegada,
            int costeRuta) {
        // Menú para altitud de vuelo
        System.out.println("Seleccione la altitud de vuelo: ");
        System.out.println("1. Baja: por debajo de 10000 pies");
        System.out.println("2. Alta: por encima de 10000 pies");
        System.out.println("3. Media: por encima de 10000 pies");
        System.out.print("Seleccione una opción: ");
        int altitudVuelo = scanner.nextInt(); // Variable para almacenar la opción seleccionada por el usuario
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerAltitud(altitudVuelo); // Llamar al método para obtener la altitud de vuelo

        // Validación y solicitud de condiciones atmosféricas
        System.out.println("Seleccione las condiciones atmosféricas: ");
        System.out.println("1. Buenas");
        System.out.println("2. Regulares");
        System.out.println("3. Malas");
        System.out.print("Seleccione una opción: ");
        int condicionesAtmosfericas = scanner.nextInt(); // Variable para almacenar la opción seleccionada por el usuario
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerCondiciones(condicionesAtmosfericas); // Llamar al método para obtener las condiciones atmosféricas
            

        // // Pedimos los datos de rutas de vuelo
        System.out.print("Introduzca el número de rutas de vuelo: ");
        int numerorutas = scanner.nextInt(); // variable para almacenar el número de rutas de vuelo
        scanner.nextLine(); // consumir el salto de línea después del nextint()

        // lista para almacenar las rutas de vuelo
        List<String> rutasVuelo = new ArrayList<>();

        // bucle para obtener cada ruta de vuelo
        for (int i = 0; i < numerorutas; i++) {
            System.out.print("Introduzca la ruta de vuelo " + (i + 1) + ": ");
            String ruta = scanner.nextLine(); // variable para almacenar la ruta de vuelo
            rutasVuelo.add(ruta); // añadimos la ruta de vuelo a la lista
        }

        // Asignamos el ID de la ruta automáticamente
        //int idRuta = 1 + (int) (Math.random() * 1000000);       

        // Creamos una nueva instancia de la clase RutaAerea
        // rutaAerea nuevaRutaAerea = new rutaAerea(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada,
        //         costeRuta, obtenerAltitud(opcionAltitud), obtenerCondiciones(opcionCondiciones), rutasVuelo);
        // nuevaRutaAerea.crear(); // Llamamos al método crear de la clase RutaAerea


        // Obtener el idRuta de la ruta creada desde la base de datos
        int idRuta = idRutaVirtual; // Variable para almacenar el id de la ruta        


        rutaAerea nuevaRutaAerea = new rutaAerea(idRuta, 0, "", "", null, null, 0, altitudVuelo, condicionesAtmosfericas, rutasVuelo);
        nuevaRutaAerea.crear(); // Llamamos al método crear de la clase RutaAerea
        
        

    }
    
    // Método para convertir una fecha en formato String a formato Date
    private static Date convertirAFecha(String fechaStr) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd"); // Creamos un objeto SimpleDateFormat
        Date fecha = null; // Variable para almacenar la fecha
        try {
            fecha = new Date(formato.parse(fechaStr).getTime()); // Convertimos la fecha de String a Date
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha: " + e.getMessage());
        }
        return fecha;
    }

    // Método para obtener el ID



    // Método para obtener la altitud de vuelo
    private static String obtenerAltitud(int opcionAltitud) {
        String altitudVuelo = ""; // Variable para almacenar la altitud de vuelo
        switch (opcionAltitud) {
            case 1:
                altitudVuelo = "Baja (por debajo de 10000 pies)";
                break;
            case 2:
                altitudVuelo = "Alta (por encima de 10000 pies)";
                break;
            case 3:
                altitudVuelo = "Media (10000 pies)";
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
        return altitudVuelo;
    }

    // Método para obtener las condiciones atmosféricas
    private static String obtenerCondiciones(int opcionCondiciones) {
        String condicionesAtmosfericas = ""; // Variable para almacenar las condiciones atmosféricas
        switch (opcionCondiciones) {
            case 1:
                condicionesAtmosfericas = "Buenas";
                break;
            case 2:
                condicionesAtmosfericas = "Regulares";
                break;
            case 3:
                condicionesAtmosfericas = "Malas";
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
        return condicionesAtmosfericas;
    }

    // Método para mostrar todas las rutas
    private static void mostrarRutas() {
        System.out.println("=== Mostrar todas las rutas ===");
        // Creamos una nueva instancia de la clase Ruta
        Ruta nuevasRuta = new Ruta(0, 0, "", "", null, null, 0);
        String listaRutas = nuevasRuta.leer(); // Llamamos al método leer de la clase Ruta
        System.out.println(listaRutas); // Mostramos todas las rutas

    }

}



    




