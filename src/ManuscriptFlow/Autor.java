package ManuscriptFlow;

import java.util.LinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Autor extends Usuario implements Validaciones{

	private LinkedList <Libro> listaLibrosPublicados;
	private LinkedList <Entrega> listaEntregas;
	private int cantidadLibrosPublicados;
	private int entregasSubidas;
	private double reputacion;
	private int numeroVentasTotales;
	private double regaliasTotales;
	private String estadoManuscrito;
    private LocalDate recordatorio;
	
	

    public Autor(int id, String nombre, String apellido, String email, String contraseña, String rol, String estadoCuenta) {
        super(id, nombre, apellido, email, contraseña, rol, estadoCuenta);
        this.listaLibrosPublicados = new LinkedList<Libro>();
        this.listaEntregas = new LinkedList<Entrega>();
        this.cantidadLibrosPublicados = 0;
        this.entregasSubidas = 0;
        this.reputacion = 0;
        this.numeroVentasTotales = 0;
        this.regaliasTotales = 0;
        this.recordatorio = null;
    }
    
    public Autor(String nombre, String apellido, String email, String contraseña, String estadoCuenta) {
    	super(nombre, apellido, email, contraseña, estadoCuenta);
    	this.listaLibrosPublicados = new LinkedList<Libro>();
    	this.listaEntregas = new LinkedList<Entrega>();
    	this.cantidadLibrosPublicados = 0;
    	this.entregasSubidas = 0;
    	this.reputacion = 0;
    	this.numeroVentasTotales = 0;
    	this.regaliasTotales = 0;
    	this.recordatorio = null;
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
		return "Autor [listaLibrosPublicados=" + listaLibrosPublicados + ", listaEntregas=" + listaEntregas
				+ ", cantidadLibrosPublicados=" + cantidadLibrosPublicados + ", entregasSubidas=" + entregasSubidas
				+ ", reputacion=" + reputacion + ", numeroVentasTotales=" + numeroVentasTotales + ", regaliasTotales="
				+ regaliasTotales + ", estadoManuscrito=" + estadoManuscrito + ", recordatorio=" + recordatorio + "]";
	}


	public void SubirEntrega() {
	    if (this.getListaEntregas().isEmpty() || !this.getListaEntregas().getLast().getEstado().equals("En revision")) {
	        String contenido = JOptionPane.showInputDialog("Ingrese el link del manuscrito:");
	        Entrega nuevaEntrega = new Entrega(contenido);
	        JOptionPane.showMessageDialog(null, "Entrega subida con éxito: " + nuevaEntrega.toString());
	        this.getListaEntregas().add(nuevaEntrega);			 			
	    } else {
	        JOptionPane.showMessageDialog(null, "No puede subir una nueva entrega porque su última está en revisión.");
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

}
