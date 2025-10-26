package modelo;

/**
 * Roles disponibles para la gestión de permisos dentro del sistema.
 */
public class Rol {

    private int idRol;
    private String nombre;
    private String descripcion;
    private String nivelAcceso;

    public Rol() {
    }

    public Rol(int idRol, String nombre, String descripcion, String nivelAcceso) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelAcceso = nivelAcceso;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}
