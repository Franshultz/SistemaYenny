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


	public boolean CrearProyecto() {
	    String titulo = JOptionPane.showInputDialog("Ingrese el nombre de su nuevo libro");
	    
	    String[] generosLiterarios = { 
	        "Fantasía", "Ciencia Ficción", "Romance", "Misterio", 
	        "Historia", "Biografía", "Poesía", "Thriller", 
	        "Drama", "Aventura", "Horror", "Novela Gráfica", 
	        "Ensayo", "Autoayuda", "Filosofía", "Literatura Infantil", 
	        "Suspenso", "Policial", "Erótico", "Distopía", "Paranormal", 
	        "Literatura Clásica", "Comedia", "Viajes", "Crónica"
	    };

	    String generoSeleccionado = (String) JOptionPane.showInputDialog(
	        null, "Seleccione un Género Literario:", 
	        "Géneros Literarios", JOptionPane.QUESTION_MESSAGE, 
	        null, generosLiterarios, generosLiterarios[0]);

	    // Validar que el usuario seleccionó un género
	    if (generoSeleccionado == null) {
	        JOptionPane.showMessageDialog(null, "No se seleccionó ningún género.");
	        return false; // Finalizar si no se seleccionó un género
	    }

	    LinkedList<Usuario> editores = ControllerUsuario.MostrarEditores();
	    ArrayList<String> resultados = new ArrayList<>();
	    for (Usuario editor : editores) {
	        String apellido = editor.getApellido();
	        String nombre = editor.getNombre();
	        String dato = apellido + " " + nombre;
	        resultados.add(dato);
	    }

	    String[] opcionesEditores = new String[resultados.size()];
	    opcionesEditores = resultados.toArray(opcionesEditores);

	    int seleccion = JOptionPane.showOptionDialog(null, "Seleccione un Editor:", 
	        "Editores", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
	        null, opcionesEditores, opcionesEditores[0]);

	    // Validar la selección del editor
	    if (seleccion != JOptionPane.CLOSED_OPTION && seleccion >= 0) {
	        String seleccionEditor = opcionesEditores[seleccion];
	        
	        Usuario editorElegido = null;
	        for (Usuario editor : editores) {
	            String apellido = editor.getApellido();
	            String nombre = editor.getNombre();
	            String dato = apellido + " " + nombre;

	            if (dato.equals(seleccionEditor)) {
	                editorElegido = editor;
	                break;
	            }
	        }

	        // Verificar que se seleccionó un editor
	        if (editorElegido == null) {
	            JOptionPane.showMessageDialog(null, "No se seleccionó ningún editor.");
	            return false; // Finalizar si no se seleccionó un editor
	        }

	        // Aquí debes asegurarte de que `this` sea una instancia correcta de `Autor`
	        Libro libro = new Libro(titulo, this, generoSeleccionado, (Editor) editorElegido);
	        return ControllerLibro.AgregarLibro(libro);
	    } else {
	        JOptionPane.showMessageDialog(null, "No se seleccionó ningún editor.");
	    }

	    return false; 
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
		if (ultimaEntrega.getEstado().equals("en revision") || ultimaEntrega.getEstado().equals("rechazada") || ultimaEntrega.getEstado().equals("pendiente de correcciones")) {
			JOptionPane.showMessageDialog(null, "No se puede subir una nueva entrega porque su ultima entrega esta " + ultimaEntrega.getEstado());
			return false;
		} else {			
			String contenido = JOptionPane.showInputDialog("Ingrese el link de su entrega");
			Entrega entrega = new Entrega((Autor) ControllerUsuario.BuscarUsuario(this.getId()), contenido, libro);		
			return ControllerEntrega.AgregarEntrega(entrega);
		}
	}
	 
	
	 public boolean SubirRevision(Libro libro) {
		 Entrega ultimaEntrega = ControllerEntrega.BuscarUltimaEntrega(libro.getId(), libro.getAutor().getId());
		 if (ultimaEntrega.getEstado().equals("pendiente de correcciones") || ultimaEntrega.getEstado().equals("rechazada")) {
			 String contenido = JOptionPane.showInputDialog("Ingrese el link de su entrega");
			 return ControllerEntrega.AgregarRevision(ultimaEntrega, contenido);
		} else {
			JOptionPane.showMessageDialog(null, "No se puede agregar revision a una entrega que esta " + ultimaEntrega.getEstado());
			return false;
		}
	 }
    
	 
    public String verEstadoEntrega(Libro libro) {
		Entrega ultimaEntrega = ControllerEntrega.BuscarUltimaEntrega(libro.getId(), libro.getAutor().getId());
		String estado = ultimaEntrega.getEstado();
        return  estado;
    }

   
    public LocalDate agregarRecordatorio() {
        recordatorio = LocalDate.now().plusWeeks(1);
        JOptionPane.showMessageDialog(null, "Se ha agregado un recordatorio para la fecha: " + recordatorio);
        return recordatorio;
    }
    

}
