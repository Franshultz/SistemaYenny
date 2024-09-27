package ManuscriptFlow;

import java.time.LocalDate;

public class Entrega {

    private int entregaID;
    private static int contadorEntregas = 0;
    private double versionManuscrito;
    private String contenido;
    private String estado;
    private String feedback;
    private LocalDate fechaEntrega;

    public Entrega(String contenido) {
        this.entregaID = ++contadorEntregas;
        this.versionManuscrito = 1;
        this.contenido = contenido;
        this.estado = "En revision";
        this.feedback = "No hay feedback para esta entrega todavia";
        this.fechaEntrega = LocalDate.now();

    }

	public int getEntregaID() {
		return entregaID;
	}

	public void setEntregaID(int entregaID) {
		this.entregaID = entregaID;
	}

	
	
	public static int getContadorEntregas() {
		return contadorEntregas;
	}

	public static void setContadorEntregas(int contadorEntregas) {
		Entrega.contadorEntregas = contadorEntregas;
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
		return "Entrega [entregaID=" + entregaID + ", versionManuscrito=" + versionManuscrito + ", contenido="
				+ contenido + ", estado=" + estado + ", feedback=" + feedback + ", fechaEntrega=" + fechaEntrega + "]";
	}

	
	
}
