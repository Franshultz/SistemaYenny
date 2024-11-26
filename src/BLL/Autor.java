package BLL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import DLL.ControllerEntrega;
import DLL.ControllerLibro;
import DLL.ControllerUsuario;
import DLL.Validaciones;

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


	public boolean CrearProyecto(String titulo, Autor usuario ,String genero, String editor) {
	    LinkedList<Usuario> editores = ControllerUsuario.MostrarEditores();
	    Usuario editorElegido = null;

	    // Buscar el editor en la lista de editores
	    for (Usuario i : editores) {
	        String apellido = i.getApellido();
	        String nombre = i.getNombre();
	        String dato = nombre + " " + apellido;

	        if (dato.equals(editor)) {
	            editorElegido = i;
	            break;
	        }
	    }

	    // Verificar que se haya encontrado un editor
	    if (editorElegido == null) {
	        JOptionPane.showMessageDialog(null, "No se seleccionó un editor válido.");
	        return false;
	    }

	    // Verificar que el editor encontrado sea una instancia de Editor
	    if (!(editorElegido instanceof Editor)) {
	        JOptionPane.showMessageDialog(null, "El usuario seleccionado no es un editor válido.");
	        return false;
	    }

	    // Crear el objeto Libro con los datos capturados
	    
	    Libro libro = new Libro(titulo, usuario, genero, (Editor) editorElegido);
	    return ControllerLibro.AgregarLibro(libro);
	}

	
	
	public static Libro SeleccionarLibro(Usuario usuario){
		LinkedList<Libro> libros = ControllerLibro.MostrarLibros(usuario.getId());
        ArrayList<String> resultados = new ArrayList<>();
        
        for (Libro libro : libros) {
            Autor autor1 = libro.getAutor(); 
            String apellido = autor1.getApellido();
            String nombre = autor1.getNombre();
            String titulo = libro.getTitulo();            
            String dato = apellido + " " + nombre + " - " + titulo;                          
            resultados.add(dato);
        }
        
        String[] opcionesTitulos = new String[resultados.size()];
        opcionesTitulos = resultados.toArray(opcionesTitulos);
        
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione un Proyecto:", 
                "Libros", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, opcionesTitulos, opcionesTitulos[0]);
        
        String seleccionTitulo = opcionesTitulos[seleccion];
        
        Libro proyectoElegido = null;
        for (Libro libro : libros) {
            Autor autor1 = libro.getAutor(); 
            String apellido = autor1.getApellido();
            String nombre = autor1.getNombre();
            String titulo = libro.getTitulo();            
            String dato = apellido + " " + nombre + " - " + titulo;
            
            if (dato.equals(seleccionTitulo)) {
                int id_libro = libro.getId();
                return proyectoElegido = ControllerLibro.BuscarLibro(id_libro);                           
            }
        }
        
        return proyectoElegido;	
	}
	
	
	public boolean EliminarProyecto(Libro libro) {
		int respuesta = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de eliminar el proyecto "+ libro.getTitulo() + "?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            return ControllerLibro.EliminarLibro(libro.getId());
        }
		return false;
	}
	
	
	public boolean SubirEntrega(Libro libro) {
	    Entrega ultimaEntrega = ControllerEntrega.BuscarUltimaEntrega(libro.getId(), libro.getAutor().getId());

	    if (ultimaEntrega == null || ultimaEntrega.getEstado().equals("aprobada")) {
	        String contenido = JOptionPane.showInputDialog("Ingrese el link de su entrega");	      
	        Entrega entrega = new Entrega((Autor) ControllerUsuario.BuscarUsuario(this.getId()), contenido, libro);
	        return ControllerEntrega.AgregarEntrega(entrega);
	    } else {
	        JOptionPane.showMessageDialog(null, "No se puede subir una nueva entrega porque su última entrega está " + ultimaEntrega.getEstado());
	        return false;
	    }
	}
	 
	
	 public boolean SubirRevision(Libro libro) {
		 Entrega ultimaEntrega = ControllerEntrega.BuscarUltimaEntrega(libro.getId(), libro.getAutor().getId());
		 if (ultimaEntrega.getEstado().equals("pendiente de correcciones")) {
			 String contenido = JOptionPane.showInputDialog("Ingrese el link de su entrega");
			 return ControllerEntrega.AgregarRevision(ultimaEntrega, contenido);
		} else {
			JOptionPane.showMessageDialog(null, "No se puede agregar revision a una entrega que esta " + ultimaEntrega.getEstado());
			return false;
		}
	 }
    
	 
	 public String verEstadoEntrega(Libro libro) {
		    Entrega ultimaEntrega = ControllerEntrega.BuscarUltimaEntrega(libro.getId(), libro.getAutor().getId());

		    if (ultimaEntrega == null) {
		        return "en espera de primer entrega"; 
		    }

		    String estado = ultimaEntrega.getEstado();
		    return estado;
		}

   
    public LocalDate agregarRecordatorio() {
        recordatorio = LocalDate.now().plusWeeks(1);
        JOptionPane.showMessageDialog(null, "Se ha agregado un recordatorio para la fecha: " + recordatorio);
        return recordatorio;
    }
    

}
