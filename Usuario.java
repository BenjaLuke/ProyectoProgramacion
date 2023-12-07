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
    @Override // Sobreescribimos el método getDatos
    public String getDatos() {
        // Lógica para obtener los datos del usuario
        return "Datos del usuario{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", tipoUsuario=" + tipoUsuario + ", telefono=" + telefono + ", email=" + email + '}';
    }

    @Override // Sobreescribimos el método setDatos
    public void setDatos(String datos) {
        // Lógica para establecer los datos del usuario, ya sea crear, modificar o eliminar

    }
    
}
