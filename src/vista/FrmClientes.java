package vista;

import conexion.Conexion;
import controlador.ClienteController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;

/**
 * Ventana para la administración de clientes. Permite ejecutar operaciones
 * CRUD de forma visual interactuando con {@link ClienteController}.
 */
public class FrmClientes extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final ClienteController controller;
    private final DefaultTableModel modeloTabla;

    private final JTextField txtIdCliente = new JTextField();
    private final JTextField txtIdUsuario = new JTextField();
    private final JTextField txtNombres = new JTextField();
    private final JTextField txtApellidos = new JTextField();
    private final JTextField txtDni = new JTextField();
    private final JTextField txtTelefono = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JTextField txtDireccion = new JTextField();
    private final JTextField txtUsername = new JTextField();
    private final JTextField txtPassword = new JTextField();
    private final JTextField txtRolId = new JTextField("2");

    private final JTable tblClientes = new JTable();

    public FrmClientes(Conexion conexion) {
        this.controller = new ClienteController(conexion);
        this.modeloTabla = new DefaultTableModel(
                new Object[]{"ID Cliente", "ID Usuario", "Nombre", "Apellido", "DNI", "Teléfono", "Email", "Dirección", "Usuario"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initComponents();
        cargarClientes();
    }

    private void initComponents() {
        setTitle("Gestión de Clientes");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        panelFormulario.add(new JLabel("ID Cliente:"));
        txtIdCliente.setEditable(false);
        panelFormulario.add(txtIdCliente);

        panelFormulario.add(new JLabel("ID Usuario:"));
        txtIdUsuario.setEditable(false);
        panelFormulario.add(txtIdUsuario);

        panelFormulario.add(new JLabel("Nombres:"));
        panelFormulario.add(txtNombres);

        panelFormulario.add(new JLabel("Apellidos:"));
        panelFormulario.add(txtApellidos);

        panelFormulario.add(new JLabel("DNI:"));
        panelFormulario.add(txtDni);

        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(txtEmail);

        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Usuario:"));
        panelFormulario.add(txtUsername);

        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(txtPassword);

        panelFormulario.add(new JLabel("ID Rol:"));
        panelFormulario.add(txtRolId);

        add(panelFormulario, BorderLayout.NORTH);

        tblClientes.setModel(modeloTabla);
        tblClientes.setPreferredScrollableViewportSize(new Dimension(600, 250));
        tblClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarClienteSeleccionado();
            }
        });
        add(new JScrollPane(tblClientes), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnGuardar.addActionListener(e -> guardarCliente());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarClientes() {
        modeloTabla.setRowCount(0);
        List<Cliente> clientes = controller.obtenerClientes();
        for (Cliente c : clientes) {
            modeloTabla.addRow(new Object[]{
                c.getIdCliente(),
                c.getIdUsuario(),
                c.getNombres(),
                c.getApellidos(),
                c.getDni(),
                c.getTelefono(),
                c.getEmail(),
                c.getDireccion(),
                c.getUsername()
            });
        }
    }

    private void cargarClienteSeleccionado() {
        int fila = tblClientes.getSelectedRow();
        if (fila >= 0) {
            txtIdCliente.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtIdUsuario.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtNombres.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtApellidos.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtDni.setText(modeloTabla.getValueAt(fila, 4).toString());
            txtTelefono.setText(modeloTabla.getValueAt(fila, 5).toString());
            txtEmail.setText(modeloTabla.getValueAt(fila, 6).toString());
            txtDireccion.setText(modeloTabla.getValueAt(fila, 7).toString());
            txtUsername.setText(modeloTabla.getValueAt(fila, 8).toString());
        }
    }

    private void guardarCliente() {
        try {
            Cliente cliente = new Cliente();
            cliente.setUsername(txtUsername.getText());
            cliente.setPassword(txtPassword.getText());
            cliente.setRolId(Integer.parseInt(txtRolId.getText()));
            cliente.setNombres(txtNombres.getText());
            cliente.setApellidos(txtApellidos.getText());
            cliente.setDni(txtDni.getText());
            cliente.setTelefono(txtTelefono.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setDireccion(txtDireccion.getText());

            if (controller.crearCliente(cliente)) {
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente");
                cargarClientes();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible registrar al cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID de rol debe ser numérico", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarCliente() {
        int fila = tblClientes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
            cliente.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
            cliente.setUsername(txtUsername.getText());
            cliente.setPassword(txtPassword.getText());
            cliente.setRolId(Integer.parseInt(txtRolId.getText()));
            cliente.setNombres(txtNombres.getText());
            cliente.setApellidos(txtApellidos.getText());
            cliente.setDni(txtDni.getText());
            cliente.setTelefono(txtTelefono.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setDireccion(txtDireccion.getText());

            if (controller.actualizarCliente(cliente)) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
                cargarClientes();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible actualizar al cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los identificadores deben ser numéricos", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarCliente() {
        int fila = tblClientes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar al cliente seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            int idUsuario = Integer.parseInt(txtIdUsuario.getText());
            if (controller.eliminarCliente(idCliente, idUsuario)) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado");
                cargarClientes();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible eliminar al cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtIdCliente.setText("");
        txtIdUsuario.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRolId.setText("2");
        tblClientes.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmClientes frame = new FrmClientes(new Conexion());
            frame.setVisible(true);
        });
    }
}
