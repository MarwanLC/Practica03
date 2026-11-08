package practica03;

import controlador.ControladorClientes;
import javax.swing.SwingUtilities;
import modelo.Cliente;
import modelo.GestorClientes;
import vista.VistaClientes;
import java.time.LocalDate;

/** Solo ensambla: crea los datos, la vista y el controlador, y los conecta. */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorClientes modelo = new GestorClientes();
            
            // Añadimos los clientes iniciales
            modelo.añadirCliente(new Cliente(103, "Ana López", 500f, LocalDate.of(1995, 5, 12)));
            modelo.añadirCliente(new Cliente(101, "Luis Pérez", 150f, LocalDate.of(1988, 11, 23)));
            modelo.añadirCliente(new Cliente(105, "Marta Ruiz", 950f, LocalDate.of(2002, 3, 15)));
            modelo.añadirCliente(new Cliente(102, "Pablo Gómez", 340f, LocalDate.of(1990, 7, 30)));
            modelo.añadirCliente(new Cliente(104, "Lucía Torres", 720f, LocalDate.of(2010, 1, 5)));

            modelo.ordenarListaPorNumero(); // Los dejamos ordenados automaticamente

            VistaClientes vista = new VistaClientes();
            new ControladorClientes(modelo, vista);
            vista.setVisible(true);
        });
    }
}
