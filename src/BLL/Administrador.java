package BLL;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.RegistrarAccion;
import DLL.Validaciones;


public class Administrador extends Usuario implements RegistrarAccion, Validaciones{

	    private LinkedList<String> registroAcciones;
	
	    public Administrador(int id, String nombre, String apellido, String mail, String password, String rol, String estadoCuenta) {
	        super(id, nombre, apellido, mail, password, rol, estadoCuenta);
	        this.registroAcciones = new LinkedList<>();
	    }


	public LinkedList<String> getRegistroAcciones() {
		return registroAcciones;
	}

	public void setRegistroAcciones(LinkedList<String> registroAcciones) {
		this.registroAcciones = registroAcciones;
	}

	
	@Override
	public String toString() {
		return "Administrador [registroAcciones=" + registroAcciones + "]";
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
	
	
	public void AdministrarLibros() {
			
	        String[] CRUD = {"Agregar", "Modificar", "Eliminar", "Salir"};
	        int seleccionCRUD = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administrar Libros",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, CRUD, CRUD[0]);;
	       
	        
	        switch (seleccionCRUD) {
	            case 0: 
	                Libro libro = ValidacionLibro();	                
	                break;
	            case 1: 
	                JOptionPane.showMessageDialog(null, "Modificar Libro");
	            case 2: 
	                JOptionPane.showMessageDialog(null, "Eliminar Libro");
	            case 3: 
	                break;
	
	            default:
	                JOptionPane.showMessageDialog(null, "ERROR");
	                break;
	        }
		}
	}
