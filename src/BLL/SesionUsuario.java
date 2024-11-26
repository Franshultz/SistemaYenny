package BLL;

//SesionUsuario.java
public class SesionUsuario {
 private static SesionUsuario instancia;
 private Usuario usuarioLogueado;

 private SesionUsuario() {}

 public static SesionUsuario getInstancia() {
     if (instancia == null) {
         instancia = new SesionUsuario();
     }
     return instancia;
 }

 public Usuario getUsuarioLogueado() {
     return usuarioLogueado;
 }

 public void setUsuarioLogueado(Usuario usuarioLogueado) {
     this.usuarioLogueado = usuarioLogueado;
 }

 public void cerrarSesion() {
     this.usuarioLogueado = null;
     instancia = null;
 }
}
