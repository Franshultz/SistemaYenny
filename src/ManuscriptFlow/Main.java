package ManuscriptFlow;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		Libreria yenny = new Libreria("Yenny", "Av. Santa Fe 1860");
		
		
		for (int i = 0; i < 3; i++) {			
			String[] rolesOpciones = {"Administrador", "Autor", "Editor", "Iniciar Sesion"};
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
		            case 3:
		            	break;
		            default:
		                JOptionPane.showMessageDialog(null, "Debe seleccionar un rol");
		                break;
		        }
		 } else if (respuesta == JOptionPane.NO_OPTION) {
	            JOptionPane.showMessageDialog(null, "Fin de Sistema :p");
	     }
		 
		 if (sesion != null) {//ADMIN
			String rolSesion = sesion.getRol();
			Administrador admin = new Administrador();
			Autor autor = new Autor();
			Libro libro = new Libro("BLAME", autor, "1234567890", "Ficción", 19.99, "Físico", "Disponible", LocalDate.now(), 10, null, 0.0);
			if (rolSesion.equals("Administrador")) {
				 String[] metodosOpciones = {"Gestionar Libros", "Gestionar Usuarios"};
				 int opcion = JOptionPane.showOptionDialog(null, "Seleccione que tipo de Usuario desea ingresar", null, 0, 0, null, metodosOpciones, metodosOpciones);
				 
				 switch (opcion) {
		            case 0:
		                admin.AdministrarLibros(libro);
		                break;
		            case 1:
		                admin.AdministrarUsuarios();; 
		                break;
		            default:
		                break;
		        }
				 
			} else if (rolSesion == "Editor"){//AUTOR
				Editor editor = new Editor();
				String[] metodosOpcionesEditor = {"Revisar Manuscritos", "Enviar Feedback", "Salir"};
				int opcionEditor = JOptionPane.showOptionDialog(null, "Seleccione que desea hacer", null, 0, 0, null, metodosOpcionesEditor, metodosOpcionesEditor);
				
				switch (opcionEditor) {
				case 0:
					editor.revisarEntrega();
					break;
                case 1:
					editor.enviarFeedback();
					break;
                case 2:
	                
	break;

				default: JOptionPane.showMessageDialog(null, "Error");
					break;
				}
			} else if (rolSesion == "Autor") {//EDITOR
				String[] metodosOpcionesAutor = {"Subir Entrega", "Editar Manuscritos", "Ver estado de Manuscritos", "Agregar Recordatorio", "Salir"};
				int opcionAutor = JOptionPane.showOptionDialog(null, "Seleccione que desea hacer", null, 0, 0, null, metodosOpcionesAutor, metodosOpcionesAutor);
				
				switch (opcionAutor) {
				case 0:
					autor.SubirEntrega();
					break;
				case 1:
					autor.EditarManuscrito();
					break;
				case 2:
					autor.verEstadoManuscrito();
					break;
				case 3:
					autor.agregarRecordatorio();
					break;
				case 4:
					
					break;
					

				default: JOptionPane.showMessageDialog(null, "Error");
					break;
				}
				
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "error, completar**");
		}

	}

}
