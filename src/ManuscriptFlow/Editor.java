package ManuscriptFlow;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Editor extends Usuario implements RegistrarAccion, Validaciones{

	private int editorID;
	private static int contadorEditores = 0;
	private String especialidad;
	private int manuscritosAsignados;
	private int cantidadManuscritosRevisados;
	private double reputacion;
	private LinkedList<String> historialRevision = new LinkedList<String>();
	
	public Editor() {
		super(null, null, null, null, null, null, null);
		this.editorID = contadorEditores++;
		this.especialidad = "";
		this.manuscritosAsignados = 0;
		this.cantidadManuscritosRevisados = 0;
		this.reputacion = 0;
		this.historialRevision = historialRevision;
	}

	public int getEditorID() {
		return editorID;
	}

	public void setEditorID(int editorID) {
		this.editorID = editorID;
	}

	public static int getContadorEditores() {
		return contadorEditores;
	}

	public static void setContadorEditores(int contadorEditores) {
		Editor.contadorEditores = contadorEditores;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getManuscritosAsignados() {
		return manuscritosAsignados;
	}

	public void setManuscritosAsignados(int manuscritosAsignados) {
		this.manuscritosAsignados = manuscritosAsignados;
	}

	public int getCantidadManuscritosRevisados() {
		return cantidadManuscritosRevisados;
	}

	public void setCantidadManuscritosRevisados(int cantidadManuscritosRevisados) {
		this.cantidadManuscritosRevisados = cantidadManuscritosRevisados;
	}

	public double getReputacion() {
		return reputacion;
	}

	public void setReputacion(double reputacion) {
		this.reputacion = reputacion;
	}

	public LinkedList<String> getHistorialRevision() {
		return historialRevision;
	}

	public void setHistorialRevision(LinkedList<String> historialRevision) {
		this.historialRevision = historialRevision;
	}

	@Override
	public String toString() {
		return "Editor [editorID=" + editorID + ", especialidad=" + especialidad + ", manuscritosAsignados="
				+ manuscritosAsignados + ", cantidadManuscritosRevisados=" + cantidadManuscritosRevisados
				+ ", reputacion=" + reputacion + ", historialRevision=" + historialRevision + "]";
	}

	
	@Override
	public void RegistrarUsuario() {
		String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
		nombre = ValidacionNombre(nombre);
		
		String apellido = JOptionPane.showInputDialog("Ingrese su apellido");
		apellido = ValidacionApellido(apellido);
				
		String email = JOptionPane.showInputDialog("Ingrese su email");
		email = ValidacionEmail(email);
		

		String password = JOptionPane.showInputDialog("Ingrese su contraseña");
		password = ValidacionPassword(password);	
		
		String estadoCuenta = "Activo";
		String rol = "Editor";
		
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
		
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setContraseña(password);
		this.setRol(rol);
		this.setEstadoCuenta(estadoCuenta);
		this.setLibreria(this.getLibreria());
		this.setEspecialidad(genero);
		
		this.getListaUsuarios().add(this);
		
		historialRevision.add("El Editor" + getNombre() + " " + getApellido() + " se registro el " + LocalDate.now());
	}
	
	
	
	public void revisarEntrega() {
		
        String manuscrito = JOptionPane.showInputDialog("Ingrese el título del manuscrito que desea revisar");
        //Falta info
        JOptionPane.showMessageDialog(null, "El manuscrito '" + manuscrito + "' ha sido revisado.");
        cantidadManuscritosRevisados++;
        historialRevision.add(manuscrito + " revisado el " + LocalDate.now());
    }

    // Falta info
    public void enviarFeedback() {
        String feedback = JOptionPane.showInputDialog("Ingresar Comentarios");
         JOptionPane.showMessageDialog(null, "El comentario ha sido enviado.");
        
    }

    

	
	@Override
	public Usuario IniciarSesion() {
	    String email = JOptionPane.showInputDialog("Ingrese su email");
	    String password = JOptionPane.showInputDialog("Ingrese su contraseña");

	    for (Usuario usuario : Usuario.getListaUsuarios()) {
	        
	        if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(password) && usuario instanceof Editor) {
	            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " (" + usuario.getRol() + ")");
	            return usuario;
	        }
	    }

	    JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario o la contraseña es incorrecta");
	    return null; 
	}

	@Override
	public void RegistrarAccion(String accion) {
				
	}
	
}
