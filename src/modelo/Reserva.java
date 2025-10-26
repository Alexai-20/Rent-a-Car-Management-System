package modelo;

import java.time.LocalDate;

/**
 * Reserva previa a un alquiler de vehículo.
 */
public class Reserva {

    private int idReserva;
    private int clienteId;
    private int vehiculoId;
    private LocalDate fechaReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private String comentarios;

    public Reserva() {
    }

    public Reserva(int idReserva, int clienteId, int vehiculoId,
            LocalDate fechaReserva, LocalDate fechaInicio, LocalDate fechaFin,
            String estado, String comentarios) {
        this.idReserva = idReserva;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.comentarios = comentarios;
    }

    public Reserva(int idReserva, int clienteId, int vehiculoId,
            LocalDate fechaReserva, LocalDate fechaInicio, LocalDate fechaFin,
            String estado) {
        this(idReserva, clienteId, vehiculoId, fechaReserva, fechaInicio,
                fechaFin, estado, null);
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
