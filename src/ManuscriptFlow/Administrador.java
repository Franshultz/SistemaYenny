package ManuscriptFlow;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Administrador extends Usuario implements RegistrarAccion{

	private static int contadorAdmins = 0;
	private int adminID; 
	private LinkedList<String> registroAcciones = new LinkedList<String>();
	
	public Administrador() {
        super(null, null, null, null, null, null, null); 
        this.adminID = ++contadorAdmins;   
        this.registroAcciones = registroAcciones;
    }

	public static int getContadorAdmins() {
		return contadorAdmins;
	}

	public static void setContadorAdmins(int contadorAdmins) {
		Administrador.contadorAdmins = contadorAdmins;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public LinkedList<String> getRegistroAcciones() {
		return registroAcciones;
	}

	public void setRegistroAcciones(LinkedList<String> registroAcciones) {
		this.registroAcciones = registroAcciones;
	}

	
	@Override
	public void RegistrarUsuario() {
		String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
		while (nombre.isEmpty()) {
			nombre = JOptionPane.showInputDialog("El nombre no puede estar vacio");		
		}
		
		String apellido = JOptionPane.showInputDialog("Ingrese su apellido");
		while (apellido.isEmpty()) {
			apellido = JOptionPane.showInputDialog("El apellido no puede estar vacio");		
		}
				
		String email = JOptionPane.showInputDialog("Ingrese su email");
		while (!email.contains("@")) {
			email = JOptionPane.showInputDialog("Su email debe contener '@' ");
		}
		
		String password = ""; 
		boolean validacionPassword = false;
		do {
			password = JOptionPane.showInputDialog("Ingrese su contraseña");
			while (password.isEmpty()) {
				password = JOptionPane.showInputDialog("La contraseña no puede estar vacia");		
			}
			
			boolean contentUpperCase = false;
			int contDigit = 0;
			for (int j = 0; j < password.length(); j++) {
				if (Character.isUpperCase(password.charAt(j))) {
					contentUpperCase = true;
				}	
				if (Character.isDigit(password.charAt(j))) {
					contDigit++;
				}
			}
			
			if (!contentUpperCase && contDigit < 3) {
				JOptionPane.showMessageDialog(null, "La contraseña debe contener una mayuscula y tres digitos como minimo");
			} else if (!contentUpperCase) {
				JOptionPane.showMessageDialog(null, "La contraseña debe contener una mayuscula como minimo");
			} else if (contDigit < 3) {
				JOptionPane.showMessageDialog(null, "La contraseña debe contener tres digitos como minimo");
			} else {
				validacionPassword = true;
			}
			
		} while (!validacionPassword);
		
		String estadoCuenta = "Activo";
		String rol = "Administrador";
		
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setContraseña(password);
		this.setRol(rol);
		this.setEstadoCuenta(estadoCuenta);
		this.setLibreria(this.getLibreria());
				
		this.getListaUsuarios().add(this);
		
		this.RegistrarAccion("Sign Up");
		
	}
	

	@Override
	public Usuario IniciarSesion() {
	    String email = JOptionPane.showInputDialog("Ingrese su email");
	    String password = JOptionPane.showInputDialog("Ingrese su contraseña");

	    for (Usuario usuario : Usuario.getListaUsuarios()) {
	        
	        if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(password) && usuario instanceof Administrador) {
	            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " (" + usuario.getRol() + ")");
	            return usuario;
	        }
	    }

	    JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario o la contraseña es incorrecta");
	    return null; 
	}
	
	
	@Override
	public void RegistrarAccion(String accion) {
		switch (accion) {
		case "Sign Up":
			this.getRegistroAcciones().add("El admin " + this.getNombre() + " " + this.getApellido() 
			+ " se registro el " + LocalDate.now().getDayOfMonth() + " de " + LocalDate.now().getMonth() 
			+ " del " + LocalDate.now().getYear());			
			System.out.println(this.getRegistroAcciones());
			break;
			
		default:
			break;
		}		
	}
	
	public void GestionarUsuarios() {
		
	}
	
	public void GestionarLibros() {
		
	}
	

}
