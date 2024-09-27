package ManuscriptFlow;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        Libreria yenny = new Libreria("Yenny", "Av. Santa Fe 1860");
        Administrador admin = null;
        Autor autor = null;
        Editor editor = null;
        boolean finSistema = false;

        for (int i = 0; i < 3; i++) {			
			String[] rolesOpciones = {"Administrador", "Autor", "Editor", "Iniciar Sesion"};
			int opcion = JOptionPane.showOptionDialog(null, "Seleccione que tipo de Usuario desea registrar", null, 0, 0, null, rolesOpciones, rolesOpciones);

			switch (opcion) {
			case 0:
				admin = new Administrador();
				admin.RegistrarUsuario();
				break;
			case 1:
				autor = new Autor();
				autor.RegistrarUsuario();
				break;
			case 2:
				editor = new Editor();
				editor.RegistrarUsuario();
				break;
				
			case 3:
				break;

			default:
				JOptionPane.showMessageDialog(null, "Sacá la mano de ahi carajo");
				break;
			}
		}			
        
        while (!finSistema) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea Iniciar Sesion?", "Confirmar", JOptionPane.YES_NO_OPTION);
            

            if (respuesta == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Fin del Sistema");
                finSistema = true;
                break;
            }


            Usuario sesion = null;
            String[] rolesOpciones = { "Administrador", "Autor", "Editor" };
            int opcionInicio = JOptionPane.showOptionDialog(null, "Seleccione su rol", "Iniciar Sesión", 0, 0, null, rolesOpciones, rolesOpciones);

            switch (opcionInicio) {
                case 0:
                    sesion = new Administrador().IniciarSesion();
                    break;
                case 1:
                    sesion = new Autor().IniciarSesion();
                    break;
                case 2:
                    sesion = new Editor().IniciarSesion();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un rol");
                    break;
            }

            if (sesion != null) {
                String rolSesion = sesion.getRol();
                boolean cerrarSesion = false;


                if (rolSesion.equals("Administrador")) {
                    do {
                        String[] metodosOpciones = { "Administrar Libros", "Administrar Usuarios", "Cerrar Sesion" };
                        int opcion = JOptionPane.showOptionDialog(null, "Seleccione una acción", null, 0, 0, null, metodosOpciones, metodosOpciones);

                        switch (opcion) {
                            case 0:
                                admin.AdministrarLibros();
                                break;
                            case 1:
                                admin.AdministrarUsuarios();
                                break;
                            case 2:
                                cerrarSesion = true; 
                                break;
                        }
                    } while (!cerrarSesion);
                }

                else if (rolSesion.equals("Editor")) {
                    do {
                        String[] metodosOpcionesEditor = { "Revisar Manuscritos", "Enviar Feedback", "Cerrar Sesion" };
                        int opcionEditor = JOptionPane.showOptionDialog(null, "Seleccione una acción", null, 0, 0, null, metodosOpcionesEditor, metodosOpcionesEditor);

                        switch (opcionEditor) {
                            case 0:
                            	JOptionPane.showMessageDialog(null, "Revisar entrega");
                                editor.revisarEntrega();
                                break;
                            case 1:
                            	JOptionPane.showMessageDialog(null, "Enviar Feedback");
                                editor.enviarFeedback();
                                break;
                            case 2:
                                cerrarSesion = true; 
                                break;
                        }
                    } while (!cerrarSesion);
                }


                else if (rolSesion.equals("Autor")) {
                    do {
                        String[] metodosOpcionesAutor = { "Subir Entrega", "Editar Manuscritos", "Ver estado de Manuscritos", "Agregar Recordatorio", "Cerrar Sesion" };
                        int opcionAutor = JOptionPane.showOptionDialog(null, "Seleccione una acción", null, 0, 0, null, metodosOpcionesAutor, metodosOpcionesAutor);

                        switch (opcionAutor) {
                            case 0:
                            	JOptionPane.showMessageDialog(null, "Seleccionar Manuscrito");
                                //autor.SubirEntrega();
                                break;
                            case 1:
                            	JOptionPane.showMessageDialog(null, "Seleccionar manuscrito para editar");
                                //autor.SubirEntrega();
                                break;
                            case 2:
                            	JOptionPane.showMessageDialog(null, "Mis manuscritos");
                                //autor.verEstadoManuscrito();
                                break;
                            case 3:
                            	JOptionPane.showMessageDialog(null, "Recordatorio");
                                //autor.agregarRecordatorio();
                                break;
                            case 4:
                                cerrarSesion = true; 
                                break;
                        }
                    } while (!cerrarSesion);
                }
            }
        }
    }
}