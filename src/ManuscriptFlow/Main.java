package ManuscriptFlow;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        boolean finSistema = false;
        do {
        	String[] opcionesInicio = { "Iniciar Sesion", "Registrarse", "Salir" };
        	int opcionInicioElegida = JOptionPane.showOptionDialog(null, "Sign Up - Sign In", "Librerias Yenny", 0, 0, null, opcionesInicio, opcionesInicio);
        	
        	switch (opcionInicioElegida) {
			case 0:
				Usuario.LogIn();
				break;
			case 1:
				Usuario.SingUp();		
				break;
			case 2:
				finSistema = true;
				break;
				
        	}
		} while (!finSistema);
        
    }    
}