package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Autor;
import BLL.Libro;
import BLL.SesionUsuario;
import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputEmail;
	private JTextField inputPassword;

	
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("LivreMond");
		titulo.setBounds(206, 67, 307, 67);
		titulo.setFont(new Font("Old English Text MT", Font.BOLD, 50));
		contentPane.add(titulo);
		
		JLabel subtitulo = new JLabel("Sign in");
		subtitulo.setBounds(267, 159, 153, 47);
		subtitulo.setFont(new Font("Mongolian Baiti", Font.PLAIN, 40));
		contentPane.add(subtitulo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(36, 224, 523, 0);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(68, 216, 523, 2);
		contentPane.add(separator_1);
		
		JLabel labelEmail = new JLabel("Email Address");
		labelEmail.setForeground(new Color(192, 192, 192));
		labelEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		labelEmail.setBounds(159, 266, 241, 27);
		contentPane.add(labelEmail);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setForeground(new Color(192, 192, 192));
		labelPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		labelPassword.setBounds(159, 365, 241, 27);
		contentPane.add(labelPassword);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(170, 303, 316, 29);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		inputPassword = new JTextField();
		inputPassword.setColumns(10);
		inputPassword.setBounds(170, 396, 316, 27);
		contentPane.add(inputPassword);
		
		JButton buttonSignIn = new JButton("Sign In");
		buttonSignIn.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		buttonSignIn.setBounds(170, 471, 316, 36);
		contentPane.add(buttonSignIn);
		
		buttonSignIn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		        String email = inputEmail.getText();
		        String password = inputPassword.getText();
		        Usuario usuario = Usuario.LogIn(email, password);		        

				if (email.isEmpty() || password.isEmpty()) {
		            JOptionPane.showMessageDialog(contentPane, 
		                "Please enter both email and password.", 
		                "Validation Error", 
		                JOptionPane.ERROR_MESSAGE);
		           
		        } else if(usuario == null) {
		            JOptionPane.showMessageDialog(contentPane, 
		                "Invalid email or password. Please try again.", 
		                "Login Failed", 
		                JOptionPane.ERROR_MESSAGE);
		            
		        } else if (usuario != null) {
		            JOptionPane.showMessageDialog(contentPane, 
			                "Welcome, " + email + "!", 
			                "Login Successful", 
			                JOptionPane.INFORMATION_MESSAGE);
			        SesionUsuario.getInstancia();
			        SesionUsuario.getInstancia().setUsuarioLogueado(usuario);
			        LoginScreen.this.dispose();
			        
			        switch (SesionUsuario.getInstancia().getUsuarioLogueado().getRol()) {		        
			            case "autor":
			            	DashboardAutor dashboardAutor = new DashboardAutor();
							dashboardAutor.setVisible(true);
			                break;
			                
			            case "editor":
			            	DashboardEditor dashboardEditor= new DashboardEditor();
			            	dashboardEditor.setVisible(true);
			                break;
			                
			            case "admin":
			            	DashboardAdmin dashboardAdmin = new DashboardAdmin();
			            	dashboardAdmin.setVisible(true);
			                break;  
			                
			        }
		        }
		    }
		});
		
		JLabel labelSignUp = new JLabel("Need an account?");
		labelSignUp.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		labelSignUp.setBounds(271, 540, 215, 27);
		contentPane.add(labelSignUp);
		
		JButton buttonSignUp = new JButton("Sign Up Now");
		buttonSignUp.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		buttonSignUp.setBounds(237, 577, 202, 36);
		contentPane.add(buttonSignUp);
		
		buttonSignUp.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	LoginScreen.this.dispose();
		    	SignUpScreen signUpScreen = new SignUpScreen();
		    	signUpScreen.setVisible(true);	    	
		    }
		});
	}
}