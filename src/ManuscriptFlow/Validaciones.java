package ManuscriptFlow;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public interface Validaciones {
	

	default Libro ValidacionLibro(){
		String titulo = JOptionPane.showInputDialog("Ingrese un titulo.");	
		while (titulo.isEmpty()) {
			titulo = JOptionPane.showInputDialog("El titulo no puede estar vacio. \nIngrese un titulo valido");			
		}
		
		
		LinkedList<String> listaAutores = new LinkedList<>();
		for (Usuario usuario : Usuario.getListaUsuarios()) {
			if (usuario.getRol().equals("Autor")) {
				listaAutores.add(usuario.getNombre() + " " + usuario.getApellido());
			}
		}		
		String[] autoresNombre = listaAutores.toArray(new String[0]);
		
		String autorSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un autor", "Autores", JOptionPane.QUESTION_MESSAGE, null, autoresNombre, autoresNombre[0]);
		String[] nombreApellidoAutor = autorSeleccionado.split(" ");
		String nombreAutor = nombreApellidoAutor[0];
		String ApellidoAutor = nombreApellidoAutor[1];
		
		Autor autor = null;
		for (Usuario usuario : Usuario.getListaUsuarios()) {
			if (usuario.getNombre().equals(nombreAutor) && usuario.getApellido().equals(ApellidoAutor)) {
				autor = (Autor) usuario;
			}
		}
		
		String isbn;
		boolean validacionIsbn;
		do {
		    validacionIsbn = true;  
		    isbn = JOptionPane.showInputDialog("Ingrese el código ISBN del libro:");

		    while (isbn.isEmpty()) {
		        isbn = JOptionPane.showInputDialog("El código no puede estar vacío. \nIngrese un código válido:");
		    }

		    for (int j = 0; j < isbn.length(); j++) {
		        if (Character.isLetter(isbn.charAt(j))) {
		            validacionIsbn = false;
		            JOptionPane.showMessageDialog(null, "El código ISBN no debe contener letras. Inténtelo nuevamente.");
		            break;
		        }
		    }
		} while (!validacionIsbn);
		
		
		String[] generosLiterarios = {
			    "Ficción",
			    "No ficción",
			    "Fantasía",
			    "Ciencia ficción",
			    "Romance",
			    "Misterio",
			    "Terror",
			    "Aventura",
			    "Histórico",
			    "Poesía",
			    "Biografía",
			    "Drama",
			    "Literatura juvenil",
			    "Thriller",
			    "Autobiografía"
			};		
		String genero = (String) JOptionPane.showInputDialog(null, "Seleccione un genero", "Generos", JOptionPane.QUESTION_MESSAGE, null, generosLiterarios, generosLiterarios[0]);
		
		
		
		boolean validacionPrecio;
		String precioStr = "";
		do {			
			validacionPrecio = true;
			precioStr = JOptionPane.showInputDialog("Ingrese un precio");
			validacionPrecio = ValidarDigito(precioStr);			
		} while (!validacionPrecio);
		double precio = Double.parseDouble(precioStr);
		
		
		String[] formatosLibro = {
			    "Físico - Tapa Dura",
			    "Físico - Tapa Blanda",
			    "Digital",
			    "Audiolibro",
			    "E-book"
			};
		String formato = (String) JOptionPane.showInputDialog(null, "Seleccione un formato", "Formato", JOptionPane.QUESTION_MESSAGE, null, formatosLibro, formatosLibro[0]);
		
		
		String[] estadosLibro = {
			    "Borrador",
			    "En Revisión",
			    "Aprobado",
			    "Publicado",
			    "En Venta",
			    "Agotado",
			    "Descontinuado"
			};
		String estado = (String) JOptionPane.showInputDialog(null, "Seleccione un estado", "Estado", JOptionPane.QUESTION_MESSAGE, null, estadosLibro, estadosLibro[0]);
		
		
		boolean validacionFechaLanzamiento = true;
		LocalDate fechaLanzamiento = null;

		do {
		    int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea publicar una fecha de lanzamiento?", "Fecha de Lanzamiento", JOptionPane.YES_NO_OPTION);

		    if (respuesta == JOptionPane.YES_OPTION) {
		        boolean fechaValida = false;

		        while (!fechaValida) {
		            String diaInput = JOptionPane.showInputDialog("Ingrese el día de lanzamiento (1-31):");
		            String mesInput = JOptionPane.showInputDialog("Ingrese el mes de lanzamiento (1-12):");
		            String anioInput = JOptionPane.showInputDialog("Ingrese el año de lanzamiento");

		            if (ValidarDigito(diaInput) && ValidarDigito(mesInput) && ValidarDigito(anioInput)) {
		                int dia = Integer.parseInt(diaInput);
		                int mes = Integer.parseInt(mesInput);
		                int anio = Integer.parseInt(anioInput);

		                try {
		                    fechaLanzamiento = LocalDate.of(anio, mes, dia);
		                    fechaValida = true;
		                    validacionFechaLanzamiento = false; 
		                    JOptionPane.showMessageDialog(null, "La fecha de lanzamiento ingresada es: " + fechaLanzamiento);
		                } catch (DateTimeException e) {
		                    JOptionPane.showMessageDialog(null, "Fecha no válida: " + e.getMessage());
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "La entrada debe contener solo dígitos.");
		            }
		        }
		    } else {
		        validacionFechaLanzamiento = false; 
		    }
		    
		} while (validacionFechaLanzamiento);
		
		
		boolean validacionStock;
		String stockStr;
		do {			
			validacionStock = true;
			stockStr = JOptionPane.showInputDialog("Ingrese un numero de stock");
			validacionStock = ValidarDigito(stockStr);
		} while (!validacionStock);
		int stock = Integer.parseInt(stockStr);
		
		
		LinkedList<String> listaEditores = new LinkedList<>();
		for (Usuario usuario : Usuario.getListaUsuarios()) {
			if (usuario.getRol().equals("Editor")) {
				listaEditores.add(usuario.getNombre() + " " + usuario.getApellido());
			}
		}		
		String[] editoresNombre = listaEditores.toArray(new String[0]);
		
		String editorSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un Editor", "Autores", JOptionPane.QUESTION_MESSAGE, null, editoresNombre, editoresNombre[0]);
		String[] nombreApellidoEditor = editorSeleccionado.split(" ");
		String nombreEditor = nombreApellidoEditor[0];
		String ApellidoEditor = nombreApellidoEditor[1];
		
		Editor editor = null;
		for (Usuario usuario : Usuario.getListaUsuarios()) {
			if (usuario.getNombre().equals(nombreEditor) && usuario.getApellido().equals(ApellidoEditor)) {
				editor = (Editor) usuario;
			}
		}
			
		 return new Libro(titulo, autor, isbn, genero, precio, formato, estado, fechaLanzamiento, stock, editor);
	}

	
	
	
	
	default String ValidacionNombre(String nombre) {
		
		while (nombre.isEmpty()) {
			nombre = JOptionPane.showInputDialog("El nombre no puede estar vacio. \n Ingrese un nombre");			
		}
		
		return nombre;
	}
	
	default String ValidacionApellido(String apellido) {
		
		while (apellido.isEmpty()) {
			apellido = JOptionPane.showInputDialog("El apellido no puede estar vacio. \n Ingrese un apelido");			
		}
		
		return apellido;
	}
	
	default String ValidacionEmail(String email) {
		
		while (email.isEmpty()) {
			email = JOptionPane.showInputDialog("El email no puede estar vacio. \n Ingrese un email");			
		}
		
		while (!email.contains("@")) {
			email = JOptionPane.showInputDialog("Su email debe contener '@' \n Ingrese un email valido");
		}
		
		return email;
	}
	
	
	default String ValidacionPassword(String password) {
	    boolean validacionPassword = false;

	    do {			
	        while (password == null || password.isEmpty()) {
	            password = JOptionPane.showInputDialog("La contraseña no puede estar vacía. \nIngrese una contraseña");
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
	            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos una mayúscula y tres dígitos.");
	        } else if (!contentUpperCase) {
	            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos una mayúscula.");
	        } else if (contDigit < 3) {
	            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos tres dígitos.");
	        } else {
	            validacionPassword = true;
	        }

	        if (!validacionPassword) {
	            password = JOptionPane.showInputDialog("La contraseña no es válida. \nIngrese una nueva contraseña");
	        }

	    } while (!validacionPassword);
	    
	    return password;
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
