package vista;

import conexion.Conexion;
import controlador.ReservaController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
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
import modelo.Reserva;

/**
 * Ventana para el mantenimiento de reservas realizadas por los clientes.
 */
public class FrmReservas extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final ReservaController controller;
    private final DefaultTableModel modeloTabla;

    private final JTextField txtIdReserva = new JTextField();
    private final JTextField txtClienteId = new JTextField();
    private final JTextField txtVehiculoId = new JTextField();
    private final JTextField txtFechaReserva = new JTextField();
    private final JTextField txtFechaInicio = new JTextField();
    private final JTextField txtFechaFin = new JTextField();
    private final JTextField txtEstado = new JTextField();

    private final JTable tblReservas = new JTable();

    public FrmReservas(Conexion conexion) {
        this.controller = new ReservaController(conexion);
        this.modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Vehículo", "Reserva", "Inicio", "Fin", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initComponents();
        cargarReservas();
    }

    private void initComponents() {
        setTitle("Gestión de Reservas");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        panelFormulario.add(new JLabel("ID Reserva:"));
        txtIdReserva.setEditable(false);
        panelFormulario.add(txtIdReserva);

        panelFormulario.add(new JLabel("ID Cliente:"));
        panelFormulario.add(txtClienteId);

        panelFormulario.add(new JLabel("ID Vehículo:"));
        panelFormulario.add(txtVehiculoId);

        panelFormulario.add(new JLabel("Fecha Reserva (YYYY-MM-DD):"));
        panelFormulario.add(txtFechaReserva);

        panelFormulario.add(new JLabel("Fecha Inicio (YYYY-MM-DD):"));
        panelFormulario.add(txtFechaInicio);

        panelFormulario.add(new JLabel("Fecha Fin (YYYY-MM-DD):"));
        panelFormulario.add(txtFechaFin);

        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(txtEstado);

        add(panelFormulario, BorderLayout.NORTH);

        tblReservas.setModel(modeloTabla);
        tblReservas.setPreferredScrollableViewportSize(new Dimension(600, 250));
        tblReservas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarReservaSeleccionada();
            }
        });
        add(new JScrollPane(tblReservas), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnGuardar.addActionListener(e -> guardarReserva());
        btnActualizar.addActionListener(e -> actualizarReserva());
        btnEliminar.addActionListener(e -> eliminarReserva());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarReservas() {
        modeloTabla.setRowCount(0);
        List<Reserva> reservas = controller.obtenerReservas();
        for (Reserva r : reservas) {
            modeloTabla.addRow(new Object[]{
                r.getIdReserva(),
                r.getClienteId(),
                r.getVehiculoId(),
                r.getFechaReserva(),
                r.getFechaInicio(),
                r.getFechaFin(),
                r.getEstado()
            });
        }
    }

    private void cargarReservaSeleccionada() {
        int fila = tblReservas.getSelectedRow();
        if (fila >= 0) {
            txtIdReserva.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtClienteId.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtVehiculoId.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtFechaReserva.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtFechaInicio.setText(modeloTabla.getValueAt(fila, 4).toString());
            txtFechaFin.setText(modeloTabla.getValueAt(fila, 5).toString());
            txtEstado.setText(modeloTabla.getValueAt(fila, 6).toString());
        }
    }

    private void guardarReserva() {
        try {
            Reserva reserva = new Reserva();
            reserva.setClienteId(Integer.parseInt(txtClienteId.getText()));
            reserva.setVehiculoId(Integer.parseInt(txtVehiculoId.getText()));
            reserva.setFechaReserva(LocalDate.parse(txtFechaReserva.getText()));
            reserva.setFechaInicio(LocalDate.parse(txtFechaInicio.getText()));
            reserva.setFechaFin(LocalDate.parse(txtFechaFin.getText()));
            reserva.setEstado(txtEstado.getText());

            if (controller.crearReserva(reserva)) {
                JOptionPane.showMessageDialog(this, "Reserva registrada correctamente");
                cargarReservas();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible registrar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Verifique los datos ingresados. Formato de fechas: YYYY-MM-DD", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarReserva() {
        int fila = tblReservas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(Integer.parseInt(txtIdReserva.getText()));
            reserva.setClienteId(Integer.parseInt(txtClienteId.getText()));
            reserva.setVehiculoId(Integer.parseInt(txtVehiculoId.getText()));
            reserva.setFechaReserva(LocalDate.parse(txtFechaReserva.getText()));
            reserva.setFechaInicio(LocalDate.parse(txtFechaInicio.getText()));
            reserva.setFechaFin(LocalDate.parse(txtFechaFin.getText()));
            reserva.setEstado(txtEstado.getText());

            if (controller.actualizarReserva(reserva)) {
                JOptionPane.showMessageDialog(this, "Reserva actualizada correctamente");
                cargarReservas();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible actualizar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Verifique los datos ingresados. Formato de fechas: YYYY-MM-DD", "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarReserva() {
        int fila = tblReservas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar la reserva seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idReserva = Integer.parseInt(txtIdReserva.getText());
            if (controller.eliminarReserva(idReserva)) {
                JOptionPane.showMessageDialog(this, "Reserva eliminada");
                cargarReservas();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible eliminar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtIdReserva.setText("");
        txtClienteId.setText("");
        txtVehiculoId.setText("");
        txtFechaReserva.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtEstado.setText("");
        tblReservas.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmReservas frame = new FrmReservas(new Conexion());
            frame.setVisible(true);
        });
    }
}
