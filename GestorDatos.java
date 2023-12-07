// 21GIIN - Proyectos de Programación - Clase Ruta
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Creamos interface GestorDatos
// A través de la interfaz GestorDatos podremos obtener datos y configurarlos
// en las clases que la implementen. Es decir, nos permitirá crear, modificar y
// eliminar y obtener datos de las clases que la implementen.
public interface GestorDatos {
    
    String getDatos();
    void setDatos(String datos);
    
}