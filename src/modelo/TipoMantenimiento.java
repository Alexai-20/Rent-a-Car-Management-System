package modelo;

/**
 * Clasificación de los mantenimientos ejecutados sobre los vehículos.
 */
public class TipoMantenimiento {

    private int idTipo;
    private String nombre;
    private String descripcion;
    private int frecuenciaDias;

    public TipoMantenimiento() {
    }

    public TipoMantenimiento(int idTipo, String nombre, String descripcion,
            int frecuenciaDias) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frecuenciaDias = frecuenciaDias;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
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

    public int getFrecuenciaDias() {
        return frecuenciaDias;
    }

    public void setFrecuenciaDias(int frecuenciaDias) {
        this.frecuenciaDias = frecuenciaDias;
    }
}
