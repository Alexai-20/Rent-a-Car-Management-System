package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Historial de mantenimientos asociados a un vehículo específico.
 */
public class MantenimientoVehiculo {

    private int idMantenimiento;
    private int vehiculoId;
    private int tipoId;
    private String descripcion;
    private BigDecimal costo;
    private LocalDate fechaMantenimiento;
    private String responsable;

    public MantenimientoVehiculo() {
    }

    public MantenimientoVehiculo(int idMantenimiento, int vehiculoId, int tipoId,
            String descripcion, BigDecimal costo, LocalDate fechaMantenimiento,
            String responsable) {
        this.idMantenimiento = idMantenimiento;
        this.vehiculoId = vehiculoId;
        this.tipoId = tipoId;
        this.descripcion = descripcion;
        this.costo = costo;
        this.fechaMantenimiento = fechaMantenimiento;
        this.responsable = responsable;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
