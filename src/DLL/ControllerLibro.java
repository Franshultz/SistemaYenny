package DLL;

import java.sql.ResultSet;
import java.sql.SQLException;
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

public class ControllerLibro {
	private static Connection con = Conexion.getInstance().getConection();
	
	public static boolean AgregarLibro(Libro libro) {
		try {			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement
			("INSERT INTO `libro`(`titulo`, `autor_id`, `genero`, `precio`, `formato` , `estadoLibro`, `fechaLanzamiento`, `numeroVentas`, `stockDisponible`, `editor_id`) VALUES (?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, libro.getTitulo());
			statement.setInt(2, libro.getAutor().getId());
			statement.setString(3, libro.getGenero());
			statement.setDouble(4, libro.getPrecio());
			statement.setString(5, libro.getFormato());
			statement.setString(6, libro.getEstadoLibro());
			statement.setDate(7, (libro.getFechaLanzamiento() != null) ? java.sql.Date.valueOf(libro.getFechaLanzamiento()) : null);
			statement.setInt(8, libro.getNumeroVentas());
			statement.setInt(9, libro.getStockDisponible());
			statement.setObject(10, (libro.getEditor() != null) ? libro.getEditor().getId() : null, java.sql.Types.INTEGER);

			
			int filas = statement.executeUpdate();
			if(filas>0) {
				JOptionPane.showMessageDialog(null, "Se agregó libro nuevo con exito");
				return true;
			}			
		} catch (SQLException e) {
			System.out.println("Error, no se pudo agregar usuario");
		}			
		return false;
	}
	
	
	public static LinkedList<Libro> MostrarLibros(int fk) {
	    LinkedList<Libro> libros = new LinkedList<>();

	    try {
	        if (con == null || con.isClosed()) {
	            System.out.println("La conexión a la base de datos no está establecida.");
	            return libros; 
	        }
	        
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `libro` WHERE autor_id = ?");
	        statement.setInt(1, fk);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	      
	        	Usuario autor = ControllerUsuario.BuscarUsuario(resultSet.getInt("autor_id"));
	        	Usuario editor = ControllerUsuario.BuscarUsuario(resultSet.getInt("editor_id"));
	            Libro libro = new Libro(
	            	resultSet.getInt("id"),
	                resultSet.getString("titulo"),
	                new Autor(resultSet.getInt("autor_id"), autor.getNombre(), autor.getApellido(), autor.getEmail(), autor.getContraseña(), autor.getRol(), autor.getEstadoCuenta()),
	                resultSet.getString("isbn"),
	                resultSet.getString("genero"),
	                resultSet.getDouble("precio"),
	                resultSet.getString("formato"),
	                resultSet.getString("estadoLibro"),
	                resultSet.getDate("fechaLanzamiento") != null ? resultSet.getDate("fecha_lanzamiento").toLocalDate() : null,
	                resultSet.getInt("stockDisponible"),
	                new Editor(resultSet.getInt("editor_id"), editor.getNombre(), editor.getApellido(), editor.getEmail(), editor.getContraseña(), editor.getRol(), editor.getEstadoCuenta())
	            );
	            libros.add(libro); 
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
	        e.printStackTrace();
	        
	    } catch (Exception e) {
	        System.out.println("Ocurrió un error: " + e.getMessage());
	        e.printStackTrace();
	        
	    }

	    return libros;
	}
	
	public static LinkedList<Libro> MostrarLibrosEnProceso(int fk) {
		LinkedList<Libro> libros = new LinkedList<>();
		
		try {
			if (con == null || con.isClosed()) {
				System.out.println("La conexión a la base de datos no está establecida.");
				return libros; 
			}
			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `libro` WHERE autor_id = ?");
			statement.setInt(1, fk);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				Usuario autor = ControllerUsuario.BuscarUsuario(resultSet.getInt("autor_id"));
				Usuario editor = ControllerUsuario.BuscarUsuario(resultSet.getInt("editor_id"));
				Libro libro = new Libro(
						resultSet.getInt("id"),
						resultSet.getString("titulo"),
						new Autor(resultSet.getInt("autor_id"), autor.getNombre(), autor.getApellido(), autor.getEmail(), autor.getContraseña(), autor.getRol(), autor.getEstadoCuenta()),
						resultSet.getString("isbn"),
						resultSet.getString("genero"),
						resultSet.getDouble("precio"),
						resultSet.getString("formato"),
						resultSet.getString("estadoLibro"),
						resultSet.getDate("fechaLanzamiento") != null ? resultSet.getDate("fecha_lanzamiento").toLocalDate() : null,
								resultSet.getInt("stockDisponible"),
								new Editor(resultSet.getInt("editor_id"), editor.getNombre(), editor.getApellido(), editor.getEmail(), editor.getContraseña(), editor.getRol(), editor.getEstadoCuenta())
						);
				libros.add(libro); 
			}
			
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("Ocurrió un error: " + e.getMessage());
			e.printStackTrace();
			
		}
		
		return libros;
	}
	
	public static Libro BuscarLibro(int id) {
	    Libro nuevo = null;
	    
	    try {            
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `libro` WHERE id= ?");
	        statement.setLong(1, id); 
	        ResultSet resultSet = statement.executeQuery();
	        
	        
	        if (resultSet.next()) {
	        	Usuario autor = ControllerUsuario.BuscarUsuario(resultSet.getInt("autor_id"));
	        	Usuario editor = ControllerUsuario.BuscarUsuario(resultSet.getInt("editor_id"));
	            nuevo = new Libro(
	            		resultSet.getInt("id"),
	            		resultSet.getString("titulo"),
	            		new Autor(resultSet.getInt("autor_id"), autor.getNombre(), autor.getApellido(), autor.getEmail(), autor.getContraseña(), autor.getRol(), autor.getEstadoCuenta()),
	            		resultSet.getString("isbn"),
	            		resultSet.getString("genero"),
	            		resultSet.getDouble("precio"),
	            		resultSet.getString("formato"),
	            		resultSet.getString("estadoLibro"),
		                resultSet.getDate("fechaLanzamiento") != null ? resultSet.getDate("fecha_lanzamiento").toLocalDate() : null,
		                resultSet.getInt("stockDisponible"),
		                new Editor(resultSet.getInt("editor_id"), editor.getNombre(), editor.getApellido(), editor.getEmail(), editor.getContraseña(), editor.getRol(), editor.getEstadoCuenta())
	            		);
	        }
	        
	    } catch (Exception e) {
	        System.out.println("No se pudo encontrar el usuario. Error: " + e.getMessage());
	    }
	    return nuevo;
	}
	
	public static boolean EliminarLibro(int id) {
		try {	
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("DELETE FROM `libro` WHERE id= ? ");
			statement.setInt(1, id);
			int fila = statement.executeUpdate();
			if (fila>0) {
				JOptionPane.showMessageDialog(null, "Se ha borrado con exito");
				return true;
			}
		
		} catch (Exception e) {
			System.out.println("No se borró");	
			return false;
		}
		return false;

	}
}
