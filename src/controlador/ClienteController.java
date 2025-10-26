package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 * Controlador responsable de las operaciones CRUD para los clientes. Utiliza
 * {@link Conexion} para comunicarse con la base de datos.
 */
public class ClienteController {

    private final Conexion conexion;

    public ClienteController(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Registra un cliente y su usuario asociado en la base de datos.
     *
     * @param cliente modelo con los datos a persistir
     * @return {@code true} si el proceso finalizó correctamente
     */
    public boolean crearCliente(Cliente cliente) {
        String sqlUsuario = "INSERT INTO USUARIOS(username, password, rol_id) VALUES (?, ?, ?)";
        String sqlCliente = "INSERT INTO CLIENTES_INFO(id_usuario, nombres, apellidos, dni, telefono, email, direccion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = conexion.conectar();
            conn.setAutoCommit(false);

            try (PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                psUsuario.setString(1, cliente.getUsername());
                psUsuario.setString(2, cliente.getPassword());
                psUsuario.setInt(3, cliente.getRolId());
                psUsuario.executeUpdate();

                try (ResultSet rs = psUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idUsuario = rs.getInt(1);
                        cliente.setIdUsuario(idUsuario);

                        try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
                            psCliente.setInt(1, idUsuario);
                            psCliente.setString(2, cliente.getNombres());
                            psCliente.setString(3, cliente.getApellidos());
                            psCliente.setString(4, cliente.getDni());
                            psCliente.setString(5, cliente.getTelefono());
                            psCliente.setString(6, cliente.getEmail());
                            psCliente.setString(7, cliente.getDireccion());
                            psCliente.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
            return true;
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Recupera todos los clientes registrados junto con la información básica
     * de su usuario.
     */
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.id_cliente, u.id_usuario, u.username, u.password, u.rol_id, "
                + "c.nombres, c.apellidos, c.dni, c.telefono, c.email, c.direccion "
                + "FROM CLIENTES_INFO c INNER JOIN USUARIOS u ON c.id_usuario = u.id_usuario";

        try (Connection conn = conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("rol_id"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    /**
     * Actualiza la información personal y de autenticación del cliente.
     */
    public boolean actualizarCliente(Cliente cliente) {
        String sqlUsuario = "UPDATE USUARIOS SET username = ?, password = ?, rol_id = ? WHERE id_usuario = ?";
        String sqlCliente = "UPDATE CLIENTES_INFO SET nombres = ?, apellidos = ?, dni = ?, telefono = ?, email = ?, direccion = ? WHERE id_cliente = ?";

        Connection conn = null;
        try {
            conn = conexion.conectar();
            conn.setAutoCommit(false);

            try (PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario)) {
                psUsuario.setString(1, cliente.getUsername());
                psUsuario.setString(2, cliente.getPassword());
                psUsuario.setInt(3, cliente.getRolId());
                psUsuario.setInt(4, cliente.getIdUsuario());
                psUsuario.executeUpdate();
            }

            try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
                psCliente.setString(1, cliente.getNombres());
                psCliente.setString(2, cliente.getApellidos());
                psCliente.setString(3, cliente.getDni());
                psCliente.setString(4, cliente.getTelefono());
                psCliente.setString(5, cliente.getEmail());
                psCliente.setString(6, cliente.getDireccion());
                psCliente.setInt(7, cliente.getIdCliente());
                psCliente.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Elimina al cliente y su usuario relacionado.
     */
    public boolean eliminarCliente(int idCliente, int idUsuario) {
        String sqlCliente = "DELETE FROM CLIENTES_INFO WHERE id_cliente = ?";
        String sqlUsuario = "DELETE FROM USUARIOS WHERE id_usuario = ?";

        Connection conn = null;
        try {
            conn = conexion.conectar();
            conn.setAutoCommit(false);

            try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, idCliente);
                psCliente.executeUpdate();
            }

            try (PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario)) {
                psUsuario.setInt(1, idUsuario);
                psUsuario.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
