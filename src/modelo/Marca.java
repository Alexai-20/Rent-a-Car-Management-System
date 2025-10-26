package modelo;

/**
 * Catálogo de marcas registradas para los vehículos.
 */
public class Marca {

    private int idMarca;
    private String nombre;
    private String paisOrigen;

    public Marca() {
    }

    public Marca(int idMarca, String nombre, String paisOrigen) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
}
