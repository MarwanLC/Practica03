/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebapractica3;

/**
 *
 * @author marwa
 */
public class PruebaPractica3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}


















































/*
APUNTES DEFINITIVOS: PRÁCTICA JAVA MVC

PASO 0: Preparar el proyecto en NetBeans

1. Crea un proyecto nuevo: File -> New Project -> Java with Ant -> Java
Application.
2. En el panel izquierdo, haz clic derecho sobre Source Packages -> New ->
Java Package y crea 3 paquetes:
○ modelo
○ controlador
○ vista



PASO 1: Crear el Modelo (modelo.Cliente)

1. Clic derecho sobre el paquete modelo -> New -> Java Class -> Nombre:
Cliente.
2. Borra todo lo que salga y pega este código:

package modelo;
import java.time.LocalDate;
public class Cliente implements Comparable<Cliente> {

 private int numero;
 private String nombre;
 private float puntos;
 private LocalDate fechaNacimiento;
 public Cliente(int numero, String nombre, float puntos,
LocalDate fechaNacimiento) {
 this.numero = numero;
 this.nombre = nombre;
 this.puntos = puntos;
 this.fechaNacimiento = fechaNacimiento;
 }
 public int getNumero() { return numero; }
 public void setNumero(int numero) { this.numero = numero; }
 public String getNombre() { return nombre; }
 public void setNombre(String nombre) { this.nombre = nombre; }
 public float getPuntos() { return puntos; }
 public void setPuntos(float puntos) { this.puntos = puntos; }
 public LocalDate getFechaNacimiento() { return fechaNacimiento;
}
 public void setFechaNacimiento(LocalDate fechaNacimiento) {
this.fechaNacimiento = fechaNacimiento; }
 @Override
 public String toString() {
 return "Cliente{" + "numero=" + numero + ", nombre=" +
nombre + ", puntos=" + puntos + ", fecha=" + fechaNacimiento + '}';
 }
 // Sirve para ordenar los clientes de menor a mayor por su
número
 @Override
 public int compareTo(Cliente otro) {
 return Integer.compare(this.numero, otro.numero);
 }
}
Usa el código con precaución.


PASO 2: Crear el Controlador (controlador)
Aquí van dos clases: Validaciones (para comprobar errores) y ControladorLista
(la lista de clientes).
2.1. Clase Validaciones.java
1. Clic derecho sobre el paquete controlador -> New -> Java Class ->
Nombre: Validaciones.

2. Pega este código:

package controlador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Validaciones {
 // Obliga a escribir la fecha como día/mes/año (ejemplo:
25/04/2003)
 private static final DateTimeFormatter FORMATO =
DateTimeFormatter.ofPattern("dd/MM/yyyy");
 public static boolean comprobarFecha(String textoFecha) {
 try {
 LocalDate fecha = LocalDate.parse(textoFecha, FORMATO);
 return fecha.getYear() >= 1900 && fecha.getYear() <=
2015;
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

2.2. Clase ControladorLista.java
1. Clic derecho sobre el paquete controlador -> New -> Java Class ->
Nombre: ControladorLista.

2. Pega este código:

package controlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import modelo.Cliente;
// Hereda de ArrayList como pide el enunciado
public class ControladorLista extends ArrayList<Cliente> {

 // Guarda la posición del cliente que estamos viendo en pantalla
 private int actual;
 public ControladorLista() {
 // Clientes iniciales de prueba
 this.add(new Cliente(104, "Carlos Gómez", 500.5f,
LocalDate.of(2011, 5, 12)));
 this.add(new Cliente(101, "Ana López", 320.0f,
LocalDate.of(2001, 11, 28)));
 this.add(new Cliente(105, "Luis Martínez", 450.0f,
LocalDate.of(2005, 2, 14)));
 this.add(new Cliente(102, "María Rodríguez", 890.0f,
LocalDate.of(2002, 8, 5)));

 ordenarPorNumero();
 actual = 0;
 }
 public boolean esVacia() {
 return this.isEmpty();
 }
 public boolean esPrimero() {
 return !esVacia() && actual == 0;
 }
 public boolean esUltimo() {
 return !esVacia() && actual == this.size() - 1;
 }
 public void avanzar() {
 if (!esUltimo() && !esVacia()) {
 actual++;
 }
 }
 public void retroceder() {
 if (!esPrimero() && !esVacia()) {
 actual--;
 }
 }
 public void irPrimero() {
 if (!esVacia()) {
 actual = 0;
 }
 }
 public void irUltimo() {
 if (!esVacia()) {
 actual = this.size() - 1;
 }
 }
 public Cliente obtenerClienteActual() {
 if (esVacia() || actual < 0 || actual >= this.size()) {
 return null;
 }
 return this.get(actual);
 }
 public void anadirCliente(Cliente c) {
 this.add(c);
 ordenarPorNumero();
 actual = this.indexOf(c);
 }
 public void borrarCliente() {
 if (!esVacia()) {
 this.remove(actual);
 if (esVacia()) {
 actual = -1;
 } else if (actual >= this.size()) {
 actual = this.size() - 1;
 }
 }
 }

public void ordenarPorNumero() {
 Collections.sort(this);
 }
}
Usa el código con precaución.
PASO 3: Crear las Vistas (vista)
Aquí creamos 3 pantallas: PanelAlta, PanelVisualizar y VentanaPrincipal.
3.1. Pantalla para crear clientes (PanelAlta.java)
1. Clic derecho sobre vista -> New -> JPanel Form -> Nombre: PanelAlta.
2. En la pestaña Design (arrastrar elementos de la derecha):
○ Arrastra 4 Label (Numero, Nombre, Puntos, Fecha (dd/mm/aaaa)).
None
○ Arrastra 4 Text Field al lado de cada etiqueta. Haz clic derecho sobre
cada caja de texto -> Change Variable Name y llámalas exactamente:
■ txtNumero
■ txtNombre
■ txtPuntos
■ txtFecha
○ Arrastra 1 Label debajo para los errores. En sus propiedades (abajo a
la derecha), cambia foreground a color Rojo y cámbiale el nombre de
variable a lblError.
○ Arrastra 1 Button abajo, ponle de texto Guardar, cámbiale el nombre
de variable a btnGuardar y haz doble clic encima de él para ir al
código.
3. En la pestaña Source (código):
○ Asegúrate de que tu clase tenga exactamente este constructor y este
evento del botón (no borres el código gris generado por NetBeans
debajo):
java
package vista;
import controlador.ControladorLista;
import controlador.Validaciones;
import java.time.LocalDate;
import modelo.Cliente;
public class PanelAlta extends javax.swing.JPanel {
 private ControladorLista controlador;
 // Constructor modificado para recibir la lista
 public PanelAlta(ControladorLista controlador) {
 initComponents();
 this.controlador = controlador;
 lblError.setText("");
 }
 // Código que se ejecuta al pulsar el botón Guardar
 private void
btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
 String strNumero = txtNumero.getText().trim();
 String nombre = txtNombre.getText().trim();
 String strPuntos = txtPuntos.getText().trim();
 String strFecha = txtFecha.getText().trim();
 if (nombre.isEmpty() ||
!Validaciones.comprobarNumero(strNumero)) {
 lblError.setText("ERROR: Revisa el número y el
nombre.");
 return;
 }
 if (!Validaciones.comprobarPuntos(strPuntos)) {
 lblError.setText("ERROR: Los puntos deben estar entre 0
y 1000.");
 return;
 }
 if (!Validaciones.comprobarFecha(strFecha)) {
 lblError.setText("ERROR: Fecha inválida. Usa dd/mm/aaaa
(1900-2015).");
 return;
 }
 int numero = Integer.parseInt(strNumero);
 float puntos = Float.parseFloat(strPuntos);
 LocalDate fecha = Validaciones.convertirFecha(strFecha);
 controlador.anadirCliente(new Cliente(numero, nombre,
puntos, fecha));
 // Limpiamos todo tras guardar
 lblError.setText("");
 txtNumero.setText("");
 txtNombre.setText("");
 txtPuntos.setText("");
 txtFecha.setText("");
 }

3.2. Pantalla para ver clientes (PanelVisualizar.java)

1. Clic derecho sobre vista -> New -> JPanel Form -> Nombre:
PanelVisualizar.
2. En la pestaña Design:
○ Arrastra 4 Label (Numero, Nombre, Puntos, Fecha).
○ Arrastra 4 Text Field, desmarca la casilla editable en las propiedades
de los 4, y llámalos exactamente:
■ txtNumero
■ txtNombre
■ txtPuntos
■ txtFecha
○ Arrastra 5 Button, cámbiales el nombre de variable y haz doble clic
en cada uno para crear su evento:
■ btnPrimero (Texto: Primero)
■ btnAnterior (Texto: Anterior)
■ btnSiguiente (Texto: Siguiente)
■ btnUltimo (Texto: Último)
■ btnBorrar (Texto: Borrar)

3. En la pestaña Source:
○ Escribe este constructor, el método actualizarVista() y los eventos
de los 5 botones:

package vista;
import controlador.ControladorLista;
import java.time.format.DateTimeFormatter;
import modelo.Cliente;
public class PanelVisualizar extends javax.swing.JPanel {
 private ControladorLista controlador;
 public PanelVisualizar(ControladorLista controlador) {
 initComponents();
 this.controlador = controlador;
 actualizarVista();
 }
 public void actualizarVista() {
 if (controlador.esVacia()) {
 txtNumero.setText("");
 txtNombre.setText("Sin clientes");
 txtPuntos.setText("");
 txtFecha.setText("");

 btnPrimero.setEnabled(false);
 btnAnterior.setEnabled(false);
 btnSiguiente.setEnabled(false);
 btnUltimo.setEnabled(false);
 btnBorrar.setEnabled(false);
 } else {
 Cliente actual = controlador.obtenerClienteActual();
 DateTimeFormatter formato =
DateTimeFormatter.ofPattern("dd/MM/yyyy");

 txtNumero.setText(String.valueOf(actual.getNumero()));
 txtNombre.setText(actual.getNombre());
 txtPuntos.setText(String.valueOf(actual.getPuntos()));

txtFecha.setText(actual.getFechaNacimiento().format(formato));
 btnBorrar.setEnabled(true);
 btnPrimero.setEnabled(!controlador.esPrimero());
 btnAnterior.setEnabled(!controlador.esPrimero());
 btnSiguiente.setEnabled(!controlador.esUltimo());
 btnUltimo.setEnabled(!controlador.esUltimo());
 }
 }
 private void
btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {
 controlador.irPrimero();
 actualizarVista();
 }
 private void
btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
 controlador.retroceder();
 actualizarVista();
 }
 private void
btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {
 controlador.avanzar();
 actualizarVista();
 }
 private void btnUltimoActionPerformed(java.awt.event.ActionEvent
evt) {
 controlador.irUltimo();
 actualizarVista();
 }
 private void btnBorrarActionPerformed(java.awt.event.ActionEvent
evt) {
 controlador.borrarCliente();
 actualizarVista();
 }

3.3. Ventana Principal con Menú (VentanaPrincipal.java)

1. Clic derecho sobre vista -> New -> JFrame Form -> Nombre:
VentanaPrincipal.
2. En la pestaña Design:
○ Haz clic en el fondo gris de la ventana y en sus propiedades desmarca
la casilla resizable (para que sea de tamaño fijo).
○ Arrastra un Menu Bar desde la paleta de la derecha hasta la parte
superior de la ventana.
○ Haz clic derecho sobre el menú (File) -> Add From Palette -> Menu
Item hasta tener 3 opciones.
○ Cambia el texto y haz doble clic en cada una de las 3 opciones:
■ Visualizar
■ Nuevo
■ Salir
3. En la pestaña Source:
○ Pega los atributos, el constructor y los 3 eventos del menú:

package vista;
import controlador.ControladorLista;
public class VentanaPrincipal extends javax.swing.JFrame {
 private ControladorLista controlador;
 private PanelVisualizar panelVer;
 private PanelAlta panelNuevo;
 public VentanaPrincipal() {
 initComponents();
 controlador = new ControladorLista();
 panelVer = new PanelVisualizar(controlador);
 panelNuevo = new PanelAlta(controlador);

 // Al abrir el programa muestra directamente el panel de
visualizar
 this.setContentPane(panelVer);
 this.pack();
 }
 // Opción de menú: Visualizar
 private void
jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
 panelVer.actualizarVista();
 this.setContentPane(panelVer);
 this.revalidate();
 this.repaint();
 this.pack();
 }
 // Opción de menú: Nuevo
 private void
jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
 this.setContentPane(panelNuevo);
 this.revalidate();
 this.repaint();
 this.pack();
 }
 // Opción de menú: Salir
 private void
jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
 System.exit(0);
 }
Usa el código con precaución.
PASO 4: Ejecutar el programa
Haz clic derecho sobre VentanaPrincipal.java en el árbol de la izquierda -> Run
File (o pulsa Shift + F6).*/