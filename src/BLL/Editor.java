package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DLL.Conexion;
import DLL.RegistrarAccion;
import DLL.Validaciones;

public class Editor extends Usuario implements RegistrarAccion, Validaciones{
	private static Connection con = Conexion.getInstance().getConection();
	private int manuscritosAsignados;
	private int cantidadManuscritosRevisados;
	private static double reputacion;
	private LinkedList<String> historialRevision; 
	
	public Editor(int id, String nombre, String apellido, String email, String contraseña, String rol, String estadoCuenta) {
	    super(id, nombre, apellido, email, contraseña, rol, estadoCuenta);
	    this.manuscritosAsignados = 0;
	    this.cantidadManuscritosRevisados = 0;
	    this.reputacion = 0;
	    this.historialRevision = new LinkedList<String>();
	}

	public int getManuscritosAsignados() {
		return manuscritosAsignados;
	}

	public void setManuscritosAsignados(int manuscritosAsignados) {
		this.manuscritosAsignados = manuscritosAsignados;
	}

	public int getCantidadManuscritosRevisados() {
		return cantidadManuscritosRevisados;
	}

	public void setCantidadManuscritosRevisados(int cantidadManuscritosRevisados) {
		this.cantidadManuscritosRevisados = cantidadManuscritosRevisados;
	}

	public double getReputacion() {
		return reputacion;
	}

	public void setReputacion(double reputacion) {
		this.reputacion = reputacion;
	}

	public LinkedList<String> getHistorialRevision() {
		return historialRevision;
	}

	public void setHistorialRevision(LinkedList<String> historialRevision) {
		this.historialRevision = historialRevision;
	}

	@Override
	public String toString() {
		return "Editor [manuscritosAsignados=" + manuscritosAsignados + ", cantidadManuscritosRevisados="
				+ cantidadManuscritosRevisados + ", reputacion=" + reputacion + ", historialRevision="
				+ historialRevision + "]";
	}
	
	
	public void revisarEntrega() {
		
        int entregaId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la entrega que desea revisar"));
        String nuevoEstado = JOptionPane.showInputDialog("Ingrese el nuevo estado (aprobada, rechazada, en revision, pendiente de correcciones)");

        try {
        	PreparedStatement statement = (PreparedStatement) con.prepareStatement ("UPDATE entrega SET estado = ? WHERE id = ?");
            statement.setString(1, nuevoEstado);
            statement.setInt(2, entregaId);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "La entrega ha sido actualizada a '" + nuevoEstado + "'.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la entrega con ID " + entregaId);
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado de la entrega: " + e.getMessage());
        }
    }

    
	 public static boolean enviarFeedback(int entregaId, String feedback) {
	        String query = "UPDATE Entregas SET feedback = ? WHERE id = ?"; 
	        try (
	             PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query)) {
	            
	            stmt.setString(1, feedback);
	            stmt.setInt(2, entregaId);

	            int filasActualizadas = stmt.executeUpdate();
	            return filasActualizadas > 0; 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
        
    public static List<Libro> getLibros() {
        List<Libro> libros = new ArrayList<>();

        try {
            PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT id, titulo, estadoLibro FROM libro");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String estadoLibro = rs.getString("estadoLibro");

               
                Libro libro = new Libro(id, titulo, null, estadoLibro, estadoLibro, reputacion, estadoLibro, estadoLibro, null, id, null);
                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los libros: " + e.getMessage());
        }

        return libros;
    }

	@Override
	public void RegistrarAccion(String accion) {
		// TODO Auto-generated method stub
		
	}
	
}
