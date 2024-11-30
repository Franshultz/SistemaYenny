package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Editor;
import BLL.Libro;
import DLL.ControllerLibro;
import DLL.RegistrarAccion;
import BLL.SesionUsuario;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DisplayVerLibros extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayVerLibros frame = new DisplayVerLibros();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DisplayVerLibros() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ver Libros");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 30));
        lblNewLabel.setBounds(294, 48, 204, 47);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 106, 730, 300);
        contentPane.add(scrollPane);

        
        table = new JTable();
        scrollPane.setViewportView(table);

        
        DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Título", "Estado" });
        table.setModel(modelo);

        
        List<Libro> libros = Editor.getLibros(); 
        for (Libro libro : libros) {
            modelo.addRow(new Object[] { libro.getId(), libro.getTitulo(), libro.getEstadoLibro() });
        }

        JButton buttonVerDetalles = new JButton("Ver Detalles");
        buttonVerDetalles.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        buttonVerDetalles.setBounds(305, 420, 160, 40);
        contentPane.add(buttonVerDetalles);

        buttonVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow(); 

                if (row != -1) {
                    int libroId = (int) table.getValueAt(row, 0); 
                    Libro libro = ControllerLibro.BuscarLibro(libroId); 
                    if (libro != null) {
                        
                        
                        JOptionPane.showMessageDialog(null, "Título: " + libro.getTitulo() + "\nEstado: " + libro.getEstadoLibro());
                    } else {
                        JOptionPane.showMessageDialog(null, "Libro no encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un libro.");
                }
            }
        });

        JButton buttonVolver = new JButton("");
        buttonVolver.setBackground(new Color(255, 255, 255));
        buttonVolver.setIcon(new ImageIcon(DisplayCrearProyecto.class.getResource("/img/volver-flecha.png")));
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

