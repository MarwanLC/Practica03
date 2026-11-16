package controlador;

/*
Clase ControladorLista.java
1. Clic derecho sobre el paquete controlador -> New -> Java Class ->
Nombre: ControladorLista.
2. Pega este código:*/

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
        this.add(new Cliente(104, "Carlos Gómez", 500.5f, LocalDate.of(2011, 5, 12)));
        this.add(new Cliente(101, "Ana López", 320.0f, LocalDate.of(2001, 11, 28)));
        this.add(new Cliente(105, "Luis Martínez", 450.0f, LocalDate.of(2005, 2, 14)));
        this.add(new Cliente(102, "María Rodríguez", 890.0f, LocalDate.of(2002, 8, 5)));
        
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
