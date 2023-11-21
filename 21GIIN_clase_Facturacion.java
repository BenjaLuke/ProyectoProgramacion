// 21GIIN - Proyectos de Programación - Clase Facturacion
// Proyecto: Gestión logística Portuaria
// Autores: Luis Valbuena - Benjamín Miguel

// Definimos la clase Facturacion
public class Facturacion {
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
    public Facturacion(String codigo, String fecha, String hora, String tipo, String matricula, String nombre, String dni, String concepto, double importe, String estado) {
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

    // Definimos los getters y setters de la clase Facturacion
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
    
    
