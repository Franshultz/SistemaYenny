package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Autor;
import BLL.SesionUsuario;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Label;

public class DashboardAutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Autor usuario = (Autor) SesionUsuario.getInstancia().getUsuarioLogueado();

	public DashboardAutor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonVerProyecto = new JButton("Ver Proyectos");
		buttonVerProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					DisplayMisLibros misLibros = new DisplayMisLibros();
					misLibros.setVisible(true);
					dispose();
			}
		});
		buttonVerProyecto.setBounds(60, 171, 138, 38);
		contentPane.add(buttonVerProyecto);
		
		JButton buttonCrearProyecto = new JButton("Crear nuevo proyecto");
		buttonCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayCrearProyecto displayCrear = new DisplayCrearProyecto();
				displayCrear.setVisible(true);
				dispose();
			}
		});
		buttonCrearProyecto.setBounds(298, 171, 138, 38);
		contentPane.add(buttonCrearProyecto);
		
		JButton buttonCerrarSesion = new JButton("");
		buttonCerrarSesion.setIcon(new ImageIcon(DashboardAutor.class.getResource("/img/cerrar-sesion.png")));
		buttonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SesionUsuario.getInstancia().cerrarSesion();
				LoginScreen login = new LoginScreen();
				login.setVisible(true);
				dispose();
			}
		});
		
		buttonCerrarSesion.setBounds(10, 10, 39, 38);
		contentPane.add(buttonCerrarSesion);
		
		JLabel labelInfoUsuario = new JLabel("");
		labelInfoUsuario.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		labelInfoUsuario.setBounds(22, 171, 703, 28);
		contentPane.add(labelInfoUsuario);
		
		JLabel lblNewLabel = new JLabel("Dashboard  de");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setAlignmentY(Component.CENTER_ALIGNMENT) ;
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(84, 27, 245, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(usuario.getNombre() + " " + usuario.getApellido());
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(84, 65, 269, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DashboardAutor.class.getResource("/img/logo1.jpg")));
		lblNewLabel_2.setBounds(399, 10, 300, 113);
		contentPane.add(lblNewLabel_2);
	}
}
