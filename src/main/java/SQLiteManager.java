import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase para manejar la conexion con la base de datos SQLite
public class SQLiteManager {
    private static Connection connection; // variable para establecer la conexion con la base de datos
    
    public SQLiteManager(String dbFilePath){
        try{
            Class.forName("org.sqlite.JDBC"); // carga el driver de SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath); // establece la conexion con la base de datos
            System.out.println("Conexion establecida"); // imprime un mensaje de conexion establecida
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
        String sql = "CREATE TABLE IF NOT EXISTS facturacion (id_facturacion INTEGER PRIMARY KEY AUTOINCREMENT, fecha DATE NOT NULL, hora TIME NOT NULL, tipo TEXT NOT NULL, matricula TEXT NOT NULL, nombre TEXT NOT NULL, dni TEXT NOT NULL, concepto TEXT NOT NULL, importe REAL NOT NULL, estado TEXT NOT NULL)";
        try{
            connection.createStatement().execute(sql);
            System.out.println("Tabla facturacion creada");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
   
}