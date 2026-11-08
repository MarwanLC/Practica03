package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** MODELO: Clase que hereda de una collection de Java para almacenar objetos de tipo Cliente */
public class GestorClientes extends ArrayList<Cliente> {
    private int actual; // El indice del Cliente a mostrar

    public GestorClientes() {
        super();
        this.actual = -1;
    }

    public void añadirCliente(Cliente c) {
        this.add(c);
        if (actual == -1) actual = 0;
    }

    public void borrarCliente() {
        if (!esVacia() && actual >= 0 && actual < this.size()) {
            this.remove(actual);
            if (this.isEmpty()) {
                actual = -1;
            } else if (actual >= this.size()) {
                actual = this.size() - 1;
            }
        }
    }

    public boolean esVacia() { return this.isEmpty(); }
    public void avanzar() { if (actual < this.size() - 1) actual++; }
    public void retroceder() { if (actual > 0) actual--; }
    public void irAlPrimero() { if (!esVacia()) actual = 0; }
    public void irAlUltimo() { if (!esVacia()) actual = this.size() - 1; }
    public boolean esPrimero() { return actual == 0; }
    public boolean esUltimo() { return actual == this.size() - 1; }
    
    public int getActualIndice() { return this.actual; }
    
    public Cliente obtenerClienteActual() {
        if (actual >= 0 && actual < this.size()) {
            return this.get(actual);
        }
        return null;
    }

    public void ordenarListaPorNumero() {
        Collections.sort(this, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return Integer.compare(c1.getNumero(), c2.getNumero());
            }
        });
        if (!esVacia()) actual = 0;
    }
}

