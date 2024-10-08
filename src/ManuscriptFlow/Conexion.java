package ManuscriptFlow;

public class Conexion {
	
	import java.sql.DriverManager;
	import java.sql.SQLException;

	import javax.swing.JOptionPane;

	import com.mysql.jdbc.Connection;
//private static Connection con = Conexion.getInstance().getConnection();
	public class Conexion {
		private static final String URL = "jdbc:mysql://localhost:3306/yenny";
		private static final String USER = "root";
		private static final String PASS = "";
		
		private static Conexion instance;
		private Connection con;
		
		private Conexion() {
			try {
				con = (Connection) DriverManager.getConnection(URL,USER,PASS);
				JOptionPane.showMessageDialog(null, "Se conectó correctamente");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "No se pudo conectar a la Base de Datos");
				
			}
		}
		public static Conexion getInstance() {
			if (instance==null) {
				instance = new Conexion();
			}
			return instance;
		}
		public Connection getConnection() {
			return con;
		}
		
		
	}


}
