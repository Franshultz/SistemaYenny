package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Entrega;
import BLL.Libro;
import BLL.Autor;
import DLL.ControllerEntrega;
import DLL.ControllerLibro;
import DLL.ControllerUsuario;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class DisplayRevisarEntrega extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> comboBoxEstado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayRevisarEntrega frame = new DisplayRevisarEntrega();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DisplayRevisarEntrega() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Revisar Entregas");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 30));
        lblNewLabel.setBounds(294, 48, 204, 47);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 106, 730, 300);
        contentPane.add(scrollPane);

        
        table = new JTable();
        scrollPane.setViewportView(table);

        DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Libro", "Autor", "Estado", "Feedback", "Fecha de Entrega" });
        table.setModel(modelo);

        LinkedList<Entrega> entregas = ControllerEntrega.MostrarEntregas(); 
        for (Entrega entrega : entregas) {
            modelo.addRow(new Object[] { 
                entrega.getId(),
                entrega.getLibro().getTitulo(),  
                entrega.getAutor().getNombre() + " " + entrega.getAutor().getApellido(),  
                entrega.getEstado(), 
                entrega.getFeedback(),
                entrega.getFechaEntrega()
            });
        }

        
        JLabel lblNuevoEstado = new JLabel("Nuevo Estado:");
        lblNuevoEstado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNuevoEstado.setBounds(30, 420, 120, 30);
        contentPane.add(lblNuevoEstado);

        comboBoxEstado = new JComboBox<>(new String[] { "En revisión", "Aprobado", "Rechazado", "Pendiente" });
        comboBoxEstado.setBounds(150, 420, 160, 30);
        contentPane.add(comboBoxEstado);

        
        JButton buttonCambiarEstado = new JButton("Cambiar Estado");
        buttonCambiarEstado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        buttonCambiarEstado.setBounds(330, 420, 160, 40);
        contentPane.add(buttonCambiarEstado);

        buttonCambiarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row != -1) {
                    int entregaId = (int) table.getValueAt(row, 0); 
                  
                    Entrega entrega = null;
                    for (Entrega ent : entregas) {
                        if (ent.getId() == entregaId) {
                            entrega = ent;
                            break;
                        }
                    }

                    if (entrega != null) {
                       
                        String nuevoEstado = (String) comboBoxEstado.getSelectedItem();

                        
                        entrega.setEstado(nuevoEstado);
                        ControllerEntrega.actualizarEntrega(entrega.getId(), entrega);
                        
                       
                        table.setValueAt(nuevoEstado, row, 3);

                        JOptionPane.showMessageDialog(null, "El estado de la entrega ha sido actualizado a: " + nuevoEstado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Entrega no encontrada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una entrega.");
                }
            }
        });

      
       
                    

        
        JButton buttonVolver = new JButton("");
        buttonVolver.setBackground(new Color(255, 255, 255));
        buttonVolver.setIcon(new ImageIcon(DisplayRevisarEntrega.class.getResource("/img/volver-flecha.png")));
        buttonVolver.setBounds(30, 27, 41, 36);
        contentPane.add(buttonVolver);

        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                DashboardEditor dashboardEditor = new DashboardEditor();
                dashboardEditor.setVisible(true);
                dispose(); 
            }
        });
    }
}
