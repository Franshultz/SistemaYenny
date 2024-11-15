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
		
        DisplayLogin displayLogin = new DisplayLogin(); 
        displayLogin.setVisible(true);
        
        // Esperar a que el login se complete
        while (SesionUsuario.getInstancia().getUsuarioLogueado() == null) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            	System.out.println("se ha producido un error");// Espera breve para evitar el ciclo rápido     
            }
        }

        Usuario usuario = SesionUsuario.getInstancia().getUsuarioLogueado();
	    if (usuario != null) {
	        JOptionPane.showMessageDialog(null, "Usuario logueado con éxito. ID: " + usuario.getId());
	        switch (usuario.getRol()) {
	            case "autor":
	                boolean finSesion = false;
	                do {
	                    Autor autor = (Autor) ControllerUsuario.BuscarUsuario(usuario.getId());
	                    String[] opcionesInicioAutor = { "Seleccionar proyecto", "Crear nuevo proyecto", "Eliminar proyecto", "Cerrar Sesion" };
	                    int opcionInicioAutorElegida = JOptionPane.showOptionDialog(null, "", "Librerías Yenny", 0, 0, null, opcionesInicioAutor, opcionesInicioAutor);

	                    switch (opcionInicioAutorElegida) {
	                        case 0:
	                            Libro proyectoElegido = Autor.SeleccionarLibro(usuario);
	                            String[] opcionesAutor = { "Subir Entrega", "Enviar Revision", "Ver estado de entrega", "Salir" };
	                            int opcionAutorElegida = JOptionPane.showOptionDialog(null, "Seleccione una opción", proyectoElegido.getTitulo(), 0, 0, null, opcionesAutor, opcionesAutor);

	                            switch (opcionAutorElegida) {
	                                case 0:
	                                    autor.SubirEntrega(proyectoElegido);
	                                    break;
	                                case 1:
	                                    autor.SubirRevision(proyectoElegido);
	                                    break;
	                                case 2:
	                                    JOptionPane.showMessageDialog(null, "La última entrega de " + proyectoElegido.getTitulo() + " está " + autor.verEstadoEntrega(proyectoElegido));
	                                    break;
	                                case 3:
	                                    finSesion = true;
	                                    break;
	                            }
	                            break;
	                        case 1:
	                            // Código para "Crear nuevo proyecto"
	                            break;
	                        case 2:
	                            // Código para "Eliminar proyecto"
	                            break;
	                        case 3:
	                            finSesion = true;
	                            break;
	                    }
	                } while (!finSesion);
	                break;
	                
	            case "editor":
	                // Implementar la lógica para el rol de editor
	                break;
	                
	            case "admin":
	                // Implementar la lógica para el rol de admin
	                break;                        
	        }
	    } else{
	        JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Revise sus credenciales e intente nuevamente.");
	    }    
	}
}