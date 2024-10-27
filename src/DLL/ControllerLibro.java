package DLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Autor;
import BLL.Editor;
import BLL.Entrega;
import BLL.Libro;
import BLL.Usuario;

public class ControllerLibro {
	private static Connection con = Conexion.getInstance().getConection();
	
	public static LinkedList<Libro> MostrarLibros() {
	    LinkedList<Libro> libros = new LinkedList<>();

	    try {
	        if (con == null || con.isClosed()) {
	            System.out.println("La conexión a la base de datos no está establecida.");
	            return libros; 
	        }
	        
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `libro`");
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	      
	        	Usuario autor = ControllerUsuario.BuscarUsuario(resultSet.getInt("autor_id"));
	        	Usuario editor = ControllerUsuario.BuscarUsuario(resultSet.getInt("editor_id"));
	            Libro libro = new Libro(
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
}
