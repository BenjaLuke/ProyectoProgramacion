import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Clase para manejar la conexion con la base de datos SQLite
public class SQLiteManager {
    private static Connection connection; // variable para establecer la conexion con la base de datos
    
    public SQLiteManager(String dbFilePath){
        try{
            Class.forName("org.sqlite.JDBC"); // carga el driver de SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath); // establece la conexion con la base de datos
            System.out.println("Conexion establecida con la base de datos: " + dbFilePath); // imprime un mensaje de conexion establecida
        }catch(ClassNotFoundException | SQLException e){ // captura las excepciones
            e.printStackTrace(); // imprime la excepcion
        }
    }

    public Connection getConnection(){ // metodo para obtener la conexion con la base de datos
        return connection;
    }
    
    public void closeConnection(){ // metodo para cerrar la conexion con la base de datos
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de usuarios
    public void createTableUsuarios(){
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, apellido TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, tipo TEXT NOT NULL)";
        try{
            connection.createStatement().execute(sql);
            System.out.println("Tabla usuarios creada");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de facturación
    public void createTableFacturacion(){
        String sql = "CREATE TABLE IF NOT EXISTS facturacion (id_factura INTEGER PRIMARY KEY AUTOINCREMENT, fecha DATE NOT NULL, hora TIME NOT NULL, tipo TEXT NOT NULL, matricula TEXT NOT NULL, nombre TEXT NOT NULL, dni TEXT NOT NULL, concepto TEXT NOT NULL, importe REAL NOT NULL, estado TEXT NOT NULL)";
        try{
            connection.createStatement().execute(sql);
            System.out.println("Tabla facturacion creada");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de empaquetado
    public void createTableEmpaquetado(){
        String sql = "CREATE TABLE IF NOT EXISTS empaquetado (id_empaquetado INTEGER PRIMARY KEY AUTOINCREMENT, numero_contenedores INTEGER NOT NULL, peso REAL NOT NULL, tamano REAL NOT NULL)";
        try{
            connection.createStatement().execute(sql);
            System.out.println("Tabla empaquetado creada");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de rutas
    public void createTableRuta() {
        String sql = "CREATE TABLE IF NOT EXISTS Ruta (idRuta INTEGER PRIMARY KEY AUTOINCREMENT, tipoRuta TEXT, puertoOrigen TEXT, puertoDestino TEXT, fechaSalida DATE, fechaLlegada DATE, costeRuta INTEGER)";
        try {
            connection.createStatement().execute(sql);
            System.out.println("Tabla Ruta creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de rutas aéreas
    public void createTableRutaAerea() {
        String sql = "CREATE TABLE IF NOT EXISTS RutaAerea (idRuta INTEGER PRIMARY KEY AUTOINCREMENT, altitudVuelo REAL, condicionesAtmosfericas TEXT, rutasVuelo TEXT)";
        try {
            connection.createStatement().execute(sql);
            System.out.println("Tabla RutaAerea creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de rutas terrestres
    public void createTableRutaTerrestre() {
        String sql = "CREATE TABLE IF NOT EXISTS RutaTerrestre (idRuta INTEGER PRIMARY KEY AUTOINCREMENT, tipoTerreno TEXT, condicionesClimaticas TEXT, numeroPeajes INTEGER)";
        try {
            connection.createStatement().execute(sql);
            System.out.println("Tabla RutaTerrestre creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear la tabla de rutas marítimas
    public void createTableRutaMaritima() {
        String sql = "CREATE TABLE IF NOT EXISTS RutaMaritima (idRuta INTEGER PRIMARY KEY AUTOINCREMENT, profundidadAgua INTEGER, condicionesMar TEXT, puertosIntermedios TEXT)";
        try {
            connection.createStatement().execute(sql);
            System.out.println("Tabla RutaMaritima creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar si existe idRuta en tabla ruta aérea
    // public boolean existeIdRuta(int idRuta) {

    //     boolean existe = false; // Variable para verificar si existe el id de la ruta

    //     String sql = "SELECT COUNT (*) FROM RutaAerea WHERE idRuta = ?"; // Definimos la consulta SQL para verificar si existe el id de la ruta

    //     try (PreparedStatement pstmt = connection.prepareStatement(sql)) { // Creamos el PreparedStatement para verificar si existe el id de la ruta
    //         pstmt.setInt(1, idRuta); // Asignamos el id de la ruta
    //         ResultSet rs = pstmt.executeQuery(); // Ejecutamos la consulta
    //         int count = rs.getInt("count"); // Obtenemos el valor del contador
    //         if (count > 0) { // Verificamos si existe el id de la ruta
    //             existe = true; // Asignamos true a la variable existe
    //         }
    //     } catch (SQLException e) {
    //         System.out.println("Error al verificar si existe el id de la ruta " + e.getMessage());
    //     }
    //     return existe; // Retornamos la variable existe
    // }

   
}