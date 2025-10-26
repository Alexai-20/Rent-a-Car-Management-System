package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Trabajador asignado al servicio de renta. Amplía la definición de
 * {@link Usuario} incorporando los campos propios de la tabla
 * TRABAJADORES_INFO.
 */
public class Trabajador extends Usuario {

    private int idTrabajador;
    private String nombres;
    private String apellidos;
    private String cargo;
    private String telefono;
    private String email;
    private LocalDate fechaContratacion;
    private BigDecimal salarioMensual;

    public Trabajador() {
    }

    public Trabajador(int idUsuario, String username, String password, int rolId,
            boolean activo, LocalDateTime fechaCreacion, int idTrabajador,
            String nombres, String apellidos, String cargo, String telefono,
            String email, LocalDate fechaContratacion, BigDecimal salarioMensual) {
        super(idUsuario, username, password, rolId, activo, fechaCreacion);
        this.idTrabajador = idTrabajador;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.salarioMensual = salarioMensual;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public BigDecimal getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(BigDecimal salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    @Override
    public String resumenPerfil() {
        return String.format("Trabajador %s %s - %s", nombres, apellidos, cargo);
    }
}
