package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.SesionUsuario;
import BLL.Editor;

public class DashboardEditor extends JFrame {

    private JPanel contentPane;
    private Editor usuario = (Editor) SesionUsuario.getInstancia().getUsuarioLogueado();
    

    public DashboardEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 818, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Dashboard Editor");
        lblTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 30));
        lblTitulo.setBounds(300, 20, 300, 50);
        contentPane.add(lblTitulo);

        JButton btnEnviarFeedback = new JButton("Enviar Feedback");
        btnEnviarFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btnEnviarFeedback.setBounds(300, 100, 200, 40);
        btnEnviarFeedback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DisplayEnviarFeedback pantallaFeedback = new DisplayEnviarFeedback();
                pantallaFeedback.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnEnviarFeedback);

        JButton btnRevisarEntrega = new JButton("Revisar Entrega");
        btnRevisarEntrega.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btnRevisarEntrega.setBounds(300, 160, 200, 40);
        btnRevisarEntrega.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DisplayRevisarEntrega pantallaRevisar = new DisplayRevisarEntrega();
                pantallaRevisar.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnRevisarEntrega);

        JButton btnVerLibros = new JButton("Ver Libros");
        btnVerLibros.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btnVerLibros.setBounds(300, 220, 200, 40);
        btnVerLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DisplayVerLibros pantallaLibros = new DisplayVerLibros();
                pantallaLibros.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVerLibros);

        JButton btnCerrarSesion = new JButton("");
        btnCerrarSesion.setIcon(new ImageIcon(DashboardEditor.class.getResource("/img/cerrar-sesion.png")));
        btnCerrarSesion.setBounds(10, 10, 39, 38);
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SesionUsuario.getInstancia().cerrarSesion();
                LoginScreen login = new LoginScreen();
                login.setVisible(true);
                dispose();
            }
        });
        JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DashboardAutor.class.getResource("/img/logo1.jpg")));
		lblNewLabel_2.setBounds(399, 10, 300, 113);
		contentPane.add(lblNewLabel_2);
		
        contentPane.add(btnCerrarSesion);
    }
}
