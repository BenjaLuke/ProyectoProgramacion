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
                        modificarRuta();
                        break;
                    case 3:
                        eliminarRuta();
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

                // Creamos el ID de la ruta automáticamente
                int idRuta = 1 + (int) (Math.random() * 1000);

                switch (tipoRuta) {
                    case 1: // Ruta Aérea
                    crearRutaAerea(idRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada,costeRuta);
                    break;
                    case 2: // Ruta Terrestre
                    crearRutaTerrestre(idRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
                    break;
                    case 3: // Ruta Marítima
                    crearRutaMaritima(idRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
                    break;
                    default:
                    System.out.println("Opción incorrecta");
                    break;
                }
                
                // Creamos una nueva instancia de la clase Ruta
                Ruta nuevaRuta = new Ruta(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
                nuevaRuta.crear(); // Llamamos al método crear de la clase Ruta


            }

        } while (tipoRuta < 1 || tipoRuta > 3); // Mientras el tipo de ruta sea incorrecto, repetimos el bucle
        

    }

    

    // Método para crear una ruta aérea
    private static void crearRutaAerea (int idRuta, String puertoOrigen, String puertoDestino, Date fechaSalida, Date fechaLlegada,
            int costeRuta) {
        // Menú para altitud de vuelo
        System.out.println("Seleccione la altitud de vuelo: ");
        System.out.println("1. Baja: por debajo de 10000 pies");
        System.out.println("2. Alta: por encima de 10000 pies");
        System.out.println("3. Media: 10000 pies");
        System.out.print("Seleccione una opción: ");
        int altitudVuelo = scanner.nextInt(); // Variable para almacenar la opción seleccionada por el usuario
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerAltitud(altitudVuelo); // Llamar al método para obtener la altitud de vuelo

        // Validación y solicitud de condiciones atmosféricas
        System.out.println("Seleccione las condiciones atmosféricas: ");
        System.out.println("1. Buenas: cielo despejado");
        System.out.println("2. Regulares: cielo nublado");
        System.out.println("3. Malas: lluvia y tormenta");
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


        rutaAerea nuevaRutaAerea = new rutaAerea(idRuta, 0, "", "", null, null, 0, altitudVuelo, condicionesAtmosfericas, rutasVuelo);
        nuevaRutaAerea.crear(); // Llamamos al método crear de la clase RutaAerea
        
        

    }

    // Método para crear una ruta terrestre
    private static void crearRutaTerrestre(int idRuta, String puertoOrigen, String puertoDestino, Date fechaSalida, Date fechaLlegada,
            int costeRuta) {
        // Menú para tipo de terreno
        System.out.println("Seleccione el tipo de terreno: ");
        System.out.println("1. Montaña");
        System.out.println("2. Desierto");
        System.out.println("3. Bosque");
        System.out.println("4. Carretera");
        System.out.print("Seleccione una opción: ");
        int tipoTerreno = scanner.nextInt(); // Variable para almacenar la opción seleccionada por el usuario
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerTipoTerreno(tipoTerreno); // Llamar al método para obtener el tipo de terreno

        // Menú para las condiciones climáticas
        System.out.println("Seleccione las condiciones climáticas: ");
        System.out.println("1. Buenas: Terreno seco");
        System.out.println("2. Regulares: Terreno húmedo");
        System.out.println("3. Malas: Terreno helado");
        System.out.print("Seleccione una opción: ");
        int condicionesAtmosfericas = scanner.nextInt(); // Variable para almacenar la opción seleccionada por el usuario
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerCondiciones(condicionesAtmosfericas); // Llamar al método para obtener las condiciones climáticas

        // Pedimos el número total de peajes
        System.out.print("Introduzca el número total de peajes: ");
        int numeroPeajes = scanner.nextInt(); // Variable para almacenar el número total de peajes
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        
        // Creamos nueva instancia de la clase RutaTerrestre
        rutaTerrestre nuevaRutaTerrestre = new rutaTerrestre(idRuta, 0, "", "", null, null, 0, tipoTerreno, condicionesAtmosfericas, numeroPeajes);
        nuevaRutaTerrestre.crear(); // Llamamos al método crear de la clase RutaTerrestre

    }

    // Método para crear una ruta marítima
    private static void crearRutaMaritima(int idRuta, String puertoOrigen, String puertoDestino, Date fechaSalida, Date fechaLlegada,
            int costeRuta) {
        // Pedimos la profundidad del agua
        System.out.println("Seleccione la profundidad del agua: ");
        System.out.println("1. Baja. Hasta 1000 metros");
        System.out.println("2. Media. Entre 1000 y 2000 metros");
        System.out.println("3. Alta. Más de 2000 metros");
        System.out.print("Seleccione una opción: ");
        int profundidadAgua = scanner.nextInt(); // Variable para almacenar la profundidad del agua
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerProfundidad(profundidadAgua); // Llamar al método para obtener la profundidad del agua

        // Validación y solicitud de condiciones del mar
        System.out.println("Seleccione las condiciones del mar: ");
        System.out.println("1. Buenas. Mar en calma");
        System.out.println("2. Regulares. Mar con oleaje");
        System.out.println("3. Malas. Mar con oleaje y tormenta");
        System.out.println("4. Extremas. Mar con oleaje, tormenta y marea roja");
        System.out.print("Seleccione una opción: ");
        int condicionesMar = scanner.nextInt(); // Variable para almacenar la opción seleccionada por el usuario
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()
        obtenerCondicionesMar(condicionesMar); // Llamar al método para obtener las condiciones del mar

        // Pedimos los puertos intermedios
        System.out.print("Introduzca los puertos intermedios: ");
        String puertosIntermedios = scanner.nextLine(); // Variable para almacenar los puertos intermedios

        // Creamos nueva instancia de la clase RutaMaritima
        rutaMaritima nuevaRutaMaritima = new rutaMaritima(idRuta, 0, "", "", null, null, 0, profundidadAgua, condicionesMar, puertosIntermedios);
        nuevaRutaMaritima.crear(); // Llamamos al método crear de la clase RutaMaritima

    }

    // Método para obtener la profundidad del agua
    private static String obtenerProfundidad(int opcionProfundidad) {
        String profundidadAgua = ""; // Variable para almacenar la profundidad del agua
        switch (opcionProfundidad) {
            case 1:
                profundidadAgua = "Baja. Hasta 1000 metros";
                break;
            case 2:
                profundidadAgua = "Media. Entre 1000 y 2000 metros";
                break;
            case 3:
                profundidadAgua = "Alta. Más de 2000 metros";
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
        return profundidadAgua;
    }

    // Método para obtener las condiciones del mar
    private static String obtenerCondicionesMar(int opcionCondiciones) {
        String condicionesMar = ""; // Variable para almacenar las condiciones del mar
        switch (opcionCondiciones) {
            case 1:
                condicionesMar = "Buenas. Mar en calma";
                break;
            case 2:
                condicionesMar = "Regulares. Mar con oleaje";
                break;
            case 3:
                condicionesMar = "Malas. Mar con oleaje y tormenta";
                break;
            case 4:
                condicionesMar = "Extremas. Mar con oleaje, tormenta y marea roja";
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
        return condicionesMar;
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

    // Método para obtener el tipo de terreno
    private static String obtenerTipoTerreno(int opcionTerreno) {
        String tipoTerreno = ""; // Variable para almacenar el tipo de terreno
        switch (opcionTerreno) {
            case 1:
                tipoTerreno = "Montaña";
                break;
            case 2:
                tipoTerreno = "Desierto";
                break;
            case 3:
                tipoTerreno = "Bosque";
                break;
            case 4:
                tipoTerreno = "Carretera";
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
        return tipoTerreno;
    }


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
                condicionesAtmosfericas = "Buenas: Cielo despejado";
                break;
            case 2:
                condicionesAtmosfericas = "Regulares: Cielo nublado";
                break;
            case 3:
                condicionesAtmosfericas = "Malas: Lluvia y tormenta";
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
        // System.out.println("=== Mostrar Rutas Aéreas ===");
        // // Creamos una nueva instancia de la clase RutaAerea
        // rutaAerea nuevasRutaAerea = new rutaAerea(0, 0, "", "", null, null, 0, 0, 0, null);
        // String listaRutasAerea = nuevasRutaAerea.leer(); // Llamamos al método leer de la clase RutaAerea
        // System.out.println(listaRutasAerea); // Mostramos todas las rutas aéreas
        // System.out.println("=== Mostrar Rutas Terrestres ===");
        // // Creamos una nueva instancia de la clase RutaTerrestre
        // rutaTerrestre nuevasRutaTerrestre = new rutaTerrestre(0, 0, "", "", null, null, 0, 0, 0, 0);
        // String listaRutasTerrestre = nuevasRutaTerrestre.leer(); // Llamamos al método leer de la clase RutaTerrestre
        // System.out.println(listaRutasTerrestre); // Mostramos todas las rutas terrestres
        
        // Ruta nuevasRuta = new Ruta(0, 0, "", "", null, null, 0);
        // rutaAerea nuevasRutaAerea = new rutaAerea(0, 0, "", "", null, null, 0, 0, 0, null);
        // // rutaTerrestre nuevasRutaTerrestre = new rutaTerrestre(0, 0, "", "", null, null, 0, 0, 0, 0);
        // // rutaMaritima nuevasRutaMaritima = new rutaMaritima(0, 0, "", "", null, null, 0, 0, 0, "");

        // String listaRutas = nuevasRuta.leer(); // Llamamos al método leer de la clase Ruta
        // String listaRutasAerea = nuevasRutaAerea.leer(); // Llamamos al método leer de la clase RutaAerea
        // // String listaRutasTerrestre = nuevasRutaTerrestre.leer(); // Llamamos al método leer de la clase RutaTerrestre
        // // String listaRutasMaritima = nuevasRutaMaritima.leer(); // Llamamos al método leer de la clase RutaMaritima

        // System.out.println("=== Mostrar todas las rutas ===");
        // System.out.println(listaRutas); // Mostramos todas las rutas
        // System.out.println("=== Mostrar Rutas Aéreas ===");
        // System.out.println(listaRutasAerea); // Mostramos todas las rutas aéreas
        // System.out.println("=== Mostrar Rutas Terrestres ===");
        // System.out.println(listaRutasTerrestre); // Mostramos todas las rutas terrestres
        // System.out.println("=== Mostrar Rutas Marítimas ===");
        // System.out.println(listaRutasMaritima); // Mostramos todas las rutas marítimas



    }

    // Método para modificar datos de una ruta
    private static void modificarRuta() {
        // SQLiteManager manager = new SQLiteManager("src/db/proyecto_logistica.db");
        System.out.println("=== Modificar ruta ===");
        System.out.println("");
        System.out.println("ATENCIÓN: NO se puede modificar el tipo de ruta, si desea cambiarlo, elimine la ruta y cree una nueva");
        System.out.println("");
        
        System.out.println("Introduzca el ID de la ruta a modificar: ");
        int idRuta = Integer.parseInt(scanner.nextLine());

        
        // Mostrar el tipo de ruta del id introducido
        System.out.println("Introduzca el tipo de ruta correspondiente a su idRuta: ");
        int tipoRuta = Integer.parseInt(scanner.nextLine());
        

        System.out.println("Introduzca el nuevo puerto de origen: ");
        String puertoOrigen = scanner.nextLine();

        System.out.println("Introduzca el nuevo puerto de destino: ");
        String puertoDestino = scanner.nextLine();

        System.out.println("Introduzca la nueva fecha de salida (yyyy/MM/dd): ");
        String fechaSalidaStr = scanner.nextLine();
        Date fechaSalida = convertirAFecha(fechaSalidaStr);

        System.out.println("Introduzca la nueva fecha de llegada (yyyy/MM/dd): ");
        String fechaLlegadaStr = scanner.nextLine();
        Date fechaLlegada = convertirAFecha(fechaLlegadaStr);

        System.out.println("Introduzca el nuevo coste de la ruta: ");
        int costeRuta = Integer.parseInt(scanner.nextLine());

        // Creamos una nueva instancia de la clase Ruta
        Ruta nuevaRuta = new Ruta(idRuta, tipoRuta, puertoOrigen, puertoDestino, fechaSalida, fechaLlegada, costeRuta);
        nuevaRuta.modificar(); // Llamamos al método modificar de la clase Ruta

        // Según el tipo de ruta, llamamos al método modificar correspondiente
        switch (tipoRuta) {
            case 1: // Ruta Aérea
            modificarRutaAerea(idRuta);
            break;
            case 2: // Ruta Terrestre
            modificarRutaTerrestre(idRuta);
            break;
            case 3: // Ruta Marítima
            modificarRutaMaritima(idRuta);
            break;
            default:
            System.out.println("Opción incorrecta");
            break;
        }

    }


    // Método para modificar una ruta aérea
    private static void modificarRutaAerea(int idRuta) {
        System.out.println("=== Modificar ruta aérea ===");
        

        System.out.println("Introduzca la nueva altitud de vuelo: ");
        int altitudVuelo = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca las nuevas condiciones atmosféricas: Buenas(1): Cielo despejado, Regulares(2): Cielo nublado, Malas(3): Lluvia y tormenta");
        int condicionesAtmosfericas = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca el nuevo número de rutas de vuelo: ");
        int numerorutas = Integer.parseInt(scanner.nextLine());

        // lista para almacenar las rutas de vuelo
        List<String> rutasVuelo = new ArrayList<>();

        // bucle para obtener cada ruta de vuelo
        for (int i = 0; i < numerorutas; i++) {
            System.out.print("Introduzca la ruta de vuelo " + (i + 1) + ": ");
            String ruta = scanner.nextLine(); // variable para almacenar la ruta de vuelo
            rutasVuelo.add(ruta); // añadimos la ruta de vuelo a la lista
        }

        // Creamos una nueva instancia de la clase RutaAerea
        rutaAerea nuevaRutaAerea = new rutaAerea(idRuta, 0, "", "", null, null, 0, altitudVuelo, condicionesAtmosfericas, rutasVuelo);
        nuevaRutaAerea.modificar(); // Llamamos al método modificar de la clase RutaAerea

        }
    

    // Método para modificar una ruta terrestre
    private static void modificarRutaTerrestre(int idRuta) {
        System.out.println("=== Modificar ruta terrestre ===");
       

        System.out.println("Introduzca el nuevo tipo de terreno: Montaña(1), Desierto(2), Bosque(3), Carretera(4)");
        int tipoTerreno = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca las nuevas condiciones climáticas: Buenas(1): Terreno seco, Regulares(2): Terreno húmedo, Malas(3): Terreno helado");
        int condicionesAtmosfericas = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca el nuevo número de peajes: ");
        int numeroPeajes = Integer.parseInt(scanner.nextLine());

        // Creamos una nueva instancia de la clase RutaTerrestre
        rutaTerrestre nuevaRutaTerrestre = new rutaTerrestre(idRuta, 0, "", "", null, null, 0, tipoTerreno, condicionesAtmosfericas, numeroPeajes);
        nuevaRutaTerrestre.modificar(); // Llamamos al método modificar de la clase RutaTerrestre

    }

    // Método para modificar una ruta marítima
    private static void modificarRutaMaritima(int idRuta) {
        System.out.println("=== Modificar ruta marítima ===");
        

        System.out.println("Introduzca la nueva profundidad del agua: Baja(1): Hasta 1000 metros, Media(2): Entre 1000 y 2000 metros, Alta(3): Más de 2000 metros ");
        int profundidadAgua = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca las nuevas condiciones del mar: Buenas(1): Mar en calma, Regulares(2): Mar con oleaje, Malas(3): Mar con oleaje y tormenta, Extremas(4): Mar con oleaje, tormenta y marea roja");
        int condicionesMar = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduzca los nuevos puertos intermedios: ");
        String puertosIntermedios = scanner.nextLine();

        // Creamos una nueva instancia de la clase RutaMaritima
        rutaMaritima nuevaRutaMaritima = new rutaMaritima(idRuta,0, "", "", null, null, 0, profundidadAgua, condicionesMar, puertosIntermedios);
        nuevaRutaMaritima.modificar(); // Llamamos al método modificar de la clase RutaMaritima

    }


    // Método para eliminar una ruta
    private static void eliminarRuta() {
        System.out.println("=== Eliminar ruta ===");
        System.out.println("Introduzca el ID de la ruta a eliminar: ");
        int idRuta = Integer.parseInt(scanner.nextLine());

        // Creamos una nueva instancia de la clase Ruta
        Ruta nuevaRuta = new Ruta(idRuta, 0, "", "", null, null, 0);
        nuevaRuta.eliminar(); // Llamamos al método eliminar de la clase Ruta

        // Creamos una nueva instancia de la clase RutaAerea
        rutaAerea nuevaRutaAerea = new rutaAerea(idRuta, 0, "", "", null, null, 0, 0, 0, null);
        nuevaRutaAerea.eliminar(); // Llamamos al método eliminar de la clase RutaAerea

        // Creamos una nueva instancia de la clase RutaTerrestre
        rutaTerrestre nuevaRutaTerrestre = new rutaTerrestre(idRuta, 0, "", "", null, null, 0, 0, 0, 0);
        nuevaRutaTerrestre.eliminar(); // Llamamos al método eliminar de la clase RutaTerrestre

        // Creamos una nueva instancia de la clase RutaMaritima
        rutaMaritima nuevaRutaMaritima = new rutaMaritima(idRuta, 0, "", "", null, null, 0, 0, 0, "");
        nuevaRutaMaritima.eliminar(); // Llamamos al método eliminar de la clase RutaMaritima
    }
    
}
