package modelo;

import java.time.LocalDateTime;

/**
 * Representa a los usuarios generales del sistema. Sirve como clase base para
 * especializaciones como {@link Cliente} y {@link Trabajador} con el fin de
 * aplicar herencia y polimorfismo dentro del modelo de dominio.
 */
public abstract class Usuario {

    private int idUsuario;
    private String username;
    private String password;
    private int rolId;
    private boolean activo;
    private LocalDateTime fechaCreacion;

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String password, int rolId,
            boolean activo, LocalDateTime fechaCreacion) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.rolId = rolId;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Permite que las subclases implementen una descripción detallada según sus
     * atributos particulares.
     */
    public abstract String resumenPerfil();
}
