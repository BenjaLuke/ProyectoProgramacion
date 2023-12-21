// 21GIIN - Proyectos de Programación - Clase Facturacion
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Facturacion
public class Facturacion extends Operacion implements GestorDatos{
    // Definimos los atributos de la clase Facturacion
    private String codigo; // Código de la factura
    private String fecha; // Fecha de la factura
    private String hora; // Hora de la factura
    private String tipo; // Tipo de factura
    private String matricula; // Matrícula del vehículo
    private String nombre; // Nombre del cliente
    private String dni; // DNI del cliente
    private String concepto; // Concepto de la factura
    private double importe; // Importe de la factura
    private String estado; // Estado de la factura

    // Definimos el constructor de la clase Facturacion
    public Facturacion (String codigo, String fecha, String hora, String tipo, String matricula, String nombre, String dni, String concepto, double importe, String estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.matricula = matricula;
        this.nombre = nombre;
        this.dni = dni;
        this.concepto = concepto;
        this.importe = importe;
        this.estado = estado;
    }

    
    // Implementación de los métodos de la interfaz GestorDatos
    @Override
    public void crear() {
        // Lógica para crear una factura
    }

    @Override
    public void modificar() {
        // Lógica para modificar una factura
    }

    @Override
    public void eliminar() {
        // Lógica para eliminar una factura
    }

    @Override
    public String leer() {
        // Lógica para leer (obtener datos) de una factura
        return null; // Retorna los datos de una factura
    }

    // Getters y setters si fueran necesarios
}
    
    
