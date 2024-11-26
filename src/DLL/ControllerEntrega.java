package DLL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Administrador;
import BLL.Autor;
import BLL.Editor;
import BLL.Entrega;
import BLL.Libro;
import BLL.Usuario;



public class ControllerEntrega {
	private static Connection con = Conexion.getInstance().getConection();
	
	public static boolean AgregarEntrega(Entrega entrega) {
		try {			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement
			("INSERT INTO `entrega`(`contenido`, `estado`, `feedback`, `fechaEntrega`, `usuario_id`, `libro_id`) VALUES (?,?,?,?,?,?)");
			statement.setString(1, entrega.getContenido());
			statement.setString(2, entrega.getEstado());
			statement.setString(3, entrega.getFeedback());
			statement.setDate(4, Date.valueOf(entrega.getFechaEntrega()));
			statement.setInt(5, entrega.getAutor().getId());
			statement.setInt(6, entrega.getLibro().getId());
			
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
	
	
	public static boolean AgregarRevision(Entrega entrega, String contenido) {
		try {			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement
			("UPDATE `entrega` SET `contenido`= ?,`estado`= ?,`feedback`= ?,`fechaEntrega`= ?,`usuario_id`= ?,`libro_id`= ? WHERE 1");
			statement.setString(1, entrega.getContenido());
			statement.setString(2, "En revision");
			statement.setString(3, "No hay feedback para esta entrega todavia");
			statement.setDate(4, Date.valueOf(LocalDate.now()));
			statement.setInt(5, entrega.getAutor().getId());
			statement.setInt(6, entrega.getLibro().getId());
			
			int filas = statement.executeUpdate();
			if(filas>0) {
				JOptionPane.showMessageDialog(null, "Se agregó nueva revision de la misma entrega con exito");
				return true;
			}			
		} catch (SQLException e) {
			System.out.println("Error, no se pudo agregar revision");	
			return false;
		}
		return false;			
	}
	
	
	public static Entrega BuscarUltimaEntrega(int libro_id, int usuario_id) {
	    Entrega entrega = null;
	    
	    try {            
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "SELECT * FROM `entrega` WHERE libro_id = ? AND usuario_id = ? ORDER BY id DESC LIMIT 1"
	        );
	        statement.setInt(1, libro_id); 
	        statement.setInt(2, usuario_id); 
	        
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {  
	            Autor autor = (Autor) ControllerUsuario.BuscarUsuario(resultSet.getInt("usuario_id"));
	            Libro libro = ControllerLibro.BuscarLibro(resultSet.getInt("libro_id"));
	            
	            entrega = new Entrega(
	                resultSet.getInt("id"),
	                autor,
	                resultSet.getString("contenido"),
	                resultSet.getString("estado"),
	                resultSet.getString("feedback"),
	                resultSet.getDate("fechaEntrega").toLocalDate(),
	                libro
	            );
	        } else {
	            System.out.println("No se encontró ninguna entrega para el libro y autor especificados.");
	        }
	        
	    } catch (Exception e) {
	        System.out.println("No se pudo encontrar la entrega. Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return entrega;
	}
	
	
	public static LinkedList<Entrega> MostrarEntregas() {
	    LinkedList<Entrega> entregas = new LinkedList<>();

	    try {
	        if (con == null || con.isClosed()) {
	            System.out.println("La conexión a la base de datos no está establecida.");
	            return entregas; 
	        }
	        
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `entrega` WHERE ");
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	Libro libro = ControllerLibro.BuscarLibro(resultSet.getInt("libro_id"));
	        	Autor autor = (Autor) ControllerUsuario.BuscarUsuario(resultSet.getInt("usuario_id"));
	            Entrega entrega = new Entrega(
	                resultSet.getInt("id"),
	                autor,
	                resultSet.getString("contenido"),
	                resultSet.getString("estado"),
	                resultSet.getString("feedback"),
	                resultSet.getDate("fechaEntrega").toLocalDate(),
	                libro
	            );

	            entregas.add(entrega); 
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

}
