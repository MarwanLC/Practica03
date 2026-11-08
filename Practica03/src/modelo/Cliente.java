package modelo;

import java.time.LocalDate;

/** MODELO: Clase cliente basada en la practica anterior */
public class Cliente {
    private int numero;
    private String nombre;
    private float puntos;
    private LocalDate fechaNacimiento;

    // Constructor completo
    public Cliente(int numero, String nombre, float puntos, LocalDate fechaNacimiento) {
        this.numero = numero;
        this.nombre = nombre;
        this.puntos = puntos;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public float getPuntos() { return puntos; }
    public void setPuntos(float puntos) { this.puntos = puntos; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return "Cliente{" + "numero=" + numero + ", nombre=" + nombre + ", puntos=" + puntos + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
}

