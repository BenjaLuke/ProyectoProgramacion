// 21GIIN - Proyectos de Programación - Clase Empaquetado
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Importamos las librerías necesarias
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

//import javax.swing.text.html.HTMLDocument.Iterator;

// Definimos la clase Usuario
public class Usuario implements GestorDatos{
    
    // Definimos la lista de usuarios
    private static List<Usuario> listaUsuarios = new ArrayList<>();

    // Definimos el contador de ids
    //private static int contadorIds = 0; // Variable estática para generar IDs únicos

    // Atributos
    private int id;
    private String nombre;
    private String clave;
    private String tipoUsuario;
    private String telefono;
    private String email;

    // Constructor
    public Usuario(int id, String nombre, String clave, String tipoUsuario, String telefono, String email) {
        this.id = id; // Asignamos el id y luego incrementamos el contador
        this.nombre = nombre;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.email = email;
    }


    // Implementamos los métodos de la interface GestorDatos
    @Override
    public void crear() {
        listaUsuarios.add(this); // Agregamos el usuario a la lista de usuarios
        System.out.println("Usuario creado con éxito: " + nombre); // Mostramos mensaje de éxito
    }   

    @Override
    public void modificar() {
        for (Usuario usuario : listaUsuarios) { // Recorremos la lista de usuarios
            if (usuario.id == this.id) { // Si el id del usuario es igual al id del usuario actual
                usuario.nombre = this.nombre; // Modificamos el nombre del usuario
                usuario.clave = this.clave; // Modificamos la clave del usuario
                usuario.tipoUsuario = this.tipoUsuario; // Modificamos el tipo de usuario
                usuario.telefono = this.telefono; // Modificamos el teléfono del usuario
                usuario.email = this.email; // Modificamos el email del usuario
                System.out.println("Usuario modificado con éxito: " + usuario.nombre); // Mostramos mensaje de éxito
                return; // Salimos del método
            }
        }
        System.out.println("No se encontró el usuario con id: " + this.id); // Mostramos mensaje de error
    }

    @Override
    public void eliminar() {
        System.out.println("Intentando eliminar usuario con ID: " + this.id);
        for (Usuario usuario : listaUsuarios) {
            System.out.println("ID del usuario en la lista: " + usuario.id);
            if (usuario.id == this.id) {
                listaUsuarios.remove(usuario);
                System.out.println("Usuario eliminado exitosamente: " + usuario.nombre);
                return;
            }
        }
        System.out.println("Usuario no encontrado para eliminar");
    }


    @Override
    public String leer() {
        StringBuilder usuarios = new StringBuilder(); // Creamos un StringBuilder para almacenar los usuarios de la lista
        for (Usuario usuario : listaUsuarios) { // Recorremos la lista de usuarios
            usuarios.append("ID: ").append(usuario.id).append(", Nombre: ").append(usuario.nombre)
                    .append(", Clave: ").append(usuario.clave).append(", Tipo de usuario: ").append(usuario.tipoUsuario)
                    .append(", Teléfono: ").append(usuario.telefono).append(", Email: ").append(usuario.email)
                    .append("\n"); // Agregamos los datos del usuario al StringBuilder
               
        }
        return usuarios.toString(); // Retornamos los usuarios
    }

}
    



