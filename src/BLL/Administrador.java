package BLL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DLL.Conexion;
import DLL.RegistrarAccion;
import DLL.Validaciones;


public class Administrador extends Usuario implements RegistrarAccion, Validaciones{
	private static Connection con = Conexion.getInstance().getConection();
	    private LinkedList<String> registroAcciones;
	
	    public Administrador(int id, String nombre, String apellido, String email, String contraseña, String rol, String estadoCuenta) {
	        super(id, nombre, apellido, email, contraseña, rol, estadoCuenta);
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


	
		
	}


	

	
