package ManuscriptFlow;

public class Libreria {

	private int libreriaID;
	private static int contadorLibrerias = 0;
	private String nombre;
	private String ubicacion;
	private static int cantidadLibros;
	private static int historialVentas;
	
	public Libreria(String nombre, String ubicacion) {
		this.libreriaID = ++contadorLibrerias;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.cantidadLibros = 0;
		this.historialVentas = 0;
	}

	
	public int getLibreriaID() {
		return libreriaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public static int getCantidadLibros() {
		return cantidadLibros;
	}

	public static void setCantidadLibros(int cantidadLibros) {
		Libreria.cantidadLibros = cantidadLibros;
	}

	public static int getHistorialVentas() {
		return historialVentas;
	}

	public static void setHistorialVentas(int historialVentas) {
		Libreria.historialVentas = historialVentas;
	}

	
	@Override
	public String toString() {
		return "Libreria [nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}
	
}
