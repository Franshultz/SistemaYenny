package DLL;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Conexion {
	private static final String URL ="jdbc:mysql://localhost:3307/yenny";
	private static final String USER = "root";
	private static final String PASSWORD ="";
	
	private static Conexion instance;   	 
	private static Connection connect;
	
		private Conexion() {
			try {
				connect =  (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
				System.out.println("Se conectó");
			} catch (SQLException e) {
				System.out.println("No se conectó");
	
			}
		}
		
		public static Conexion getInstance() {
			if(instance ==null) {
				instance = new Conexion();
			}
			return instance;	
		}
		
		public Connection getConection() {
			return connect;
		}
}
