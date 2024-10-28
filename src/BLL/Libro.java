package BLL;

import java.time.LocalDate;

public class Libro {

	private int id;
	private String titulo;
	private Autor autor;
	private String isbn;
	private String genero;
	private double precio;	
	private String formato;
	private String estadoLibro;
	private LocalDate fechaLanzamiento;
	private int numeroVentas;
	private int stockDisponible;
	private Editor editor;
	
	public Libro(int id, String titulo, Autor autor, String isbn, String genero, double precio, String formato,
			String estadoLibro, LocalDate fechaLanzamiento, int stockDisponible, Editor editor) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.genero = genero;
		this.precio = precio;
		this.formato = formato;
		this.estadoLibro = estadoLibro;
		this.fechaLanzamiento = fechaLanzamiento;
		this.numeroVentas = 0;
		this.stockDisponible = stockDisponible;
		this.editor = editor;
	}
	
	public Libro(String titulo, Autor autor, String genero, Editor editor) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = "";
		this.genero = genero;
		this.precio = 0.0;
		this.formato = "";
		this.estadoLibro = "En proceso";
		this.fechaLanzamiento = null;
		this.numeroVentas = 0;
		this.stockDisponible = 0;
		this.editor = editor;
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getEstadoLibro() {
		return estadoLibro;
	}

	public void setEstadoLibro(String estadoLibro) {
		this.estadoLibro = estadoLibro;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public int getNumeroVentas() {
		return numeroVentas;
	}

	public void setNumeroVentas(int numeroVentas) {
		this.numeroVentas = numeroVentas;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}


	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + ", genero=" + genero + ", precio="
				+ precio + ", formato=" + formato + ", estadoLibro=" + estadoLibro + ", fechaLanzamiento="
				+ fechaLanzamiento + ", numeroVentas=" + numeroVentas + ", stockDisponible=" + stockDisponible
				+ ", editor=" + editor + "]";
	}
	
	
	
}
