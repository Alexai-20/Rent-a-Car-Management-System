package vista;

import conexion.Conexion;
import controlador.VehiculoController;
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
import modelo.Vehiculo;

/**
 * Ventana para la gestión de vehículos disponibles en la flota.
 */
public class FrmVehiculos extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final VehiculoController controller;
    private final DefaultTableModel modeloTabla;

    private final JTextField txtIdVehiculo = new JTextField();
    private final JTextField txtModeloId = new JTextField();
    private final JTextField txtMarcaId = new JTextField();
    private final JTextField txtAnio = new JTextField();
    private final JTextField txtColor = new JTextField();
    private final JTextField txtPlaca = new JTextField();
    private final JTextField txtKilometraje = new JTextField();
    private final JTextField txtEstado = new JTextField();

    private final JTable tblVehiculos = new JTable();

    public FrmVehiculos(Conexion conexion) {
        this.controller = new VehiculoController(conexion);
        this.modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Modelo", "Marca", "Año", "Color", "Placa", "Kilometraje", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initComponents();
        cargarVehiculos();
    }

    private void initComponents() {
        setTitle("Gestión de Vehículos");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        panelFormulario.add(new JLabel("ID Vehículo:"));
        txtIdVehiculo.setEditable(false);
        panelFormulario.add(txtIdVehiculo);

        panelFormulario.add(new JLabel("ID Modelo:"));
        panelFormulario.add(txtModeloId);

        panelFormulario.add(new JLabel("ID Marca:"));
        panelFormulario.add(txtMarcaId);

        panelFormulario.add(new JLabel("Año:"));
        panelFormulario.add(txtAnio);

        panelFormulario.add(new JLabel("Color:"));
        panelFormulario.add(txtColor);

        panelFormulario.add(new JLabel("Número de Placa:"));
        panelFormulario.add(txtPlaca);

        panelFormulario.add(new JLabel("Kilometraje:"));
        panelFormulario.add(txtKilometraje);

        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(txtEstado);

        add(panelFormulario, BorderLayout.NORTH);

        tblVehiculos.setModel(modeloTabla);
        tblVehiculos.setPreferredScrollableViewportSize(new Dimension(600, 250));
        tblVehiculos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarVehiculoSeleccionado();
            }
        });
        add(new JScrollPane(tblVehiculos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnGuardar.addActionListener(e -> guardarVehiculo());
        btnActualizar.addActionListener(e -> actualizarVehiculo());
        btnEliminar.addActionListener(e -> eliminarVehiculo());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarVehiculos() {
        modeloTabla.setRowCount(0);
        List<Vehiculo> vehiculos = controller.obtenerVehiculos();
        for (Vehiculo v : vehiculos) {
            modeloTabla.addRow(new Object[]{
                v.getIdVehiculo(),
                v.getModeloId(),
                v.getMarcaId(),
                v.getAnio(),
                v.getColor(),
                v.getNumeroPlaca(),
                v.getKilometraje(),
                v.getEstado()
            });
        }
    }

    private void cargarVehiculoSeleccionado() {
        int fila = tblVehiculos.getSelectedRow();
        if (fila >= 0) {
            txtIdVehiculo.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtModeloId.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtMarcaId.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtAnio.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtColor.setText(modeloTabla.getValueAt(fila, 4).toString());
            txtPlaca.setText(modeloTabla.getValueAt(fila, 5).toString());
            txtKilometraje.setText(modeloTabla.getValueAt(fila, 6).toString());
            txtEstado.setText(modeloTabla.getValueAt(fila, 7).toString());
        }
    }

    private void guardarVehiculo() {
        try {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setModeloId(Integer.parseInt(txtModeloId.getText()));
            vehiculo.setMarcaId(Integer.parseInt(txtMarcaId.getText()));
            vehiculo.setAnio(Integer.parseInt(txtAnio.getText()));
            vehiculo.setColor(txtColor.getText());
            vehiculo.setNumeroPlaca(txtPlaca.getText());
            vehiculo.setKilometraje(Double.parseDouble(txtKilometraje.getText()));
            vehiculo.setEstado(txtEstado.getText());

            if (controller.crearVehiculo(vehiculo)) {
                JOptionPane.showMessageDialog(this, "Vehículo registrado correctamente");
                cargarVehiculos();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible registrar el vehículo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos numéricos deben contener valores válidos", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarVehiculo() {
        int fila = tblVehiculos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setIdVehiculo(Integer.parseInt(txtIdVehiculo.getText()));
            vehiculo.setModeloId(Integer.parseInt(txtModeloId.getText()));
            vehiculo.setMarcaId(Integer.parseInt(txtMarcaId.getText()));
            vehiculo.setAnio(Integer.parseInt(txtAnio.getText()));
            vehiculo.setColor(txtColor.getText());
            vehiculo.setNumeroPlaca(txtPlaca.getText());
            vehiculo.setKilometraje(Double.parseDouble(txtKilometraje.getText()));
            vehiculo.setEstado(txtEstado.getText());

            if (controller.actualizarVehiculo(vehiculo)) {
                JOptionPane.showMessageDialog(this, "Vehículo actualizado correctamente");
                cargarVehiculos();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible actualizar el vehículo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos numéricos deben contener valores válidos", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarVehiculo() {
        int fila = tblVehiculos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el vehículo seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idVehiculo = Integer.parseInt(txtIdVehiculo.getText());
            if (controller.eliminarVehiculo(idVehiculo)) {
                JOptionPane.showMessageDialog(this, "Vehículo eliminado");
                cargarVehiculos();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible eliminar el vehículo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtIdVehiculo.setText("");
        txtModeloId.setText("");
        txtMarcaId.setText("");
        txtAnio.setText("");
        txtColor.setText("");
        txtPlaca.setText("");
        txtKilometraje.setText("");
        txtEstado.setText("");
        tblVehiculos.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmVehiculos frame = new FrmVehiculos(new Conexion());
            frame.setVisible(true);
        });
    }
}
