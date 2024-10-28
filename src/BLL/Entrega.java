package BLL;

import java.time.LocalDate;

public class Entrega {

    private int id;
    private Autor autor;
    private String contenido;
    private String estado;
    private String feedback;
    private LocalDate fechaEntrega;
    private Libro libro;

    public Entrega(int id, Autor autor, String contenido, String estado, String feedback, LocalDate fechaEntrega, Libro libro) {
    	this.id = id;
    	this.autor = autor;
    	this.contenido = contenido;
    	this.estado = estado;
    	this.feedback = feedback;
    	this.fechaEntrega = fechaEntrega;
    	this.libro = libro;
    }
    
    public Entrega(Autor autor, String contenido, Libro libro) {
    	this.autor = autor;
        this.contenido = contenido;
        this.estado = "en revision";
        this.feedback = "No hay feedback para esta entrega todavia";
        this.fechaEntrega = LocalDate.now();
        this.libro = libro;
    }

    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", autor=" + autor + ", contenido=" + contenido + ", estado=" + estado
				+ ", feedback=" + feedback + ", fechaEntrega=" + fechaEntrega + ", libro=" + libro + "]";
	}

	

}
