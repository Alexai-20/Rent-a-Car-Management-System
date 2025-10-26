package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Reserva;

/**
 * Controlador para las operaciones CRUD de la entidad {@link Reserva}.
 */
public class ReservaController {

    private final Conexion conexion;

    public ReservaController(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean crearReserva(Reserva reserva) {
        String sql = "INSERT INTO RESERVAS(cliente_id, vehiculo_id, fecha_reserva, fecha_inicio, fecha_fin, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getClienteId());
            ps.setInt(2, reserva.getVehiculoId());
            ps.setDate(3, Date.valueOf(reserva.getFechaReserva()));
            ps.setDate(4, Date.valueOf(reserva.getFechaInicio()));
            ps.setDate(5, Date.valueOf(reserva.getFechaFin()));
            ps.setString(6, reserva.getEstado());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM RESERVAS";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id_reserva"),
                        rs.getInt("cliente_id"),
                        rs.getInt("vehiculo_id"),
                        rs.getDate("fecha_reserva").toLocalDate(),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getDate("fecha_fin").toLocalDate(),
                        rs.getString("estado")
                );
                reservas.add(reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservas;
    }

    public boolean actualizarReserva(Reserva reserva) {
        String sql = "UPDATE RESERVAS SET cliente_id = ?, vehiculo_id = ?, fecha_reserva = ?, fecha_inicio = ?, fecha_fin = ?, estado = ? "
                + "WHERE id_reserva = ?";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getClienteId());
            ps.setInt(2, reserva.getVehiculoId());
            ps.setDate(3, Date.valueOf(reserva.getFechaReserva()));
            ps.setDate(4, Date.valueOf(reserva.getFechaInicio()));
            ps.setDate(5, Date.valueOf(reserva.getFechaFin()));
            ps.setString(6, reserva.getEstado());
            ps.setInt(7, reserva.getIdReserva());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarReserva(int idReserva) {
        String sql = "DELETE FROM RESERVAS WHERE id_reserva = ?";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
