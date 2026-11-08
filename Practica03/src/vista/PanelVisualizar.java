package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/** VISTA: Panel para consultar y borrar los datos de los clientes */
public class PanelVisualizar extends JPanel {
    private final JLabel lblContador = new JLabel("Sin clientes", SwingConstants.CENTER);
    private final JTextField txtNumero = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPuntos = new JTextField();
    private final JTextField txtFecha = new JTextField();

    private final JButton btnPrimero = new JButton("<<");
    private final JButton btnAnterior = new JButton("<");
    private final JButton btnSiguiente = new JButton(">");
    private final JButton btnUltimo = new JButton(">>");
    private final JButton btnBorrar = new JButton("Borrar Actual");

    public PanelVisualizar() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel datos = new JPanel(new GridLayout(4, 2, 8, 8));
        txtNumero.setEditable(false); txtNombre.setEditable(false);
        txtPuntos.setEditable(false); txtFecha.setEditable(false);

        datos.add(new JLabel("Número:")); datos.add(txtNumero);
        datos.add(new JLabel("Nombre:")); datos.add(txtNombre);
        datos.add(new JLabel("Puntos:")); datos.add(txtPuntos);
        datos.add(new JLabel("Fecha Nacimiento:")); datos.add(txtFecha);

        // Botonera de direccion
        JPanel panelNavegacion = new JPanel(new GridLayout(1, 4, 5, 5));
        panelNavegacion.add(btnPrimero); panelNavegacion.add(btnAnterior);
        panelNavegacion.add(btnSiguiente); panelNavegacion.add(btnUltimo);

        JPanel botoneraInferior = new JPanel(new BorderLayout(5, 5));
        botoneraInferior.add(panelNavegacion, BorderLayout.CENTER);
        botoneraInferior.add(btnBorrar, BorderLayout.SOUTH);

        add(lblContador, BorderLayout.NORTH);
        add(datos, BorderLayout.CENTER);
        add(botoneraInferior, BorderLayout.SOUTH);
    }

    public void mostrarCliente(String num, String nom, String pts, String fec, int pos, int total) {
        lblContador.setText("Cliente " + pos + " de " + total);
        txtNumero.setText(num);
        txtNombre.setText(nom);
        txtPuntos.setText(pts);
        txtFecha.setText(fec);
    }

    public void limpiarCampos() {
        lblContador.setText("Lista vacía");
        txtNumero.setText(""); txtNombre.setText("");
        txtPuntos.setText(""); txtFecha.setText("");
    }

    public void setNavegacion(boolean puedeAtras, boolean puedeAdelante) {
        btnPrimero.setEnabled(puedeAtras);
        btnAnterior.setEnabled(puedeAtras);
        btnSiguiente.setEnabled(puedeAdelante);
        btnUltimo.setEnabled(puedeAdelante);
    }

    public void addPrimeroListener(ActionListener l) { btnPrimero.addActionListener(l); }
    public void addAnteriorListener(ActionListener l) { btnAnterior.addActionListener(l); }
    public void addSiguienteListener(ActionListener l) { btnSiguiente.addActionListener(l); }
    public void addUltimoListener(ActionListener l) { btnUltimo.addActionListener(l); }
    public void addBorrarListener(ActionListener l) { btnBorrar.addActionListener(l); }
}
