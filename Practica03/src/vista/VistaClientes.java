package vista;

import java.awt.*;
import javax.swing.*;

/** VISTA: Ventana contenedora principal no redimensionable con menu */
public class VistaClientes extends JFrame {
    private final PanelVisualizar panelVisualizar = new PanelVisualizar();
    private final PanelNuevo panelNuevo = new PanelNuevo();
    private final JPanel contenedor = new JPanel(new CardLayout());

    public VistaClientes() {
        super("Gestión de Clientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // No redimensionable

        // Menu superior
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem itemVisualizar = new JMenuItem("Visualizar");
        JMenuItem itemNuevo = new JMenuItem("Nuevo");
        JMenuItem itemSalir = new JMenuItem("Salir");

        menu.add(itemVisualizar);
        menu.add(itemNuevo);
        menu.addSeparator();
        menu.add(itemSalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Añadir paneles al contenedor de tarjetas
        contenedor.add(panelVisualizar, "Visualizar");
        contenedor.add(panelNuevo, "Nuevo");
        add(contenedor);

        CardLayout cl = (CardLayout) contenedor.getLayout();

        // Acciones del menu para cargar los paneles
        itemVisualizar.addActionListener(e -> cl.show(contenedor, "Visualizar"));
        itemNuevo.addActionListener(e -> cl.show(contenedor, "Nuevo"));
        itemSalir.addActionListener(e -> System.exit(0));

        setSize(480, 320);
        setLocationRelativeTo(null);
        cl.show(contenedor, "Visualizar"); // Mostrar visualizar por defecto
    }

    public PanelVisualizar getPanelVisualizar() { return panelVisualizar; }
    public PanelNuevo getPanelNuevo() { return panelNuevo; }
}
