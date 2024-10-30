package GUI;

import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Autor;
import BLL.Editor;
import BLL.Libro;
import BLL.Usuario;
import DLL.ControllerLibro;
import DLL.ControllerUsuario;

public class Main {

    public static void main(String[] args) {

        boolean finSistema = false;
        do {
            String[] opcionesInicio = { "Iniciar Sesion", "Registrarse", "Salir" };
            int opcionInicioElegida = JOptionPane.showOptionDialog(null, "Sign Up - Sign In", "Librerias Yenny", 0, 0, null, opcionesInicio, opcionesInicio);

            switch (opcionInicioElegida) {
                case 0:
                    Usuario usuario = Usuario.LogIn();
                    if (usuario == null) {
                        JOptionPane.showMessageDialog(null, "Mail y/o Contraseña no encontrados, verifique su contraseña o mail");
                    } else {    
                        switch (usuario.getRol()) {
                            case "autor":
                                boolean finSesionAutor = false;
                                do {    
                                    Autor autor = (Autor) ControllerUsuario.BuscarUsuario(usuario.getId());
                                    String[] opcionesInicioAutor = { "Seleccionar proyecto", "Crear nuevo proyecto", "Eliminar proyecto", "Cerrar Sesion" };
                                    int opcionInicioAutorElegida = JOptionPane.showOptionDialog(null, "", "Librerias Yenny", 0, 0, null, opcionesInicioAutor, opcionesInicioAutor);
                                    
                                    switch (opcionInicioAutorElegida) {
                                        case 0:
                                            Libro proyectoElegido = Autor.SeleccionarLibro(usuario);
                                            if (proyectoElegido != null) {
                                                boolean finSubMenuAutor = false;
                                                do {
                                                    String[] opcionesAutor = { "Subir Entrega", "Enviar Revision", "Ver estado de entrega", "Salir" };
                                                    int opcionAutorElegida = JOptionPane.showOptionDialog(null, "Seleccione una opcion", proyectoElegido.getTitulo(), 0, 0, null, opcionesAutor, opcionesAutor);
                                                    
                                                    switch (opcionAutorElegida) {
                                                        case 0: 
                                                            autor.SubirEntrega(proyectoElegido);
                                                            break;
                                                        case 1: 
                                                            autor.SubirRevision(proyectoElegido);
                                                            break;
                                                        case 2: 
                                                            JOptionPane.showMessageDialog(null, "La ultima entrega de " + proyectoElegido.getTitulo() + " esta " + autor.verEstadoEntrega(proyectoElegido));
                                                            break;
                                                        case 3: 
                                                            finSubMenuAutor = true;
                                                            break;
                                                        default:
                                                            JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                                                    }
                                                } while (!finSubMenuAutor);
                                            }
                                            break;
                                            
                                        case 1:
                                            autor.CrearProyecto();
                                            break;
                                            
                                        case 2:
                                            Libro proyectoAEliminar = Autor.SeleccionarLibro(usuario);
                                            if (proyectoAEliminar != null) {
                                                autor.EliminarProyecto(proyectoAEliminar);
                                            }
                                            break;
                                            
                                        case 3:
                                            finSesionAutor = true;
                                            break;
                                            
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                                            break;
                                    }
                                } while (!finSesionAutor);
                                break;
                                
                            case "editor":
                                boolean finSesionEditor = false;
                                do {
                                    Editor editor = (Editor) ControllerUsuario.BuscarUsuario(usuario.getId());
                                    String[] opcionesInicioEditor = { "Calificar Manuscritos", "Enviar Feedback", "Ver Manuscritos(ID)", "Cerrar Sesion" };
                                    int opcionInicioEditorElegida = JOptionPane.showOptionDialog(null, "", "Librerias Yenny", 0, 0, null, opcionesInicioEditor, opcionesInicioEditor);

                                    switch (opcionInicioEditorElegida) {
                                        case 0:
                                            editor.revisarEntrega();
                                            break;
                                        case 1:
                                            editor.enviarFeedback();
                                            break;
                                        case 2:
                                            String autorIdStr = JOptionPane.showInputDialog("Ingrese el ID del autor para mostrar sus libros:");
                                            if (autorIdStr != null) {
                                                try {
                                                    int autorId = Integer.parseInt(autorIdStr);
                                                    LinkedList<Libro> libros = ControllerLibro.MostrarLibros(autorId);
                                                    StringBuilder librosInfo = new StringBuilder("Libros del Autor (ID: " + autorId + "):\n");
                                                    for (Libro libro : libros) {
                                                        librosInfo.append("ID: ").append(libro.getId())
                                                                  .append(", Titulo: ").append(libro.getTitulo())
                                                                  .append(", Estado: ").append(libro.getEstadoLibro()).append("\n");
                                                    }
                                                    if (libros.isEmpty()) {
                                                        librosInfo = new StringBuilder("No hay libros para el autor con ID: " + autorId);
                                                    }
                                                    JOptionPane.showMessageDialog(null, librosInfo.toString());
                                                } catch (NumberFormatException e) {
                                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                                                }
                                            }
                                            break;
                                        case 3:
                                            finSesionEditor = true;
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                                            break;
                                    }
                                } while (!finSesionEditor);
                                break;
                                
                            case "admin":
                                boolean finSesionAdmin = false;
                                do {
                                    String[] opcionesInicioAdmin = { "CRUD Libros", "CRUD Usuarios", "Cerrar Sesion" };
                                    int opcionInicioAdminElegida = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administración", 0, 0, null, opcionesInicioAdmin, opcionesInicioAdmin);

                                    switch (opcionInicioAdminElegida) {
                                        case 0:
                                            boolean finCrudLibros = false;
                                            do {
                                                String[] opcionesLibrosAdmin = { "Agregar Libro", "Mostrar Libros", "Buscar Libro", "Eliminar Libro", "Salir" };
                                                int opcionLibrosAdmin = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administración - Libros", 0, 0, null, opcionesLibrosAdmin, opcionesLibrosAdmin);

                                                switch (opcionLibrosAdmin) {
                                                    case 0:
                                                    	//`titulo`, `autor_id`, `genero`, `precio`, `formato` , `estadoLibro`, `fechaLanzamiento`, `numeroVentas`, `stockDisponible`, `editor_id`
                                                        String newtitulo = JOptionPane.showInputDialog("Ingresar titulo del nuevo Libro");
                                                        int newid = Integer.parseInt(JOptionPane.showInputDialog("Ingresar ID del autor del Libro"));
                                                        String newgenero = JOptionPane.showInputDialog("Ingresar genero del nuevo Libro");
                                                        Double newprecio = Double.parseDouble(JOptionPane.showInputDialog("Ingresar precio del nuevo Libro"));
                                                        LocalDate newfecha = LocalDate.now();
                                                        int newstock = Integer.parseInt(JOptionPane.showInputDialog("Ingresar stock"));
                                                        int newid2 =Integer.parseInt(JOptionPane.showInputDialog("Ingresar id del editor"));
                                                        
                                                        Libro nuevoLibro = new Libro((Integer) null, newtitulo, null, newtitulo, newtitulo, 1, null, null, newfecha, newid, null); 
                                                        ControllerLibro.AgregarLibro(nuevoLibro);
                                                        break;
                                                    case 1:
                                                    	int idAutor = 0;
                                                    	idAutor = Integer.parseInt(JOptionPane.showInputDialog("Ingresar ID del autor para ver sus libros"));
                                                        LinkedList<Libro> libros = ControllerLibro.MostrarLibros(idAutor);

                                                        
                                                        if (libros.isEmpty()) {
                                                            JOptionPane.showMessageDialog(null, "No hay libros disponibles para este autor.");
                                                        } else {
                                                           
                                                            StringBuilder mensaje = new StringBuilder("Libros del autor:\n\n");
                                                            for (Libro libro : libros) {
                                                                mensaje.append("ID: ").append(libro.getId())
                                                                       .append(", Título: ").append(libro.getTitulo())
                                                                       .append(", ISBN: ").append(libro.getIsbn())
                                                                       .append(", Precio: ").append(libro.getPrecio())
                                                                       .append(", Estado: ").append(libro.getEstadoLibro()).append("\n");
                                                            }

                                                      
                                                            JOptionPane.showMessageDialog(null, mensaje.toString());
                                                        }
                                                        break;
                                                    case 2:
                                                        String idBuscarStr = JOptionPane.showInputDialog("Ingrese el ID del libro a buscar:");
                                                        int idBuscar = Integer.parseInt(idBuscarStr);
                                                        Libro libroBuscado = ControllerLibro.BuscarLibro(idBuscar);
                                                        if (libroBuscado != null) {
                                                            JOptionPane.showMessageDialog(null, "Libro encontrado: " + libroBuscado.getTitulo());
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
                                                        }
                                                        break;
                                                    case 3:
                                                        String idEliminarStr = JOptionPane.showInputDialog("Ingrese el ID del libro a eliminar:");
                                                        int idEliminar = Integer.parseInt(idEliminarStr);
                                                        boolean eliminado = ControllerLibro.EliminarLibro(idEliminar);
                                                        if (eliminado) {
                                                            JOptionPane.showMessageDialog(null, "Libro eliminado con éxito.");
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Error al eliminar el libro.");
                                                        }
                                                        break;
                                                    case 4:
                                                        finCrudLibros = true;
                                                        break;
                                                    default:
                                                        JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                                                        break;
                                                }
                                            } while (!finCrudLibros);
                                            break;

                                        case 1:
                                            boolean finCrudUsuarios = false;
                                            do {
                                                String[] opcionesUsuariosAdmin = { "Agregar Usuario", "Actualizar Usuario", "Buscar Usuario", "Eliminar Usuario", "Salir" };
                                                int opcionUsuariosAdmin = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Administración - Usuarios", 0, 0, null, opcionesUsuariosAdmin, opcionesUsuariosAdmin);

                                                switch (opcionUsuariosAdmin) {
                                                    case 0:
                                                        String nombreUsuario = JOptionPane.showInputDialog("Ingresar nombre de usuario");
                                                        String apellidoUsuario = JOptionPane.showInputDialog("Ingresar apellido de usuario");
                                                        String emailUsuario = JOptionPane.showInputDialog("Ingresar email de usuario");
                                                        String contraUsuario = JOptionPane.showInputDialog("Ingresar contraseña");
                                                        String estadoUsuario = JOptionPane.showInputDialog("Ingresar estado de cuenta (activo / inactivo)");

                                                        Autor nuevoUsuario = new Autor(nombreUsuario, apellidoUsuario, emailUsuario, contraUsuario, estadoUsuario);
                                                        ControllerUsuario.AgregarUsuario(nuevoUsuario);
                                                        break;
                                                    case 1:
                                                    	 LinkedList<Usuario> usuarios = ControllerUsuario.MostrarUsuarios();
                                                         
                                                        
                                                         if (usuarios.isEmpty()) {
                                                             JOptionPane.showMessageDialog(null, "No hay usuarios disponibles para actualizar.");
                                                             break;
                                                         }

                                                        
                                                         String mensaje = "Seleccione un usuario para actualizar:\n\n";
                                                         for (Usuario usuarioAct : usuarios) {
                                                             mensaje += usuarioAct.getId() + ": " + usuarioAct.getNombre() + " " + usuarioAct.getApellido() + "\n";
                                                         }

                                                        
                                                         String idStr = JOptionPane.showInputDialog(mensaje);
                                                         
                                                         try {
                                                             int id = Integer.parseInt(idStr);
                                                             Usuario usuarioAActualizar = null;

                                                            
                                                             for (Usuario usuarioAct : usuarios) {
                                                                 if (usuarioAct.getId() == id) {
                                                                     usuarioAActualizar = usuarioAct;
                                                                     break;
                                                                 }
                                                             }

                                                            
                                                             if (usuarioAActualizar != null) {
                                                                 String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", usuarioAActualizar.getNombre());
                                                                 String nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido:", usuarioAActualizar.getApellido());
                                                                 String nuevoEstadoCuenta = JOptionPane.showInputDialog("Ingrese el nuevo estado de cuenta (activo/inactivo):", usuarioAActualizar.getEstadoCuenta());

                                                                 
                                                                 usuarioAActualizar.setNombre(nuevoNombre);
                                                                 usuarioAActualizar.setApellido(nuevoApellido);
                                                                 usuarioAActualizar.setEstadoCuenta(nuevoEstadoCuenta);

                                                                 
                                                                 ControllerUsuario.ActualizarUsuario(usuarioAActualizar);
                                                                 JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");
                                                             } else {
                                                                 JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                                                             }
                                                         } catch (NumberFormatException e) {
                                                             JOptionPane.showMessageDialog(null, "ID no válida.");
                                                         } catch (Exception e) {
                                                             JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage());
                                                         }
                                                         break;
                                                    case 2:
                                                        String idBuscarUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario a buscar:");
                                                        int idBuscarUsuario = Integer.parseInt(idBuscarUsuarioStr);
                                                        Usuario usuarioBuscado = ControllerUsuario.BuscarUsuario(idBuscarUsuario);
                                                        if (usuarioBuscado != null) {
                                                            JOptionPane.showMessageDialog(null, "Usuario encontrado: \n Nombre completo: "+ usuarioBuscado.getNombre() + " " + usuarioBuscado.getApellido()+ "\nEmail: " + usuarioBuscado.getEmail()+ "\nRol: " + usuarioBuscado.getRol()+ "\nEstado de cuenta: " + usuarioBuscado.getEstadoCuenta());
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                                                        }
                                                        break;
                                                    case 3:
                                                        String idEliminarUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:");
                                                        int idEliminarUsuario = Integer.parseInt(idEliminarUsuarioStr);
                                                        ControllerUsuario.EliminarUsuario(idEliminarUsuario);
                                                        break;
                                                    case 4:
                                                        finCrudUsuarios = true;
                                                        break;
                                                    default:
                                                        JOptionPane.showMessageDialog(null, "Error");
                                                        break;
                                                }
                                            } while (!finCrudUsuarios);
                                            break;

                                        case 2:
                                            finSesionAdmin = true;
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Error");
                                            break;
                                    }
                                } while (!finSesionAdmin);
                                break;
                        }
                    }
                    break;

                case 1:
                    Usuario.SingUp();        
                    break;

                case 2:
                    finSistema = true;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Error");
                    break;
            }
        } while (!finSistema);
    }
}
