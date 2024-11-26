package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.SesionUsuario;
import BLL.Usuario;
import BLL.Autor;
import BLL.Libro; // Asegúrate de importar la clase Libro
import DLL.ControllerUsuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class DisplayCrearProyecto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputTitulo;
    private LinkedList<Usuario> listaEditores;
    Autor usuario = (Autor) SesionUsuario.getInstancia().getUsuarioLogueado();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayCrearProyecto frame = new DisplayCrearProyecto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DisplayCrearProyecto() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 818, 597);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Crear Proyecto");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 30));
        lblNewLabel.setBounds(294, 48, 204, 47);
        contentPane.add(lblNewLabel);

        JLabel labelTitulo = new JLabel("Titulo");
        labelTitulo.setFont(new Font("Calisto MT", Font.PLAIN, 20));
        labelTitulo.setBounds(147, 148, 188, 36);
        contentPane.add(labelTitulo);

        JLabel labelGenero = new JLabel("Genero");
        labelGenero.setFont(new Font("Calisto MT", Font.PLAIN, 20));
        labelGenero.setBounds(147, 240, 188, 36);
        contentPane.add(labelGenero);

        JLabel labelEditor = new JLabel("Editor");
        labelEditor.setFont(new Font("Calisto MT", Font.PLAIN, 20));
        labelEditor.setBounds(147, 333, 188, 36);
        contentPane.add(labelEditor);

        JComboBox<String> inputGenero = new JComboBox<>();
        inputGenero.setFont(new Font("Segoe UI Variable", Font.PLAIN, 15));
        inputGenero.setModel(new DefaultComboBoxModel<>(new String[] { 
                "Fantasía", "Ciencia Ficción", "Romance", "Misterio", 
                "Historia", "Biografía", "Poesía", "Thriller", 
                "Drama", "Aventura", "Horror", "Novela Gráfica", 
                "Ensayo", "Autoayuda", "Filosofía", "Literatura Infantil", 
                "Suspenso", "Policial", "Erótico", "Distopía", "Paranormal", 
                "Literatura Clásica", "Comedia", "Viajes", "Crónica"
            }));
        inputGenero.setBounds(147, 277, 503, 36);
        contentPane.add(inputGenero);

        inputTitulo = new JTextField();
        inputTitulo.setBounds(147, 194, 503, 36);
        contentPane.add(inputTitulo);
        inputTitulo.setColumns(10);

        listaEditores = ControllerUsuario.MostrarEditores(); // Almacenar la lista de editores
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Usuario editor : listaEditores) {
            model.addElement(editor.getNombre() + " " + editor.getApellido());
        }

        JComboBox<String> inputEditor = new JComboBox<>(model);
        inputEditor.setBounds(147, 368, 503, 36);
        contentPane.add(inputEditor);

        JButton buttonCrear = new JButton("Crear");
        buttonCrear.setFont(new Font("Perpetua", Font.PLAIN, 20));
        buttonCrear.setBounds(359, 468, 113, 36);
        contentPane.add(buttonCrear);
        
        buttonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = inputTitulo.getText();
                String genero = (String) inputGenero.getSelectedItem();
                String editor = (String) inputEditor.getSelectedItem();
           
                if (usuario.CrearProyecto(titulo, usuario ,genero, editor)) {
					JOptionPane.showMessageDialog(null, "Se creo correctamente tu nuevo proyecto");
					DisplayMisLibros misLibros = new DisplayMisLibros();
					misLibros.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "lol");
				} 
            }
        });

        JButton buttonVolver = new JButton("");
        buttonVolver.setBackground(new Color(255, 255, 255));
        buttonVolver.setIcon(new ImageIcon(DisplayCrearProyecto.class.getResource("/img/volver-flecha.png")));
        buttonVolver.setBounds(30, 27, 41, 36);
        contentPane.add(buttonVolver);
    }
}

