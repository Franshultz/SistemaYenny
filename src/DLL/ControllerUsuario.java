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
import BLL.Usuario;

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
	            String rol = resultSet.getString("rol");
	            Usuario usuario = null;

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
	
	
	public static LinkedList<Usuario> MostrarEditores() {
	    LinkedList<Usuario> editores = new LinkedList<>();

	    try {
	        if (con == null || con.isClosed()) {
	            System.out.println("La conexión a la base de datos no está establecida.");
	            return editores; 
	        }

	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `usuario` WHERE rol = 'editor';");
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	           Usuario editor = new Editor(
	                        resultSet.getInt("id"),
	                        resultSet.getString("nombre"),
	                        resultSet.getString("apellido"),
	                        resultSet.getString("mail"),
	                        resultSet.getString("password"),
	                        resultSet.getString("rol"),
	                        resultSet.getString("estado_cuenta")
	                    	);

	            if (editores != null) {
	            	editores.add(editor);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Ocurrió un error: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return editores;
	}
	
	
	public static void ActualizarUsuario(Usuario usuario) {
		
		try {			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("UPDATE `usuario` SET `nombre`=?,`apellido`=?,`rol`=?,`password`=? WHERE id = ?");
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getApellido());
			statement.setString(3, usuario.getRol());
			statement.setString(4, usuario.getContraseña());
			statement.setInt(5, usuario.getId());

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
					con.prepareStatement("DELETE FROM `usuario` WHERE id= ? ");
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
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `usuario` WHERE id= ?");
	        statement.setLong(1, id); 
	        ResultSet resultSet = statement.executeQuery();
	        
	    
	        if (resultSet.next()) {
	            String rol = resultSet.getString("rol");
	            
	            switch (rol) {
	                case "admin":
	                    nuevo = new Administrador(
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
	                    nuevo = new Autor(
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
	                    nuevo = new Editor(
	                        resultSet.getInt("id"),
	                        resultSet.getString("nombre"),
	                        resultSet.getString("apellido"),
	                        resultSet.getString("mail"),
	                        resultSet.getString("password"),
	                        rol,
	                        resultSet.getString("estado_cuenta")
	                    );
	                    break;
	            }
	        }
	        
	    } catch (Exception e) {
	        System.out.println("No se pudo encontrar el usuario. Error: " + e.getMessage());
	    }
	    return nuevo;
	}
}