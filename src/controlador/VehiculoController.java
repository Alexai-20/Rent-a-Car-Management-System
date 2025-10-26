package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Vehiculo;

/**
 * Gestiona las operaciones CRUD sobre la tabla VEHICULOS.
 */
public class VehiculoController {

    private final Conexion conexion;

    public VehiculoController(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean crearVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO VEHICULOS(modelo_id, marca_id, anio, color, numero_placa, kilometraje, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehiculo.getModeloId());
            ps.setInt(2, vehiculo.getMarcaId());
            ps.setInt(3, vehiculo.getAnio());
            ps.setString(4, vehiculo.getColor());
            ps.setString(5, vehiculo.getNumeroPlaca());
            ps.setDouble(6, vehiculo.getKilometraje());
            ps.setString(7, vehiculo.getEstado());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEHICULOS";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("id_vehiculo"),
                        rs.getInt("modelo_id"),
                        rs.getInt("marca_id"),
                        rs.getInt("anio"),
                        rs.getString("color"),
                        rs.getString("numero_placa"),
                        rs.getDouble("kilometraje"),
                        rs.getString("estado")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehiculos;
    }

    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE VEHICULOS SET modelo_id = ?, marca_id = ?, anio = ?, color = ?, numero_placa = ?, kilometraje = ?, estado = ? "
                + "WHERE id_vehiculo = ?";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehiculo.getModeloId());
            ps.setInt(2, vehiculo.getMarcaId());
            ps.setInt(3, vehiculo.getAnio());
            ps.setString(4, vehiculo.getColor());
            ps.setString(5, vehiculo.getNumeroPlaca());
            ps.setDouble(6, vehiculo.getKilometraje());
            ps.setString(7, vehiculo.getEstado());
            ps.setInt(8, vehiculo.getIdVehiculo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVehiculo(int idVehiculo) {
        String sql = "DELETE FROM VEHICULOS WHERE id_vehiculo = ?";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
