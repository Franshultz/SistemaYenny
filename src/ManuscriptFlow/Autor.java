package ManuscriptFlow;

import java.util.LinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Autor extends Usuario{

	private int autorID;
	private static int contadorAutores = 0;
	private LinkedList <Libro> listaLibrosPublicados= new LinkedList<Libro>();
	private LinkedList <Entrega> listaEntregas= new LinkedList<Entrega>();
	private int cantidadLibrosPublicados;
	private int entregasSubidas;
	private double reputacion;
	private int numeroVentasTotales;
	private double regaliasTotales;
	private String estadoManuscrito;
    private LocalDate recordatorio;
	
	public Autor() {
		super(null, null, null, null, null, null, null);
		this.autorID = contadorAutores++;
		this.listaLibrosPublicados = listaLibrosPublicados;
		this.listaEntregas = listaEntregas;
		this.cantidadLibrosPublicados = 0;
		this.entregasSubidas = 0;
		this.reputacion = 0;
		this.numeroVentasTotales = 0;
		this.regaliasTotales = 0;
        this.recordatorio = null;
	}
	

	public int getAutorID() {
		return autorID;
	}

	public void setAutorID(int autorID) {
		this.autorID = autorID;
	}

	public static int getContadorAutores() {
		return contadorAutores;
	}

	public static void setContadorAutores(int contadorAutores) {
		Autor.contadorAutores = contadorAutores;
	}

	public LinkedList<Libro> getListaLibrosPublicados() {
		return listaLibrosPublicados;
	}

	public void setListaLibrosPublicados(LinkedList<Libro> listaLibrosPublicados) {
		this.listaLibrosPublicados = listaLibrosPublicados;
	}
	
	public LinkedList<Entrega> getListaEntregas() {
		return listaEntregas;
	}


	public void setListaEntregas(LinkedList<Entrega> listaEntregas) {
		this.listaEntregas = listaEntregas;
	}


	public String getEstadoManuscrito() {
		return estadoManuscrito;
	}


	public void setEstadoManuscrito(String estadoManuscrito) {
		this.estadoManuscrito = estadoManuscrito;
	}


	public LocalDate getRecordatorio() {
		return recordatorio;
	}


	public void setRecordatorio(LocalDate recordatorio) {
		this.recordatorio = recordatorio;
	}


	public int getCantidadLibrosPublicados() {
		return cantidadLibrosPublicados;
	}

	public void setCantidadLibrosPublicados(int cantidadLibrosPublicados) {
		this.cantidadLibrosPublicados = cantidadLibrosPublicados;
	}

	public int getEntregasSubidas() {
		return entregasSubidas;
	}

	public void setEntregasSubidas(int entregasSubidas) {
		this.entregasSubidas = entregasSubidas;
	}

	public double getReputacion() {
		return reputacion;
	}

	public void setReputacion(double reputacion) {
		this.reputacion = reputacion;
	}

	public int getNumeroVentasTotales() {
		return numeroVentasTotales;
	}

	public void setNumeroVentasTotales(int numeroVentasTotales) {
		this.numeroVentasTotales = numeroVentasTotales;
	}

	public double getRegaliasTotales() {
		return regaliasTotales;
	}

	public void setRegaliasTotales(double regaliasTotales) {
		this.regaliasTotales = regaliasTotales;
	}

	@Override
	public String toString() {
		return "Autor [autorID=" + autorID + ", listaLibrosPublicados=" + listaLibrosPublicados
				+ ", cantidadLibrosPublicados=" + cantidadLibrosPublicados + ", entregasSubidas=" + entregasSubidas
				+ ", reputacion=" + reputacion + ", numeroVentasTotales=" + numeroVentasTotales + ", regaliasTotales="
				+ regaliasTotales + "]";
	}
	
	 public void SubirEntrega() {
		if (this.getListaEntregas().getLast().getEstado().equals("En revision")) {
			 JOptionPane.showMessageDialog(null, "No puede subir una nueva porque su ultima entrega todavia esta en revision");
		} else {
			String contenido = JOptionPane.showInputDialog("Ingrese el link del manuscrito:");
			Entrega nuevaEntrega = new Entrega(contenido);
			JOptionPane.showMessageDialog(null, "Entrega subida con éxito: " + nuevaEntrega.toString());
			this.getListaEntregas().add(nuevaEntrega);			 			
		}
	 }
	 
	 public void SubirRevision() {
		 if (this.getListaEntregas().getLast().getEstado().equals("Reenviar")) {			 
			 String contenido = JOptionPane.showInputDialog("Ingrese el link del manuscrito:");
			 this.getListaEntregas().getLast().setContenido(contenido);
			 this.getListaEntregas().getLast().setVersionManuscrito(this.getListaEntregas().getLast().getVersionManuscrito() + 0.1);	
			 this.getListaEntregas().getLast().setFechaEntrega(LocalDate.now());	
		} else {
			JOptionPane.showMessageDialog(null, "Solo puede enviar una revision si su ultima entrega lo requiere por el editor");
		}
	 }
    
    public void verEstadoManuscrito() {
        JOptionPane.showMessageDialog(null, "El estado actual del manuscrito es: " + this.getListaEntregas().getLast().getEstado());
    }

   
    public LocalDate agregarRecordatorio() {
        recordatorio = LocalDate.now().plusWeeks(1);
        JOptionPane.showMessageDialog(null, "Se ha agregado un recordatorio para la fecha: " + recordatorio);
        return recordatorio;
    }

	@Override
	public void RegistrarUsuario() {
		String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
		while (nombre.isEmpty()) {
			nombre = JOptionPane.showInputDialog("El nombre no puede estar vacio");		
		}
		
		String apellido = JOptionPane.showInputDialog("Ingrese su apellido");
		while (apellido.isEmpty()) {
			apellido = JOptionPane.showInputDialog("El apellido no puede estar vacio");		
		}
				
		String email = JOptionPane.showInputDialog("Ingrese su email");
		while (!email.contains("@")) {
			email = JOptionPane.showInputDialog("Su email debe contener '@' ");
		}
		
		String password = ""; 
		boolean validacionPassword = false;
		do {
			password = JOptionPane.showInputDialog("Ingrese su contraseña");
			while (password.isEmpty()) {
				password = JOptionPane.showInputDialog("La contraseña no puede estar vacia");		
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
				JOptionPane.showMessageDialog(null, "La contraseña debe contener una mayuscula y tres digitos como minimo");
			} else if (!contentUpperCase) {
				JOptionPane.showMessageDialog(null, "La contraseña debe contener una mayuscula como minimo");
			} else if (contDigit < 3) {
				JOptionPane.showMessageDialog(null, "La contraseña debe contener tres digitos como minimo");
			} else {
				validacionPassword = true;
			}
			
		} while (!validacionPassword);
		
		String estadoCuenta = "Activo";
		String rol = "Autor";
		
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setContraseña(password);
		this.setRol(rol);
		this.setEstadoCuenta(estadoCuenta);
		this.setLibreria(this.getLibreria());
				
		this.getListaUsuarios().add(this);
		
	}
	
	
	@Override
	public Usuario IniciarSesion() {
	    String email = JOptionPane.showInputDialog("Ingrese su email");
	    String password = JOptionPane.showInputDialog("Ingrese su contraseña");

	    for (Usuario usuario : Usuario.getListaUsuarios()) {
	        
	        if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(password) && usuario instanceof Autor) {
	            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " (" + usuario.getRol() + ")");
	            return usuario;
	        }
	    }

	    JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario o la contraseña es incorrecta");
	    return null; 
	}

}
