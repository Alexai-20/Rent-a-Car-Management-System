package modelo;

import java.math.BigDecimal;

/**
 * Entidad que representa los vehículos disponibles en el inventario de renta.
 */
public class Vehiculo {

    private int idVehiculo;
    private int modeloId;
    private int marcaId;
    private int anio;
    private String color;
    private String numeroPlaca;
    private double kilometraje;
    private String estado;
    private String tipoCombustible;
    private BigDecimal tarifaDiaria;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, int modeloId, int marcaId, int anio,
            String color, String numeroPlaca, double kilometraje, String estado,
            String tipoCombustible, BigDecimal tarifaDiaria) {
        this.idVehiculo = idVehiculo;
        this.modeloId = modeloId;
        this.marcaId = marcaId;
        this.anio = anio;
        this.color = color;
        this.numeroPlaca = numeroPlaca;
        this.kilometraje = kilometraje;
        this.estado = estado;
        this.tipoCombustible = tipoCombustible;
        this.tarifaDiaria = tarifaDiaria;
    }

    public Vehiculo(int idVehiculo, int modeloId, int marcaId, int anio,
            String color, String numeroPlaca, double kilometraje, String estado) {
        this(idVehiculo, modeloId, marcaId, anio, color, numeroPlaca,
                kilometraje, estado, null, BigDecimal.ZERO);
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getModeloId() {
        return modeloId;
    }

    public void setModeloId(int modeloId) {
        this.modeloId = modeloId;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public BigDecimal getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setTarifaDiaria(BigDecimal tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }
}
