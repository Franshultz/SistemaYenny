package BLL;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.RegistrarAccion;
import DLL.Validaciones;

public class Editor extends Usuario implements RegistrarAccion, Validaciones{

	private int manuscritosAsignados;
	private int cantidadManuscritosRevisados;
	private double reputacion;
	private LinkedList<String> historialRevision; 
	
	public Editor(int id, String nombre, String apellido, String email, String contraseña, String rol, String estadoCuenta) {
	    super(id, nombre, apellido, email, contraseña, rol, estadoCuenta);
	    this.manuscritosAsignados = 0;
	    this.cantidadManuscritosRevisados = 0;
	    this.reputacion = 0;
	    this.historialRevision = new LinkedList<String>();
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
		return "Editor [manuscritosAsignados=" + manuscritosAsignados + ", cantidadManuscritosRevisados="
				+ cantidadManuscritosRevisados + ", reputacion=" + reputacion + ", historialRevision="
				+ historialRevision + "]";
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
	public void RegistrarAccion(String accion) {
		// TODO Auto-generated method stub
		
	}
	
}
