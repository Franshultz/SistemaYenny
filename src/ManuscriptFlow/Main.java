package ManuscriptFlow;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		Libreria yenny = new Libreria("Yenny", "Av. Santa Fe 1860");
		
		
		for (int i = 0; i < 3; i++) {			
			String[] rolesOpciones = {"Administrador", "Autor", "Editor"};
			int opcion = JOptionPane.showOptionDialog(null, "Seleccione que tipo de Usuario desea ingresar", null, 0, 0, null, rolesOpciones, rolesOpciones);

			switch (opcion) {
			case 0:
				Administrador admin = new Administrador();
				admin.RegistrarUsuario();
				break;
			case 1:
				Autor autor = new Autor();
				autor.RegistrarUsuario();
				break;
			case 2:
				Editor editor = new Editor();
				editor.RegistrarUsuario();
				break;

			default:
				break;
			}
		}			
		
		Usuario sesion = null;
		 int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea Iniciar Sesion?", "Confirmar", JOptionPane.YES_NO_OPTION);
		 if (respuesta == JOptionPane.YES_OPTION) {
			 String[] rolesOpciones = {"Administrador", "Autor", "Editor"};
			 int opcionInicio = JOptionPane.showOptionDialog(null, "Seleccione su rol", "Iniciar Sesión", 0, 0, null, rolesOpciones, rolesOpciones);
		        switch (opcionInicio) {
		            case 0:
		                sesion = new Administrador().IniciarSesion();
		                break;
		            case 1:
		            	sesion = new Autor().IniciarSesion();
		                break;
		            case 2:
		            	sesion = new Editor().IniciarSesion();
		                break;
		            default:
		                JOptionPane.showMessageDialog(null, "Debe seleccionar un rol");
		                break;
		        }
		 } else if (respuesta == JOptionPane.NO_OPTION) {
	            JOptionPane.showMessageDialog(null, "Fin de Sistema :p");
	     }
		 
		 if (sesion != null) {
			String rolSesion = sesion.getRol();
			if (rolSesion.equals("Adminstrador")) {
				 String[] metodosOpciones = {"Gestionar Usuarios", "Gestionar Libros"};
				 int opcion = JOptionPane.showOptionDialog(null, "Seleccione que tipo de Usuario desea ingresar", null, 0, 0, null, metodosOpciones, metodosOpciones);
				 
				 switch (opcion) {
		            case 0:
		                ((Administrador) sesion).GestionarUsuarios(); 
		                break;
		            case 1:
		                ((Administrador) sesion).GestionarLibros(); 
		                break;
		            default:
		                break;
		        }
				 
			} else if (rolSesion == "Autor"){

			} else if (rolSesion == "Editor") {
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "error, completar**");
		}

	}

}
