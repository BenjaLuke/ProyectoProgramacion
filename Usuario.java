// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Usuario
public class Usuario implements GestorDatos{
    
    private int id;
    private String nombre;
    private String clave;
    private String tipoUsuario;
    private String telefono;
    private String email;

    // Constructor
    public Usuario(int id, String nombre, String clave, String tipoUsuario, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.email = email;
    }


    // Implementamos los métodos de la interface GestorDatos
    @Override
    public void crear() {
        // Lógica para crear un usuario
    }

    @Override
    public void modificar() {
        // Lógica para modificar un usuario
    }

    @Override
    public void eliminar() {
        // Lógica para eliminar un usuario
    }

    @Override
    public String leer() {
        // Lógica para leer (obtener datos) de un usuario
        return null; // Retorna los datos del usuario
    }
    
}
