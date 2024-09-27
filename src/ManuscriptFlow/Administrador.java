package ManuscriptFlow;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Administrador extends Usuario implements RegistrarAccion, Validaciones{

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
		nombre = ValidacionNombre(nombre);
		
		String apellido = JOptionPane.showInputDialog("Ingrese su apellido");
		apellido = ValidacionApellido(apellido);
				
		String email = JOptionPane.showInputDialog("Ingrese su email");
		email = ValidacionEmail(email);
		

		String password = JOptionPane.showInputDialog("Ingrese su contraseña");
		password = ValidacionPassword(password);	
		
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
		email = ValidacionEmail(email);
		
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

	public void AdministrarUsuarios() {
		String[] opciones = {"Agregar Usuario", "Ver Usuarios", "Editar Usuario", "Eliminar Usuario", "Salir"};
		int seleccionUsuarios;
		
		do {
			seleccionUsuarios = JOptionPane.showOptionDialog(null, 
					"Seleccione una opción para administrar usuarios", 
					"Administración de Usuarios", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.INFORMATION_MESSAGE, 
					null, 
					opciones, 
					opciones[0]);
			
			switch (seleccionUsuarios) {
			case 0: 
				Usuario.getListaUsuarios();
				break;
			case 1: 
				Usuario.getListaUsuarios();
				break;
			case 2: 
				Usuario.getListaUsuarios();
				break;
			case 3: 
				Usuario.getListaUsuarios();
				break;
			case 4: 
				JOptionPane.showMessageDialog(null, "Hasta nunca");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opción no válida");
				break;
			}
		} while (seleccionUsuarios != 4);
	}
	
	
	public void AdministrarLibros() {
			
	        String[] CRUD = {"Agregar", "Modificar", "Eliminar", "Salir"};
	        int seleccionCRUD = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administrar Libros",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, CRUD, CRUD[0]);;
	       
	        
	        switch (seleccionCRUD) {
	            case 0: 
	                Libro libro = ValidacionLibro();
	                
	                break;
	
	            case 1: 
	                
	            case 2: 
	                
	            case 3: 
	                break;
	
	            default:
	                JOptionPane.showMessageDialog(null, "ERROR");
	                break;
	        }
		}
	}
