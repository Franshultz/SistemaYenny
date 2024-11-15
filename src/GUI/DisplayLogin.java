package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

public class DisplayLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputEmail;
	private JTextField inputPassword;
	private Usuario usuarioLogueado = null; // Variable para almacenar el usuario logueado

	public DisplayLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("LivreMond");
		titulo.setBounds(237, 43, 245, 68);
		titulo.setFont(new Font("Old English Text MT", Font.PLAIN, 50));
		contentPane.add(titulo);
		
		JLabel subtitulo = new JLabel("Sign in to Yenny");
		subtitulo.setBounds(288, 165, 144, 40);
		subtitulo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		contentPane.add(subtitulo);
		
		JLabel labelEmail = new JLabel("Email address or Username");
		labelEmail.setBounds(187, 274, 197, 27);
		labelEmail.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		contentPane.add(labelEmail);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(187, 375, 79, 22);
		labelPassword.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		contentPane.add(labelPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(118, 216, 482, 22);
		contentPane.add(separator);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(198, 303, 324, 27);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		inputPassword = new JTextField();
		inputPassword.setBounds(198, 399, 324, 27);
		inputPassword.setColumns(10);
		contentPane.add(inputPassword);
		
		JButton buttonSignIn = new JButton("Sign In");
		buttonSignIn.setBackground(new Color(0, 0, 0));
		buttonSignIn.setForeground(new Color(0, 128, 0));
		buttonSignIn.setBounds(220, 473, 133, 33);
		buttonSignIn.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		
		buttonSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Usuario usuarioLogueado = Usuario.LogIn(inputEmail.getText(), inputPassword.getText());
                if (usuarioLogueado == null) {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intente nuevamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    SesionUsuario.getInstancia().setUsuarioLogueado(usuarioLogueado); // Guarda el usuario logueado
                    dispose(); // Cierra la ventana de login
                }
            }
        });
		
		contentPane.add(buttonSignIn);
		
		JButton buttonSignUp = new JButton("Sign Up");
		buttonSignUp.setBackground(new Color(0, 0, 0));
		buttonSignUp.setBounds(370, 473, 133, 33);
		buttonSignUp.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		buttonSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Acción para el botón Sign Up (registrar nuevo usuario)
			}
		});
		contentPane.add(buttonSignUp);
	}
	
	// Método para obtener el usuario logueado
	public Usuario getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public static void main(String[] args) {
		DisplayLogin frame = new DisplayLogin();
		frame.setVisible(true);
		
		// Esperar hasta que el usuario se loguee
		while (frame.getUsuarioLogueado() == null) {
			try {
				Thread.sleep(100); // Tiempo de espera para evitar el consumo excesivo de CPU
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Usuario logueado, ahora puedes obtenerlo
		Usuario usuario = frame.getUsuarioLogueado();
		System.out.println("Usuario logueado: " + usuario);
	}
}

