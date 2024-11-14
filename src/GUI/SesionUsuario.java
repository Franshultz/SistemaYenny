package GUI;

import BLL.Usuario;

//SesionUsuario.java
public class SesionUsuario {
 private static SesionUsuario instancia;
 private Usuario usuarioLogueado;

 // Constructor privado para evitar instanciación directa
 private SesionUsuario() {}

 // Método para obtener la instancia única de SesionUsuario
 public static SesionUsuario getInstancia() {
     if (instancia == null) {
         instancia = new SesionUsuario();
     }
     return instancia;
 }

 // Método para obtener el usuario logueado
 public Usuario getUsuarioLogueado() {
     return usuarioLogueado;
 }

 // Método para establecer el usuario logueado
 public void setUsuarioLogueado(Usuario usuarioLogueado) {
     this.usuarioLogueado = usuarioLogueado;
 }

 // Método para cerrar sesión y limpiar el usuario
 public void cerrarSesion() {
     this.usuarioLogueado = null;
 }
}
