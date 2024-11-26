package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import DLL.Validaciones;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SignUpScreen extends JFrame implements Validaciones{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputApellido;
	private JTextField inputEmail;
	private JTextField inputPassword;
	private JLabel labelNombre;
	private JLabel labelApellido;
	private JLabel labelEmail;
	private JLabel labelPassword;
	private JLabel titulo;
	private JButton buttonSignUp;

	public SignUpScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputNombre = new JTextField();
		inputNombre.setBounds(179, 161, 298, 28);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		inputApellido = new JTextField();
		inputApellido.setColumns(10);
		inputApellido.setBounds(179, 237, 298, 28);
		contentPane.add(inputApellido);
		
		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(179, 310, 298, 28);
		contentPane.add(inputEmail);
		
		inputPassword = new JTextField();
		inputPassword.setColumns(10);
		inputPassword.setBounds(179, 389, 298, 28);
		contentPane.add(inputPassword);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		labelNombre.setBounds(169, 136, 101, 20);
		contentPane.add(labelNombre);
		
		labelApellido = new JLabel("Apellido");
		labelApellido.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		labelApellido.setBounds(169, 214, 101, 20);
		contentPane.add(labelApellido);
		
		labelEmail = new JLabel("Email");
		labelEmail.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		labelEmail.setBounds(169, 287, 101, 20);
		contentPane.add(labelEmail);
		
		labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		labelPassword.setBounds(169, 365, 101, 20);
		contentPane.add(labelPassword);
		
		titulo = new JLabel("Sign Up");
		titulo.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 35));
		titulo.setBounds(251, 55, 167, 61);
		contentPane.add(titulo);
		
		buttonSignUp = new JButton("Sign Up");
		buttonSignUp.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		buttonSignUp.setBounds(180, 453, 298, 34);
		contentPane.add(buttonSignUp);
		
		buttonSignUp.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String nombre = inputNombre.getText();
		    	String apellido = inputApellido.getText();
		    	String email = inputEmail.getText();
		    	String password = inputPassword.getText();		    
		    	if (Usuario.SingUp(nombre, apellido, email, password)) {
		    		SignUpScreen.this.dispose();
					LoginScreen login = new LoginScreen();
					login.setVisible(true);
				}
		    }
		});
	}

}
