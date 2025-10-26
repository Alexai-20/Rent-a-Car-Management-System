package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Factura generada para un alquiler.
 */
public class Factura {

    private int idFactura;
    private int alquilerId;
    private LocalDate fechaEmision;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String estadoPago;

    public Factura() {
    }

    public Factura(int idFactura, int alquilerId, LocalDate fechaEmision,
            BigDecimal subtotal, BigDecimal impuestos, BigDecimal total,
            String estadoPago) {
        this.idFactura = idFactura;
        this.alquilerId = alquilerId;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
        this.estadoPago = estadoPago;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getAlquilerId() {
        return alquilerId;
    }

    public void setAlquilerId(int alquilerId) {
        this.alquilerId = alquilerId;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
