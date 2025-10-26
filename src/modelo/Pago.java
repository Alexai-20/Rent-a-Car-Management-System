package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Registro de los pagos aplicados a las facturas.
 */
public class Pago {

    private int idPago;
    private int facturaId;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private String metodoPago;
    private String referenciaTransaccion;

    public Pago() {
    }

    public Pago(int idPago, int facturaId, BigDecimal monto, LocalDate fechaPago,
            String metodoPago, String referenciaTransaccion) {
        this.idPago = idPago;
        this.facturaId = facturaId;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.referenciaTransaccion = referenciaTransaccion;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getReferenciaTransaccion() {
        return referenciaTransaccion;
    }

    public void setReferenciaTransaccion(String referenciaTransaccion) {
        this.referenciaTransaccion = referenciaTransaccion;
    }
}
