package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import DLL.ControllerUsuario;
import BLL.Usuario;

public class DisplayCRUDUsuarios extends JFrame {
    private JPanel contentPane;
    private JTable tableUsuarios;
    private DefaultTableModel model;

    public DisplayCRUDUsuarios() {
        setTitle("CRUD de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        
        model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Email"}, 0);
        
       
        tableUsuarios = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        scrollPane.setBounds(20, 59, 740, 250);
        contentPane.add(scrollPane);
        
       
        JButton btnActualizar = new JButton("Actualizar Usuario");
        btnActualizar.setBounds(20, 320, 150, 30);
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    int usuarioID = (int) model.getValueAt(selectedRow, 0); 
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo Nombre:");
                    String nuevoApellido = JOptionPane.showInputDialog("Nuevo Apellido:");
                    String nuevoEmail = JOptionPane.showInputDialog("Nuevo Email:");
                    
                    Usuario usuario = ControllerUsuario.BuscarUsuario(usuarioID);
                    usuario.setNombre(nuevoNombre);
                    usuario.setApellido(nuevoApellido);
                    usuario.setEmail(nuevoEmail);
                    
                   
                    ControllerUsuario.ActualizarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario.");
                }
            }
        });
        contentPane.add(btnActualizar);
        
        
        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.setBounds(180, 320, 150, 30);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    int usuarioID = (int) model.getValueAt(selectedRow, 0); 
                    ControllerUsuario.EliminarUsuario(usuarioID);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario.");
                }
            }
        });
        contentPane.add(btnEliminar);
        
        
        actualizarTabla();
    }

    
    private void actualizarTabla() {
       
        model.setRowCount(0);
        
        
        LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getId(), 
                usuario.getNombre(), 
                usuario.getApellido(), 
                usuario.getEmail()
            });
        }
    
    JButton buttonVolver = new JButton("");
    buttonVolver.setBackground(new Color(255, 255, 255));
    buttonVolver.setIcon(new ImageIcon(DisplayCrearProyecto.class.getResource("/img/volver-flecha.png")));
    buttonVolver.setBounds(20, 12, 41, 36);
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
