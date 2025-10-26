package modelo;

import java.time.LocalDate;

/**
 * Registro de eventos relevantes asociados al cliente.
 */
public class HistorialCliente {

    private int idHistorial;
    private int clienteId;
    private String descripcion;
    private LocalDate fechaRegistro;
    private String tipoEvento;

    public HistorialCliente() {
    }

    public HistorialCliente(int idHistorial, int clienteId, String descripcion,
            LocalDate fechaRegistro, String tipoEvento) {
        this.idHistorial = idHistorial;
        this.clienteId = clienteId;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.tipoEvento = tipoEvento;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}
