package GUI;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Autor;
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
				    	//Los estados para las entregas son "aprobada, rechazada, en revision, pendiente de correciones" asi como esta escrito
				        break;
				    case "admin":
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