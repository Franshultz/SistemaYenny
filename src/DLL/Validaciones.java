package DLL;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Autor;
import BLL.Editor;
import BLL.Libro;
import BLL.Usuario;
import GUI.LoginScreen;
import GUI.SignUpScreen;


public interface Validaciones {	
	
	static String ValidacionNombre(String nombre) {
            try {
                if (nombre == null || nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                } else {	
                	return nombre;
                }

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
            }
            return null;
    }
	
	
	static String ValidacionApellido(String apellido) {
	        try {
	        	
	            if (apellido == null) {
	                throw new IllegalArgumentException("\nDebes agregar un apellido.");
	            } else if (apellido.isEmpty()) {
	                throw new IllegalArgumentException("\nEl apellido no puede estar vacío.");
	            } else {
	            	return apellido;	            	
	            }	       

	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
	            return null;
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
	            return null;
	        }
	        
	}
	
	
	static String ValidacionEmail(String email) {
	        try {
	            
	        	
	            if (email == null) {
	                throw new IllegalArgumentException("\nDebes agregar un email.");
	            } else if (email.isEmpty()) {
	                throw new IllegalArgumentException("\nEl email no puede estar vacío.");
	            } else if (!email.contains("@")) {
	                throw new IllegalArgumentException("\nEl email debe contener '@'.");
	            } else if (!email.contains(".")) {
	                throw new IllegalArgumentException("\nEl email debe contener un dominio (ej. ejemplo.com).");
	            } else {
	            	return email;
	            }
	            

	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
	            return null;

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
	            return null;
	        }

	}
	
	
	static String ValidacionPassword(String password) {
	        try {

	            if (password == null) {
	                throw new IllegalArgumentException("Debes agregar una contraseña.");
	            } else if (password.isEmpty()) {
	                throw new IllegalArgumentException("La contraseña no puede estar vacía.");
	            }

	            boolean contentUpperCase = false;
	            int contDigit = 0;

	            for (int j = 0; j < password.length(); j++) {
	                if (Character.isUpperCase(password.charAt(j))) {
	                    contentUpperCase = true;
	                }
	                if (Character.isDigit(password.charAt(j))) {
	                    contDigit++;
	                }
	            }

	            if (!contentUpperCase && contDigit < 3) {
	                throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula y tres dígitos.");
	            } else if (!contentUpperCase) {
	                throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula.");
	            } else if (contDigit < 3) {
	                throw new IllegalArgumentException("La contraseña debe contener al menos tres dígitos.");
	            } else {
	            	return password;
	            }


	        } catch (IllegalArgumentException e) {
	            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
	            return null;
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado");
	            return null;
	        }

	}
	
	
	public static boolean ValidarDigito(String digito) {
		for (int i = 0; i < digito.length(); i++) {
			if (Character.isLetter(digito.charAt(i))) {
				JOptionPane.showMessageDialog(null, "Debe contener solo digitos numericos");
				return false;
			}
		}
		return true;
	}

	
}
