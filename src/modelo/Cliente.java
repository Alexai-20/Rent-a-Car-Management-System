package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Cliente del sistema de renta. Extiende a {@link Usuario} para heredar los
 * atributos de autenticación y complementar con los datos personales propios de
 * la tabla CLIENTES_INFO.
 */
public class Cliente extends Usuario {

    private int idCliente;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;
    private LocalDate fechaRegistro;

    public Cliente() {
    }

    public Cliente(int idUsuario, String username, String password, int rolId,
            boolean activo, LocalDateTime fechaCreacion, int idCliente,
            String nombres, String apellidos, String dni, String telefono,
            String email, String direccion, LocalDate fechaRegistro) {
        super(idUsuario, username, password, rolId, activo, fechaCreacion);
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente(int idUsuario, String username, String password, int rolId,
            int idCliente, String nombres, String apellidos, String dni,
            String telefono, String email, String direccion) {
        this(idUsuario, username, password, rolId, true, null, idCliente,
                nombres, apellidos, dni, telefono, email, direccion, null);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String resumenPerfil() {
        return String.format("Cliente %s %s - DNI %s", nombres, apellidos, dni);
    }
}
