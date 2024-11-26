package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import BLL.Autor;
import BLL.Libro;
import BLL.SesionUsuario;
import BLL.Usuario;
import DLL.ControllerLibro;

public class DisplayMisLibros extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    Autor usuario = (Autor) SesionUsuario.getInstancia().getUsuarioLogueado();


    public DisplayMisLibros() {
        setTitle("Gestor de Manuscritos");
        setSize(1056, 419);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Mis Libros");
        lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 40));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(377, 34, 250, 48);
        contentPane.add(lblNewLabel);

        // Columnas de la tabla (incluye el ID oculto)
        String[] columnNames = {"ID", "Titulo", "Autor", "ISBN", "Genero", "Estado", "Ventas", "Stock", "Editor", "Subir Entrega", "Eliminar"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 9 || column == 10; // Solo columnas "Subir Entrega" y "Eliminar" editables
            }
        };
        table = new JTable(model);
        configurarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 124, 1027, 226);
        contentPane.add(scrollPane);

        JButton buttonVolver = new JButton("");
        buttonVolver.setBackground(new Color(192, 192, 192));
        buttonVolver.setIcon(new ImageIcon(DisplayMisLibros.class.getResource("/img/volver-flecha.png")));
        buttonVolver.setBounds(10, 34, 39, 35);
        contentPane.add(buttonVolver);

        buttonVolver.addActionListener(e -> {
            DashboardAutor dashboardAutor = new DashboardAutor();
            dashboardAutor.setVisible(true);
            dispose();
        });

        actualizarTabla(usuario);
    }

    private void configurarTabla() {
        // Ocultar la columna "ID"
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);

        // Configurar renderizador y editor para las columnas "Subir Entrega" y "Eliminar"
        table.getColumn("Subir Entrega").setCellRenderer(new ButtonRenderer());
        table.getColumn("Subir Entrega").setCellEditor(new ButtonEditor());
        table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar").setCellEditor(new ButtonEditorEliminar());
        table.setRowHeight(30);
    }

    private void actualizarTabla(Usuario usuario) {
        model.setRowCount(0);
        LinkedList<Libro> listaLibros = ControllerLibro.MostrarLibrosEnProceso(usuario.getId());

        for (Libro libro : listaLibros) {
            // Verificar si el libro está en proceso
            boolean mostrarBotonSubirEntrega = libro.getEstadoLibro().equals("En proceso");

            model.addRow(new Object[] {
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor().getNombre() + " " + libro.getAutor().getApellido(),
                libro.getIsbn(),
                libro.getGenero(),
                libro.getEstadoLibro(),
                libro.getNumeroVentas(),
                libro.getStockDisponible(),
                libro.getEditor().getNombre() + " " + libro.getEditor().getApellido(),
                mostrarBotonSubirEntrega ? "Subir Entrega" : "", 
                "Eliminar"
            });
        }
    }


    class ButtonRenderer extends JButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int idLibro; // ID del libro correspondiente

        public ButtonEditor() {
            super(new JCheckBox());
            button = new JButton("Subir Entrega");
            button.addActionListener(e -> {
                DisplayEntregasOpciones displayEntregOpciones = new DisplayEntregasOpciones(idLibro);
                displayEntregOpciones.setVisible(true);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            idLibro = (int) model.getValueAt(row, 0); // Guardar el ID del libro correspondiente
            button.setText(value != null ? value.toString() : "Subir Entrega");
            return button;
        }

        private void realizarAccion(int idLibro) {
            String[] opciones = {"Subir Entrega", "Enviar Revisión", "Cancelar"};
            int opcionSeleccionada = JOptionPane.showOptionDialog(
                null,
                "Seleccione la acción que desea realizar:",
                "Acciones de Entrega",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (opcionSeleccionada == JOptionPane.CLOSED_OPTION || opcionSeleccionada == 2) {
                return;
            }

            boolean accionRealizada = false;

            if (opcionSeleccionada == 0) { // Subir Entrega
                accionRealizada = usuario.SubirEntrega(ControllerLibro.BuscarLibro(idLibro));
                if (!accionRealizada) {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se pudo subir la entrega. Intente nuevamente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else if (opcionSeleccionada == 1) { // Enviar Revisión
                accionRealizada = usuario.SubirRevision(ControllerLibro.BuscarLibro(idLibro));
                if (!accionRealizada) {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se pudo enviar la revisión. Intente nuevamente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }

            if (accionRealizada) {
                JOptionPane.showMessageDialog(
                    null,
                    "Acción realizada con éxito para el libro con ID: " + idLibro,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    class ButtonEditorEliminar extends DefaultCellEditor {
        private JButton button;
        private int idLibro;

        public ButtonEditorEliminar() {
            super(new JCheckBox());
            button = new JButton("Eliminar");
            button.addActionListener(e -> {
                eliminarLibro(idLibro);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            idLibro = (int) model.getValueAt(row, 0); // Guardar el ID del libro correspondiente
            button.setText(value != null ? value.toString() : "Eliminar");
            return button;
        }

        private void eliminarLibro(int idLibro) {
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de que deseas eliminar este libro?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean libroEliminado = ControllerLibro.EliminarLibro(idLibro);
                if (libroEliminado) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El libro fue eliminado exitosamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    actualizarTabla(usuario); // Actualizar la tabla después de la eliminación
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se pudo eliminar el libro. Intente nuevamente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }
}

