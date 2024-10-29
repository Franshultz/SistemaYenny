package GUI;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Autor;
import BLL.Editor;
import BLL.Entrega;
import BLL.Libro;
import BLL.Usuario;
import DLL.ControllerEntrega;
import DLL.ControllerLibro;
import DLL.ControllerUsuario;

public class Main {

    public static void main(String[] args) {

        boolean finSistema = false;
        do {
        	String[] opcionesInicio = { "Iniciar Sesion", "Registrarse", "Salir" };
        	int opcionInicioElegida = JOptionPane.showOptionDialog(null, "Sign Up - Sign In", "Librerias Yenny", 0, 0, null, opcionesInicio, opcionesInicio);
        	
        	switch (opcionInicioElegida) {
			case 0:
				Usuario usuario = Usuario.LogIn();
				if (usuario == null) {
					JOptionPane.showMessageDialog(null, "Mail y/o Contraseña no encontrados, verifique su contraseña o mail");
					
				} else {	
					switch (usuario.getRol()) {
				    case "autor":
				        boolean finSesion = false;
				        do {    
				            Autor autor = (Autor) ControllerUsuario.BuscarUsuario(usuario.getId());
				            String[] opcionesInicioAutor = { "Seleccionar proyecto", "Crear nuevo proyecto", "Eliminar proyecto", "Cerrar Sesion" };
				            int opcionInicioAutorElegida = JOptionPane.showOptionDialog(null, "", "Librerias Yenny", 0, 0, null, opcionesInicioAutor, opcionesInicioAutor);
				            
				            switch (opcionInicioAutorElegida) {
				                case 0:
				                    Libro proyectoElegido = Autor.SeleccionarLibro(usuario);
				                    
				                    String[] opcionesAutor = { "Subir Entrega", "Enviar Revision", "Ver estado de entrega","Salir" };
				                    int opcionAutorElegida = JOptionPane.showOptionDialog(null, "Seleccione una opcion", proyectoElegido.getTitulo(), 0, 0, null, opcionesAutor, opcionesAutor);
				                    
				                    switch (opcionAutorElegida) {
				                        case 0: 
				                            autor.SubirEntrega(proyectoElegido);
				                            break;
				                            
				                        case 1: 
				                            autor.SubirRevision(proyectoElegido);
				                            break;
				                            
				                        case 2: 
				                            JOptionPane.showMessageDialog(null, "La ultima entrega de " + proyectoElegido.getTitulo() + " esta " + autor.verEstadoEntrega(proyectoElegido));
				                            break;
				                            
				                        case 3: 
				                        	finSesion = true;
				                        	break;
				                            
				                    }
				                    break;
				                case 1:
				                    autor.CrearProyecto();
				                    break;

				                case 2:
				                	Libro proyectoElegido1 = Autor.SeleccionarLibro(usuario);
				                    autor.EliminarProyecto(proyectoElegido1);
				                    break;
				                    
				                case 3:
				                	finSesion = true;
				                	break;

				                default:
				                    break;
				            }
				        } while (!finSesion);
				        break; 
				        
				    case "editor":
				    	 boolean finSesion2 = false;
				    	 do {
				    		 Editor editor = (Editor) ControllerUsuario.BuscarUsuario(usuario.getId());
				    		 String[] opcionesInicioEditor = { "Calificar Manuscritos", "Enviar Feedback", "Ver Manuscritos(ID)", "Cerrar Sesion" };
				    		 int opcionInicioEditorElegida = JOptionPane.showOptionDialog(null, "", "Librerias Yenny", 0, 0, null, opcionesInicioEditor, opcionesInicioEditor);
					            
					            switch (opcionInicioEditorElegida) {
					            case 0:
					            	editor.revisarEntrega();
					            	break;
					            case 1:
					            	editor.enviarFeedback();
					            	break;
					            case 2:
					            	 String autorIdStr = JOptionPane.showInputDialog("Ingrese el ID del autor para mostrar sus libros:");
					                 if (autorIdStr != null) {
					                     try {
					                         int autorId = Integer.parseInt(autorIdStr);
					                         LinkedList<Libro> libros = ControllerLibro.MostrarLibros(autorId);
					                         String librosInfo = "Libros del Autor (ID: " + autorId + "):\n";
					                         for (Libro libro : libros) {
					                             librosInfo += "ID: " + libro.getId() +
					                                           ", Titulo: " + libro.getTitulo() +
					                                           ", Estado: " + libro.getEstadoLibro() + "\n";
					                         }
					                         if (libros.isEmpty()) {
					                             librosInfo = "No hay libros para el autor con ID: " + autorId;
					                         }
					                         JOptionPane.showMessageDialog(null, librosInfo);
					                     } catch (NumberFormatException e) {
					                         JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
					                     }
					                 }
					                 break;
					            case 3:
					            	finSesion2 = true;
					            	break;
					            	default:
					            		break;
					            }
					            } while (!finSesion2);
				    	 
				    	//Los estados para las entregas son "aprobada, rechazada, en revision, pendiente de correciones" asi como esta escrito
		
				    case "admin":
				        boolean finSesion3 = false;
				        do {
				        	String[] opcionesInicioAdmin = {
					                "CRUD Libros", 
					                "CRUD Usuarios",
					                "Cerrar Sesion"
					            };
				           
				            int OpcionInicioAdminElegida = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administración", 0, 0, null, opcionesInicioAdmin, opcionesInicioAdmin);

				            switch (OpcionInicioAdminElegida) {
				            case 0:
				            	 String[] opcionesLibrosAdmin = {
							                "Agregar Libro", 
							                "Mostrar Libros", 
							                "Buscar Libro", 
							                "Eliminar Libro", 
							                "Salir"
							            };
				            	int CRUDelegida = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administración", 0, 0, null, opcionesLibrosAdmin, opcionesLibrosAdmin);
				            	switch (CRUDelegida) {
								
								 case 0: 
						                Libro nuevoLibro = new Libro(null, null, null, null); 
						                
						                ControllerLibro.AgregarLibro(nuevoLibro);
						                break;
								 case 1:
									 LinkedList<Libro> libros = ControllerLibro.MostrarLibrosEnProceso(0);
								 case 2:
									 String idBuscarStr = JOptionPane.showInputDialog("Ingrese el ID del libro a buscar:");
						                int idBuscar = Integer.parseInt(idBuscarStr);
						                Libro libroBuscado = ControllerLibro.BuscarLibro(idBuscar);
						                if (libroBuscado != null) {
						                    JOptionPane.showMessageDialog(null, "Libro encontrado: " + libroBuscado.getTitulo());
						                } else {
						                    JOptionPane.showMessageDialog(null, "Libro no encontrado.");
						                }
						                break;
								 case 3:
									 String idEliminarStr = JOptionPane.showInputDialog("Ingrese el ID del libro a eliminar:");
						                int idEliminar = Integer.parseInt(idEliminarStr);
						                boolean eliminado = ControllerLibro.EliminarLibro(idEliminar);
						                if (eliminado) {
						                    JOptionPane.showMessageDialog(null, "Libro eliminado con éxito.");
						                } else {
						                    JOptionPane.showMessageDialog(null, "Error al eliminar el libro.");
						                }
						                break;
								 case 4:
									 finSesion3 = true;
								default:
									break;
								}
				            case 2:
				            	String[] opcionesUsuariosAdmin = {
				            			"Agregar Usuario", 
						                "Mostrar Usuarios", 
						                "Buscar Usuario", 
						                "Eliminar Usuario", 
						                "Salir"};
				            	int CRUDusuarios = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administración", 0, 0, null, opcionesUsuariosAdmin, opcionesUsuariosAdmin);
				            	switch (CRUDusuarios) {
								case 0:
									  Usuario nuevoUsuario = null;
									ControllerUsuario.AgregarUsuario(nuevoUsuario);
								
									break;
								case 1:
									LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
									break;
								case 2:
									 String idBuscarUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario a buscar:");
						                int idBuscarUsuario = Integer.parseInt(idBuscarUsuarioStr);
						                Usuario usuarioBuscado = ControllerUsuario.BuscarUsuario(idBuscarUsuario);
						                if (usuarioBuscado != null) {
						                    JOptionPane.showMessageDialog(null, "Usuario encontrado: " + usuarioBuscado.getNombre() + " " + usuarioBuscado.getApellido());
						                } else {
						                    JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
						                }
						                break;
								case 3:
									String idEliminarUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:");
					                int idEliminarUsuario = Integer.parseInt(idEliminarUsuarioStr);
					                ControllerUsuario.EliminarUsuario(idEliminarUsuario);
					                break;
								default:
									break;
								}
				            	
				            	break;

				                case 3: 
				                    finSesion3 = true;
				                    break;

				                default:
				                    break;
				            }
				        } while (!finSesion3);
				        break;                        
					}
				}
				break;
				
			case 1:
				Usuario.SingUp();		
				break;
				
			case 2:
				finSistema = true;
				break;
				
        	}    
		} while (!finSistema);      
    }
}