package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Libro;
import BLL.SesionUsuario;
import BLL.Usuario;
import DLL.ControllerLibro;

public class DisplayMisLibros extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayMisLibros frame = new DisplayMisLibros();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DisplayMisLibros() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1001, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("Mis Libros");
        lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 40));
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        Usuario usuario = SesionUsuario.getInstancia().getUsuarioLogueado();

        String[] columnNames = {"Titulo", "Autor", "ISBN", "Genero", "Estado", "Ventas", "Stock", "Editor"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);

        actualizarTabla(usuario);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        setTitle("Gestor de Manuscritos");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void actualizarTabla(Usuario usuario) {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        LinkedList<Libro> listaLibros = ControllerLibro.MostrarLibrosEnProceso(usuario.getId());

        // Agregar los datos al modelo
        for (Libro libro : listaLibros) {
            model.addRow(new Object[]{
                libro.getTitulo(),
                libro.getAutor().getNombre() + " " + libro.getAutor().getApellido(),
                libro.getIsbn(),
                libro.getGenero(),
                libro.getEstadoLibro(),
                libro.getNumeroVentas(),
                libro.getStockDisponible(),
                libro.getEditor().getNombre() + " " + libro.getEditor().getApellido()
            });
        }
    }
}
