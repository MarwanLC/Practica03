package modelo;

/*
PASO 1: Crear el Modelo (modelo.Cliente)
1. Clic derecho sobre el paquete modelo -> New -> Java Class -> Nombre:
Cliente.
2. Borra todo lo que salga y pega este código:*/

import java.time.LocalDate;

public class Cliente implements Comparable<Cliente> {
    
    private int numero;
    private String nombre;
    private float puntos;
    private LocalDate fechaNacimiento;

    public Cliente(int numero, String nombre, float puntos, LocalDate fechaNacimiento) {
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

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return "Cliente{" + "numero=" + numero + ", nombre=" + nombre + ", puntos=" + puntos + ", fecha=" + fechaNacimiento + '}';
    }

    // Sirve para ordenar los clientes de menor a mayor por su número
    @Override
    public int compareTo(Cliente otro) {
        return Integer.compare(this.numero, otro.numero);
    }
}
