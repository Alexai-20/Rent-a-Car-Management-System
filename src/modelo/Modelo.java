package modelo;

/**
 * Modelo comercial de un vehículo, asociado a una {@link Marca}.
 */
public class Modelo {

    private int idModelo;
    private int marcaId;
    private String nombre;
    private String tipo;
    private int capacidadPasajeros;

    public Modelo() {
    }

    public Modelo(int idModelo, int marcaId, String nombre, String tipo,
            int capacidadPasajeros) {
        this.idModelo = idModelo;
        this.marcaId = marcaId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }
}
