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

public class DashboardAutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Autor usuario = (Autor) SesionUsuario.getInstancia().getUsuarioLogueado();

	public DashboardAutor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 246);
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
		buttonVerProyecto.setBounds(58, 85, 138, 38);
		contentPane.add(buttonVerProyecto);
		
		JButton buttonCrearProyecto = new JButton("Crear nuevo proyecto");
		buttonCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.CrearProyecto();
			}
		});
		buttonCrearProyecto.setBounds(300, 85, 138, 38);
		contentPane.add(buttonCrearProyecto);
		
		JButton buttonCerrarSesion = new JButton("Cerrar Sesion");
		buttonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonCerrarSesion.setBounds(547, 85, 138, 38);
		contentPane.add(buttonCerrarSesion);
		
		JLabel labelInfoUsuario = new JLabel("");
		labelInfoUsuario.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		labelInfoUsuario.setBounds(22, 171, 703, 28);
		contentPane.add(labelInfoUsuario);
	}
}
