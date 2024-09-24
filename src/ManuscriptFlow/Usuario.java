package ManuscriptFlow;

import java.util.LinkedList;

public abstract class Usuario {

	private String nombre;
	private String apellido;
	private String email;
	private String contraseña;
	private String rol;
	private String estadoCuenta;
	private Libreria libreria;
	private static LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
	
	
	public Usuario(String nombre, String apellido, String email, String contraseña, String rol, String estadoCuenta, Libreria libreria) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
		this.rol = rol;
		this.estadoCuenta = estadoCuenta;
		this.libreria = libreria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getEstadoCuenta() {
		return estadoCuenta;
	}


	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}


	public Libreria getLibreria() {
		return libreria;
	}


	public void setLibreria(Libreria libreria) {
		this.libreria = libreria;
	}


	public static LinkedList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public static void setListaUsuarios(LinkedList<Usuario> listaUsuarios) {
		Usuario.listaUsuarios = listaUsuarios;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", contraseña="
				+ contraseña + ", rol=" + rol + ", estadoCuenta=" + estadoCuenta + ", libreria=" + libreria
				+ ", listaUsuarios=" + listaUsuarios + "]";
	}
	
	public abstract void RegistrarUsuario();
	
	public abstract Usuario IniciarSesion();

}
