	package BLL;
	
	import java.util.LinkedList; 

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import BLL.Usuario;
import DLL.Conexion;
import DLL.ControllerUsuario;
import DLL.Validaciones;
	
	
	public abstract class Usuario implements Validaciones{
		private static Connection connect = Conexion.getInstance().getConection();
	
		private int id;
		private String nombre;
		private String apellido;
		private String email;
		private String contraseña;
		private String rol;
		private String estadoCuenta;
		private LinkedList<String> interesLiterario;
		
		public Usuario(int id, String nombre, String apellido, String email, String contraseña, String rol, String estadoCuenta) {
			this.id = id;
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
			this.contraseña = contraseña;
			this.rol = rol;
			this.estadoCuenta = estadoCuenta;
			this.interesLiterario = null;
		}
		
		public Usuario(String nombre, String apellido, String email, String contraseña, String estadoCuenta) {
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
			this.contraseña = contraseña;
			this.rol = "autor";
			this.estadoCuenta = estadoCuenta;
			this.interesLiterario = null;
		}
	
		
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public String getNombre() {
			return nombre;
		}
	
	
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	
	
		public String getApellido() {
			return apellido;
		}
	
	
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
	
	
		public String getEmail() {
			return email;
		}
	
	
		public void setEmail(String email) {
			this.email = email;
		}
	
	
		public String getContraseña() {
			return contraseña;
		}
	
	
		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}
	
	
		public String getRol() {
			return rol;
		}
	
	
		public void setRol(String rol) {
			this.rol = rol;
		}
	
	
		public String getEstadoCuenta() {
			return estadoCuenta;
		}
	
	
		public void setEstadoCuenta(String estadoCuenta) {
			this.estadoCuenta = estadoCuenta;
		}		
			

		public LinkedList<String> getInteresLiterario() {
			return interesLiterario;
		}

		public void setInteresLiterario(LinkedList<String> interesLiterario) {
			this.interesLiterario = interesLiterario;
		}

		
		@Override
		public String toString() {
			return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
					+ ", contraseña=" + contraseña + ", rol=" + rol + ", estadoCuenta=" + estadoCuenta
					+ ", interesLiterario=" + interesLiterario + "]";
		}
		
		
//		public static void SingUp(String nombre) {
//			nombre = Validaciones.ValidacionNombre(nombre);
//			String apellido = Validaciones.ValidacionApellido();						
//			String emailRegistro;
//			boolean flag;
//			LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();		    
//		    do {
////		        emailRegistro = Validaciones.ValidacionEmail();
////		        flag = false; 
//		        
//		        for (Usuario persona : usuarios) {
//		            if (persona.getEmail().equals(emailRegistro)) {
//		                JOptionPane.showMessageDialog(null, "Mail ya registrado, intente con otro");
//		                flag = true; 
//		                break;
//		            }
//		        }
//		    } while (flag); 
//
//		    String contraseñaRegistro = Validaciones.ValidacionPassword();
//		    ControllerUsuario.AgregarUsuario(new Autor(nombre, apellido, emailRegistro, contraseñaRegistro, "activo"));
//		}

		
		public static Usuario LogIn(String email, String contraseña) {
			email = Validaciones.ValidacionEmail(email);		

			LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
			for (Usuario persona : usuarios) {
				if (persona.getEmail().equals(email) && persona.getContraseña().equals(contraseña)) {				
					return persona;
				} 
			}
			return null;		
		}
	
	}
