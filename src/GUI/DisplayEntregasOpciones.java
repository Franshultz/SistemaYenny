package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Autor;
import BLL.Libro;
import BLL.SesionUsuario;
import DLL.ControllerLibro;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DisplayEntregasOpciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int idLibro;
	Autor usuario = (Autor) SesionUsuario.getInstancia().getUsuarioLogueado();

	public DisplayEntregasOpciones(int idLibro) {
		this.idLibro = idLibro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1124, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonSubirEntrega = new JButton("Subir Entrega");
		buttonSubirEntrega.setFont(new Font("Leelawadee UI", Font.PLAIN, 18));
		buttonSubirEntrega.setBounds(149, 264, 168, 35);
		buttonSubirEntrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.SubirEntrega(ControllerLibro.BuscarLibro(idLibro));
			}
		});
		contentPane.add(buttonSubirEntrega);
		
		JButton btnEnviarRevision = new JButton("Enviar Revision");
		btnEnviarRevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario.SubirRevision(ControllerLibro.BuscarLibro(idLibro));
			}
		});
		
		btnEnviarRevision.setFont(new Font("Leelawadee UI", Font.PLAIN, 18));
		btnEnviarRevision.setBounds(483, 264, 168, 35);
		contentPane.add(btnEnviarRevision);
		
		JButton btnVerEstadoDe = new JButton("Ver estado de ultima entrega");
		btnVerEstadoDe.setFont(new Font("Leelawadee UI", Font.PLAIN, 18));
		btnVerEstadoDe.setBounds(416, 182, 313, 35);
		btnVerEstadoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado = usuario.verEstadoEntrega(ControllerLibro.BuscarLibro(idLibro));
				JOptionPane.showMessageDialog(null, "Su ultima entrega de " + ControllerLibro.BuscarLibro(idLibro).getTitulo() + "esta " + estado);
			}
		});
		contentPane.add(btnVerEstadoDe);
		
		JLabel lblNewLabel = new JLabel("Recorda que si tu ultima entrega esta en revision deberas esperar a que tener una devolucion antes de subir una nueva entrega");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblNewLabel.setBounds(110, 48, 929, 47);
		contentPane.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Leelawadee UI", Font.PLAIN, 18));
		btnCancelar.setBounds(821, 264, 168, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayMisLibros oa = new DisplayMisLibros();
				oa.setVisible(true);		
				dispose();
			}
		});
		contentPane.add(btnCancelar);
		
		JLabel lblTeneEnCuenta = new JLabel("Tene en cuenta tambien que si tienes pendiente una correcion deberas enviar la revision de tu ultima entrega antes de subir una nueva");
		lblTeneEnCuenta.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblTeneEnCuenta.setBounds(78, 105, 1006, 47);
		contentPane.add(lblTeneEnCuenta);
	}

}
