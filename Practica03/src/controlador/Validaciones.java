package controlador;

/*
PASO 2: Crear el Controlador (controlador)
Aquí van dos clases: Validaciones (para comprobar errores) y ControladorLista
(la lista de clientes).
2.1. Clase Validaciones.java
1. Clic derecho sobre el paquete controlador -> New -> Java Class -> Nombre: Validaciones.
2. Pega este código:*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validaciones {

    // Obliga a escribir la fecha como día/mes/año (ejemplo: 25/04/2003)
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean comprobarFecha(String textoFecha) {
        try {
            LocalDate fecha = LocalDate.parse(textoFecha, FORMATO);
            return fecha.getYear() >= 1900 && fecha.getYear() <= 2015;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate convertirFecha(String textoFecha) {
        return LocalDate.parse(textoFecha, FORMATO);
    }

    public static boolean comprobarPuntos(String textoPuntos) {
        try {
            float puntos = Float.parseFloat(textoPuntos);
            return puntos >= 0 && puntos <= 1000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean comprobarNumero(String textoNumero) {
        try {
            Integer.parseInt(textoNumero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
