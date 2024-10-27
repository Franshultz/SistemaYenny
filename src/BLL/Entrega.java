package BLL;

import java.time.LocalDate;

public class Entrega {

    private int id;
    private int autor;
    private double versionManuscrito;
    private String contenido;
    private String estado;
    private String feedback;
    private LocalDate fechaEntrega;

    public Entrega(int id, double versionManuscrito, String contenido, String estado, String feedback, LocalDate fechaEntrega, String autor) {
    	this.id = id;
    	this.versionManuscrito = versionManuscrito;
    	this.contenido = contenido;
    	this.estado = estado;
    	this.feedback = feedback;
    	this.fechaEntrega = fechaEntrega;
    }
    
    public Entrega(int autor, String contenido) {
    	this.autor = autor;
        this.versionManuscrito = 0.0;
        this.contenido = contenido;
        this.estado = "En revision";
        this.feedback = "No hay feedback para esta entrega todavia";
        this.fechaEntrega = LocalDate.now();
    }

    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = autor;
	}

	public double getVersionManuscrito() {
		return versionManuscrito;
	}

	public void setVersionManuscrito(double versionManuscrito) {
		this.versionManuscrito = versionManuscrito;
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

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", versionManuscrito=" + versionManuscrito + ", contenido=" + contenido
				+ ", estado=" + estado + ", feedback=" + feedback + ", fechaEntrega=" + fechaEntrega + "]";
	}

}
