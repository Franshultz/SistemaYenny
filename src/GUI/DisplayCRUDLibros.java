package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import DLL.ControllerLibro;
import BLL.Libro;

public class DisplayCRUDLibros extends JFrame {
    private JPanel contentPane;
    private JTable tableLibros;
    private DefaultTableModel model;

    public DisplayCRUDLibros() {
        setTitle("CRUD de Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        model = new DefaultTableModel(new Object[]{"ID", "Título", "Género", "Precio", "Formato", "Stock"}, 0);
        
        
        tableLibros = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableLibros);
        scrollPane.setBounds(20, 59, 740, 250);
        contentPane.add(scrollPane);
        
        
        JButton btnActualizar = new JButton("Actualizar Libro");
        btnActualizar.setBounds(20, 320, 150, 30);
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableLibros.getSelectedRow();
                if (selectedRow != -1) {
                    int libroID = (int) model.getValueAt(selectedRow, 0);  
                    double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo Precio:"));
                    String nuevoFormato = JOptionPane.showInputDialog("Nuevo Formato (Físico/Digital):");
                    int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Nuevo Stock:"));
                    
                    Libro libro = ControllerLibro.BuscarLibro(libroID);
                    libro.setPrecio(nuevoPrecio);
                    libro.setFormato(nuevoFormato);
                    libro.setStockDisponible(nuevoStock);
                    
                    if (ControllerLibro.ActualizarLibro(libro)) {
                        JOptionPane.showMessageDialog(null, "Libro actualizado exitosamente.");
                        actualizarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el libro.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un libro.");
                }
            }
        });
        contentPane.add(btnActualizar);
        
        
        JButton btnEliminar = new JButton("Eliminar Libro");
        btnEliminar.setBounds(180, 320, 150, 30);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableLibros.getSelectedRow();
                if (selectedRow != -1) {
                    int libroID = (int) model.getValueAt(selectedRow, 0);  
                    if (ControllerLibro.EliminarLibro(libroID)) {
                        JOptionPane.showMessageDialog(null, "Libro eliminado exitosamente.");
                        actualizarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el libro.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un libro.");
                }
            }
        });
        contentPane.add(btnEliminar);
        
       
        actualizarTabla();
    }

   
    private void actualizarTabla() {
      
        model.setRowCount(0);
        
        
        LinkedList<Libro> libros = ControllerLibro.MostrarLibros(1);
        for (Libro libro : libros) {
            model.addRow(new Object[]{
                libro.getId(), 
                libro.getTitulo(), 
                libro.getGenero(), 
                libro.getPrecio(), 
                libro.getFormato(), 
                libro.getStockDisponible()
            });
        }
    
    JButton buttonVolver = new JButton("");
    buttonVolver.setBackground(new Color(255, 255, 255));
    buttonVolver.setIcon(new ImageIcon(DisplayCrearProyecto.class.getResource("/img/volver-flecha.png")));
    buttonVolver.setBounds(10, 11, 41, 36);
    contentPane.add(buttonVolver);

    buttonVolver.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            DashboardAdmin dashboardAdmin = new DashboardAdmin();
            dashboardAdmin.setVisible(true);

            dispose();
        }
    });
}
}
