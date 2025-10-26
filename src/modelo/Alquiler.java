package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa el alquiler activo asociado a una reserva confirmada.
 */
public class Alquiler {

    private int idAlquiler;
    private int reservaId;
    private int trabajadorId;
    private LocalDate fechaEntrega;
    private LocalDate fechaDevolucion;
    private BigDecimal montoTotal;
    private double kilometrajeInicial;
    private double kilometrajeFinal;

    public Alquiler() {
    }

    public Alquiler(int idAlquiler, int reservaId, int trabajadorId,
            LocalDate fechaEntrega, LocalDate fechaDevolucion,
            BigDecimal montoTotal, double kilometrajeInicial,
            double kilometrajeFinal) {
        this.idAlquiler = idAlquiler;
        this.reservaId = reservaId;
        this.trabajadorId = trabajadorId;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.montoTotal = montoTotal;
        this.kilometrajeInicial = kilometrajeInicial;
        this.kilometrajeFinal = kilometrajeFinal;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getKilometrajeInicial() {
        return kilometrajeInicial;
    }

    public void setKilometrajeInicial(double kilometrajeInicial) {
        this.kilometrajeInicial = kilometrajeInicial;
    }

    public double getKilometrajeFinal() {
        return kilometrajeFinal;
    }

    public void setKilometrajeFinal(double kilometrajeFinal) {
        this.kilometrajeFinal = kilometrajeFinal;
    }
}
