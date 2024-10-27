package DLL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Entrega;
import BLL.Usuario;



public class ControllerEntrega {
	private static Connection con = Conexion.getInstance().getConection();
	
	public static boolean AgregarEntrega(Entrega entrega) {
		try {			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement
			("INSERT INTO `entrega`(`versionManuscrito`, `contenido`, `estado`, `feedback`, `fechaEntrega`, `usuario_id`) VALUES (?,?,?,?,?,?)");
			statement.setDouble(1, entrega.getVersionManuscrito());
			statement.setString(2, entrega.getContenido());
			statement.setString(3, entrega.getEstado());
			statement.setString(4, entrega.getFeedback());
			statement.setDate(5, Date.valueOf(entrega.getFechaEntrega()));
			statement.setInt(6, entrega.getAutor());
			
			int filas = statement.executeUpdate();
			if(filas>0) {
				JOptionPane.showMessageDialog(null, "Se agregó entrega nueva con exito");
				return true;
			}			
		} catch (SQLException e) {
			System.out.println("Error, no se pudo agregar entrega");	
			return false;
		}
		return false;			
	}
	
	
	
	public static LinkedList<Entrega> MostrarEntrega() {
	    LinkedList<Entrega> entregas = new LinkedList<>();

	    try {
	        if (con == null || con.isClosed()) {
	            System.out.println("La conexión a la base de datos no está establecida.");
	            return entregas; 
	        }
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `entrega`");
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            entregas.add((Entrega) resultSet);
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
	        e.printStackTrace();
	        
	    } catch (Exception e) {
	        System.out.println("Ocurrió un error: " + e.getMessage());
	        e.printStackTrace();
	        
	    }

	    return entregas;
	}

	
	
	public static void ActualizarEntrega(Entrega entrega) {
			
			try {			
				PreparedStatement statement = (PreparedStatement) 
						con.prepareStatement("UPDATE `persona` SET `nombre`=?,`rol`=?,`password`=? WHERE id = ?");
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getRol());
				statement.setString(3, usuario.getContraseña());
				statement.setInt(4, usuario.getId());
	
				int fila = statement.executeUpdate();
				if (fila>0) {
					JOptionPane.showMessageDialog(null, "Se actualizó");
				}
			
			} catch (Exception e) {
				System.out.println("No se borró");		
			}	
		}
	
}
