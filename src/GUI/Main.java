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
						Autor autor = (Autor) ControllerUsuario.BuscarUsuario(usuario.getId());
						String[] opcionesInicioAutor = { "Seleccionar proyecto", "Salir"};
			        	int opcionInicioAutorElegida = JOptionPane.showOptionDialog(null, "", "Librerias Yenny", 0, 0, null, opcionesInicioAutor, opcionesInicioAutor);
						
						switch (opcionInicioAutorElegida) {
						case 0:
							LinkedList<Libro> libros = ControllerLibro.MostrarLibros();
							ArrayList<String> resultados = new ArrayList<>();
							
							for (Libro libro : libros) {
						          Autor autor1 = libro.getAutor(); 
						          String apellido = autor1.getApellido();
						          String nombre = autor1.getNombre();
						          String titulo = libro.getTitulo();
						            
						          String dato = apellido + " " + nombre + " - " + titulo;						            
						          resultados.add(dato);
						    }
							 
							String[] opcionesTitulos = new String[resultados.size()];
							opcionesTitulos = resultados.toArray(opcionesTitulos);
						    
						    int seleccion = JOptionPane.showOptionDialog(null, "Seleccione un Proyecto:", 
					                "Libros", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
					                null, opcionesTitulos, opcionesTitulos[0]);
							
							
							String[] opcionesAutor = { "Enviar Entrega", "Enviar Revision", "Salir" };
							int opcionAutorElegida = JOptionPane.showOptionDialog(null, "Sign Up - Sign In", "Librerias Yenny", 0, 0, null, opcionesAutor, opcionesAutor);
							
							switch (opcionAutorElegida) {
							case 0:
								autor.SubirEntrega();
								break;
								
							case 1:
								autor.SubirRevision();
								break;
							case 2:
								
								break;

							default:
								break;
							}
							
						case 1:
							
							break;
						}
					case "editor":
						
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