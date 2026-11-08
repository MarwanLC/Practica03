package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/** VISTA: Panel con los elementos necesarios para crear un nuevo cliente */
public class PanelNuevo extends JPanel {
    private final JTextField txtNumero = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPuntos = new JTextField();
    private final JTextField txtFecha = new JTextField();
    private final JLabel lblError = new JLabel("", SwingConstants.CENTER);
    private final JButton btnGuardar = new JButton("Guardar Cliente");

    public PanelNuevo() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(new JLabel("Número Cliente:")); formulario.add(txtNumero);
        formulario.add(new JLabel("Nombre Completo:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Puntos Iniciales (0-1000):")); formulario.add(txtPuntos);
        formulario.add(new JLabel("Fecha Nac. (AAAA-MM-DD):")); formulario.add(txtFecha);

        JPanel panelSur = new JPanel(new BorderLayout(5, 5));
        panelSur.add(lblError, BorderLayout.NORTH);
        panelSur.add(btnGuardar, BorderLayout.SOUTH);

        add(new JLabel("INSERTAR NUEVO CLIENTE", SwingConstants.CENTER), BorderLayout.NORTH);
        add(formulario, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }

    public String getTxtNumero() { return txtNumero.getText(); }
    public String getTxtNombre() { return txtNombre.getText(); }
    public String getTxtPuntos() { return txtPuntos.getText(); }
    public String getTxtFecha() { return txtFecha.getText(); }
    public JLabel getLblError() { return lblError; }

    public void limpiarCampos() {
        txtNumero.setText("");
        txtNombre.setText("");
        txtPuntos.setText("");
        txtFecha.setText("");
    }

    public void addGuardarListener(ActionListener l) { btnGuardar.addActionListener(l); }
}
