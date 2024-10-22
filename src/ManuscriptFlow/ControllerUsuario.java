package ManuscriptFlow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ControllerUsuario {
	private static Connection con = Conexion.getInstance().getConection();

	public static void AgregarUsuario(Usuario usuario) {
		try {			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement
			("INSERT INTO `usuario`(`nombre`, `apellido`, `mail`, `password`, `estado_cuenta`) VALUES (?,?,?,?,?)");
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getApellido());
			statement.setString(3, usuario.getEmail());
			statement.setString(4, usuario.getContraseña());
			statement.setString(5, usuario.getEstadoCuenta());
			
			int filas = statement.executeUpdate();
			if(filas>0) {
				JOptionPane.showMessageDialog(null, "Se agregó usuario nuevo con exito");
			}			
		} catch (SQLException e) {
			System.out.println("Error, no se pudo agregar usuario");	
		}			
	}
	
	
	public static LinkedList<Usuario> MostrarUsuarios() {
	    LinkedList<Usuario> usuarios = new LinkedList<>();

	    try {
	        if (con == null || con.isClosed()) {
	            System.out.println("La conexión a la base de datos no está establecida.");
	            return usuarios; 
	        }

	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `usuario`");
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	            String rol = resultSet.getString("rol");
	            Usuario usuario = null;
	            
	            LinkedList<String> generos = new LinkedList<>();
	            PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("SELECT * FROM `interes_literario` WHERE usuario_id =?" );
	            statement2.setInt(1, id);
	            ResultSet resultSet2 = statement2.executeQuery();
	            while (resultSet2.next()) {
	            	String genero = null;
					genero = resultSet2.getString("tipo_interes");
					if (genero != null) {
		                generos.add(genero);
		            }
				}

	            switch (rol) {
	                case "admin":
	                    usuario = new Administrador(
	                        resultSet.getInt("id"),
	                        resultSet.getString("nombre"),
	                        resultSet.getString("apellido"),
	                        resultSet.getString("mail"),
	                        resultSet.getString("password"),
	                        rol,
	                        resultSet.getString("estado_cuenta")                      
	                        );
	                    break;
	                case "autor":
	                    usuario = new Autor(
	                        resultSet.getInt("id"),
	                        resultSet.getString("nombre"),
	                        resultSet.getString("apellido"),
	                        resultSet.getString("mail"),
	                        resultSet.getString("password"),
	                        rol,
	                        resultSet.getString("estado_cuenta")
	                        );
	                    break;
	                case "editor":
	                    usuario = new Editor(
	                        resultSet.getInt("id"),
	                        resultSet.getString("nombre"),
	                        resultSet.getString("apellido"),
	                        resultSet.getString("mail"),
	                        resultSet.getString("password"),
	                        rol,
	                        resultSet.getString("estado_cuenta")
	                    	);
	                    break;
	                default:
	                    System.out.println("Rol no reconocido: " + rol);
	                    break;
	            }

	            if (usuario != null) {
	                usuarios.add(usuario);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Ocurrió un error: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return usuarios;
	}
	
	
	public static void ActualizarUsuario(Usuario usuario) {
		
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
	
	
	public static void EliminarUsuario(int id) {
		Usuario nuevo = null;
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("DELETE FROM `persona` WHERE id= ? ");
			statement.setInt(1, id);
			int fila = statement.executeUpdate();
			if (fila>0) {
				JOptionPane.showMessageDialog(null, "Se borró");
			}
		
		} catch (Exception e) {
			System.out.println("No se borró");		
		}

	}
	
	
	public static Usuario BuscarUsuario(int id) {
		Usuario nuevo = null;
		
		try {			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `persona` WHERE id= ? ");
			statement.setInt(1, id); 
			ResultSet resultSet = statement.executeQuery();
			
			String rol = resultSet.getString("rol");
			
			 LinkedList<String> generos = new LinkedList<>();
	            PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("SELECT * FROM `interes_literario` WHERE usuario_id =?" );
	            statement2.setInt(1, id);
	            ResultSet resultSet2 = statement2.executeQuery();
	            while (resultSet2.next()) {
	            	String genero = null;
					genero = resultSet2.getString("tipo_interes");
					if (genero != null) {
		                generos.add(genero);
		            }
				}
			switch (rol) {
			case "admin":
				if (resultSet.next()) {
					nuevo = new Administrador(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							resultSet.getString("mail"),
							resultSet.getString("password"),
							resultSet.getString("rol"),
							resultSet.getString("estado_cuenta")
							);
				}
				break;
			case "autor":
				if (resultSet.next()) {
					nuevo = new Autor(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							resultSet.getString("mail"),
							resultSet.getString("password"),
							resultSet.getString("rol"),
							resultSet.getString("estado_cuenta")
							);
				}
				break;
			case "editor":
				if (resultSet.next()) {
					nuevo = new Editor(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							resultSet.getString("mail"),
							resultSet.getString("password"),
							resultSet.getString("rol"),
							resultSet.getString("estado_cuenta")
							);
				}
				break;
			}
            
			
		
		} catch (Exception e) {
			System.out.println("No se agregó");		
		}
		return nuevo;
	}	
}