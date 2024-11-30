package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.Editor;
import BLL.Entrega;
import DLL.ControllerEntrega;

public class DisplayEnviarFeedback extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaEntregas;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayEnviarFeedback frame = new DisplayEnviarFeedback();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DisplayEnviarFeedback() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Enviar Feedback");
        lblTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 30));
        lblTitulo.setBounds(280, 30, 240, 40);
        contentPane.add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 100, 700, 300);
        contentPane.add(scrollPane);

        tablaEntregas = new JTable();
        tablaEntregas.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Título Libro", "Autor", "Estado", "Fecha Entrega" }
        ));
        scrollPane.setViewportView(tablaEntregas);

        JButton btnEnviarFeedback = new JButton("Enviar Feedback");
        btnEnviarFeedback.setFont(new Font("Perpetua", Font.PLAIN, 20));
        btnEnviarFeedback.setBounds(300, 450, 200, 40);
        contentPane.add(btnEnviarFeedback);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(30, 30, 90, 30);
        contentPane.add(btnVolver);

        // Acción para volver al menú anterior
        JButton buttonVolver = new JButton("");
        buttonVolver.setBackground(new Color(255, 255, 255));
        buttonVolver.setIcon(new ImageIcon(DisplayCrearProyecto.class.getResource("/img/volver-flecha.png")));
        buttonVolver.setBounds(30, 27, 41, 36);
        contentPane.add(buttonVolver);

            
        ;

        // Acción para cargar feedback
        btnEnviarFeedback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaEntregas.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una entrega de la tabla.");
                    return;
                }

                int entregaId = (int) tablaEntregas.getValueAt(filaSeleccionada, 0);
                String feedback = JOptionPane.showInputDialog("Ingrese su feedback para la entrega seleccionada:");

                if (feedback == null || feedback.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El feedback no puede estar vacío.");
                    return;
                }

                boolean enviado = Editor.enviarFeedback(entregaId, feedback);
                if (enviado) {
                    JOptionPane.showMessageDialog(null, "El feedback se envió correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al enviar el feedback.");
                }
            }
        });

        // Cargar las entregas al iniciar
        cargarEntregasEnTabla();
    }

    private void cargarEntregasEnTabla() {
        LinkedList<Entrega> entregas = ControllerEntrega.MostrarEntregas();
        DefaultTableModel modelo = (DefaultTableModel) tablaEntregas.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        for (Entrega entrega : entregas) {
            modelo.addRow(new Object[] {
                entrega.getId(),
                entrega.getLibro().getTitulo(),
                entrega.getAutor().getNombre() + " " + entrega.getAutor().getApellido(),
                entrega.getEstado(),
                entrega.getFechaEntrega()
            });
        }
    }
}
