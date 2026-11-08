package controlador;

import modelo.Cliente;
import modelo.GestorClientes;
import vista.VistaClientes;
import java.time.LocalDate;
import java.awt.Color;
import javax.swing.JLabel;

/** CONTROLADOR: conecta el modelo y la vista, e implementa el control de errores. */
public class ControladorClientes {
    private final GestorClientes modelo;
    private final VistaClientes vista;

    public ControladorClientes(GestorClientes modelo, VistaClientes vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Conectar eventos del panel Visualizar
        this.vista.getPanelVisualizar().addPrimeroListener(e -> { modelo.irAlPrimero(); actualizarVista(); });
        this.vista.getPanelVisualizar().addAnteriorListener(e -> { modelo.retroceder(); actualizarVista(); });
        this.vista.getPanelVisualizar().addSiguienteListener(e -> { modelo.avanzar(); actualizarVista(); });
        this.vista.getPanelVisualizar().addUltimoListener(e -> { modelo.irAlUltimo(); actualizarVista(); });
        this.vista.getPanelVisualizar().addBorrarListener(e -> { modelo.borrarCliente(); actualizarVista(); });

        // Conectar evento del panel Nuevo
        this.vista.getPanelNuevo().addGuardarListener(e -> procesarNuevoCliente());

        actualizarVista();
    }

    private void actualizarVista() {
        Cliente c = modelo.obtenerClienteActual();
        if (c != null) {
            vista.getPanelVisualizar().mostrarCliente(
                String.valueOf(c.getNumero()),
                c.getNombre(),
                String.valueOf(c.getPuntos()),
                c.getFechaNacimiento().toString(),
                modelo.getActualIndice() + 1,
                modelo.size()
            );
            vista.getPanelVisualizar().setNavegacion(!modelo.esPrimero(), !modelo.esUltimo());
        } else {
            vista.getPanelVisualizar().limpiarCampos();
            vista.getPanelVisualizar().setNavegacion(false, false);
        }
    }

    private void procesarNuevoCliente() {
        var panel = vista.getPanelNuevo();
        JLabel labelError = panel.getLblError();

        String txtNum = panel.getTxtNumero().trim();
        String txtNom = panel.getTxtNombre().trim();
        String txtPts = panel.getTxtPuntos().trim();
        String txtFec = panel.getTxtFecha().trim();

        if (txtNum.isEmpty() || txtNom.isEmpty() || txtPts.isEmpty() || txtFec.isEmpty()) {
            labelError.setForeground(Color.RED);
            labelError.setText("Error: Todos los campos son obligatorios.");
            return;
        }

        // Comprobación estática de errores
        if (validarCampos(txtFec, txtPts, labelError)) {
            try {
                int num = Integer.parseInt(txtNum);
                float pts = Float.parseFloat(txtPts);
                LocalDate fecha = LocalDate.parse(txtFec);

                Cliente nuevo = new Cliente(num, txtNom, pts, fecha);
                modelo.añadirCliente(nuevo);
                modelo.ordenarListaPorNumero(); // Mantiene la lista ordenada

                labelError.setForeground(Color.GREEN.darker());
                labelError.setText("¡Cliente guardado correctamente!");
                panel.limpiarCampos();
                actualizarVista();
            } catch (NumberFormatException ex) {
                labelError.setForeground(Color.RED);
                labelError.setText("Error: El número debe ser un entero.");
            }
        }
    }

    // Método estático para el control de errores
    public static boolean validarCampos(String txtFecha, String txtPuntos, JLabel lblError) {
        lblError.setForeground(Color.RED);

        try {
            float puntos = Float.parseFloat(txtPuntos);
            if (puntos < 0 || puntos > 1000) {
                lblError.setText("Error: Puntos deben estar entre 0 y 1000.");
                return false;
            }
        } catch (NumberFormatException e) {
            lblError.setText("Error: Formato de puntos inválido.");
            return false;
        }

        try {
            LocalDate fecha = LocalDate.parse(txtFecha);
            int anio = fecha.getYear();
            if (anio < 1900 || anio > 2015) {
                lblError.setText("Error: Año de nacimiento debe estar entre 1900 y 2015.");
                return false;
            }
        } catch (Exception e) {
            lblError.setText("Error: Formato fecha inválido (AAAA-MM-DD).");
            return false;
        }

        lblError.setText("");
        return true;
    }
}
