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

	public void AdministrarLibros(Libro libro) {
		
        String[] CRUD = {"Agregar", "Modificar", "Eliminar", "Salir"};
        
			
		
        int seleccionCRUD;
        seleccionCRUD = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administrar Libros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, CRUD, CRUD[0]);
        
        switch (seleccionCRUD) {
            case 0: 
                String nuevoTitulo = JOptionPane.showInputDialog("Ingresar título del libro:");
                if (nuevoTitulo != null && !nuevoTitulo.trim().isEmpty()) {
                    libro.setTitulo(nuevoTitulo);
                    JOptionPane.showMessageDialog(null, "Libro agregado exitosamente: " + libro.getTitulo());
                }
                break;

            case 1: 
                String tituloActual = JOptionPane.showInputDialog("Ingresar título del libro a modificar:");
                if (tituloActual != null && tituloActual.equals(libro.getTitulo())) {
                    String nuevoTituloModificado = JOptionPane.showInputDialog("Ingresar nuevo título:");
                    if (nuevoTituloModificado != null && !nuevoTituloModificado.trim().isEmpty()) {
                    	JOptionPane.showMessageDialog(null, "Título del libro " + libro.getTitulo() + "modificado a " + nuevoTituloModificado);
                        libro.setTitulo(nuevoTituloModificado);
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un libro con ese título.");
                }
                break;

            case 2: 
                String tituloEliminar = JOptionPane.showInputDialog("Ingrese el título del libro a eliminar:");
                if (tituloEliminar != null && tituloEliminar.equals(libro.getTitulo())) {
                    // NECESITAMOS UNA LISTA DE LIBROS PARA PODER ELIMINARLOS
                    // listaLibros.remove("LIBRO X");
                    JOptionPane.showMessageDialog(null, "Libro eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un libro con ese título.");
                }
                break;

            case 3: 
                break;

            default:
                JOptionPane.showMessageDialog(null, "ERROR");
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
